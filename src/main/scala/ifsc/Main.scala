package ifsc

import upickle.default._

object Main extends App {
  implicit val bankReader: Reader[Bank] = macroR

  val ifscCode = args(0)
  val response = requests.get(s"https://ifsc.razorpay.com/$ifscCode")

  val bank = read[Bank](response.text())

  println("Bank name: %s".format(bank.BANK))
  println("Bank branch: %s".format(bank.BRANCH))
  println("Bank address: %s".formar(bank.ADDRESS))
  println("Bank city: %s".format(bank.CITY))
}
