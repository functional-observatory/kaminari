import java.net.HttpCookie
import upickle.default._

object Notion:
  val baseUrl = "https://www.notion.so/api/v3"
  val spacesUrl = s"$baseUrl/getSpaces"
  val searchUrl = s"$baseUrl/search"
  val deleteBlocksUrl = s"$baseUrl/deleteBlocks"
  var token = ""

  def setToken(token: String) =
    this.token = token

  def getCookies =
    Map("token_v2" -> new HttpCookie("token_v2", token))

  def getSpaces =
    val response =
      requests
        .post(Notion.spacesUrl, cookies = getCookies)
        .text()
    read[ujson.Value](response).obj

  def getTrash(spaceId: String) =
    val body =
      ujson.Obj(
        "limit" -> 1000,
        "query" -> "",
        "sort" -> "Relevance",
        "source" -> "trash",
        "spaceId" -> spaceId,
        "type" -> "BlocksInSpace",
        "filters" -> ujson.Obj(
          "isDeletedOnly" -> true,
          "isNavigableOnly" -> true,
          "lastEditedTime" -> ujson.Obj(),
          "requireEditPermissions" -> false,
          "excludeTemplates" -> false,
          "editedBy" -> ujson.Arr(),
          "createdTime" -> ujson.Obj(),
          "createdBy" -> ujson.Arr(),
          "ancestors" -> ujson.Arr()
        )
      )

    val response = requests
      .post(Notion.searchUrl, cookies = getCookies, data = body)
      .text()

    read[ujson.Value](response)
      .obj("results")
      .arr
      .map(result => result("id").str)

  def deleteBlocks(blocks: ujson.Arr, permanentlyDelete: Boolean) =
    val data = ujson.Obj(
      "blockIds" -> blocks,
      "permanentlyDelete" -> permanentlyDelete
    )

    val response =
      requests.post(
        Notion.deleteBlocksUrl,
        cookies = getCookies,
        data = data
      )

    read[ujson.Value](response)
