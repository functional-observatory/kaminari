package ifsc

import upickle.default._

object Main extends App {
  implicit val bankReader: Reader[Bank] = macroR

  val ifscCode = args(0)
  val response = requests.get(s"https://ifsc.razorpay.com/$ifscCode")

  val bank = read[Bank](response.text())

  println("Bank name: ", bank.BANK)
  println("Bank branch: ", bank.BRANCH)
  println("Bank address: ", bank.ADDRESS)
  println("Bank city: ", bank.CITY)
}
