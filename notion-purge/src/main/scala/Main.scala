import scala.io.StdIn

object Main extends App {
  val token = sys.env.get("NOTION_TOKEN") match {
    case Some(token) => token
    case None        => throw new RuntimeException("NOTION_TOKEN not found in env")
  }

  Notion.setToken(token)

  val workspace = args(0)

  val spaces = Notion.getSpaces
    .map(linkedHashmap => linkedHashmap._2.obj)
    .flatMap(account => account("space").obj)
    .map(linkedHashmap => linkedHashmap._2.obj)
    .map(space => space("value").obj)

  val currentSpace =
    spaces
      .find(space => space("name").str == workspace)
      .map(space => space("id").str)

  val spaceId = currentSpace match {
    case Some(value) => value
    case None        => throw new RuntimeException(s"Workspace: $workspace not found")
  }

  val trash = Notion.getTrash(spaceId)

  println(
    "The following pages will be deleted permanently, to confirm press ENTER or press CTRL+C to abort."
  )

  trash.foreach(id => {
    println(id)
  })

  StdIn.readLine()

  Notion.deleteBlocks(trash, permanentlyDelete = true)
  println("Successfully purged")
}
