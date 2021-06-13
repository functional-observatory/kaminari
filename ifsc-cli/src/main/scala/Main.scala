import upickle.default._

@main def main(ifscCode: String): Unit =
  implicit val bankReader: Reader[Bank] = macroR

  val response = requests.get(s"https://ifsc.razorpay.com/$ifscCode")
  val bank = read[Bank](response.text())

  println("Bank name: %s".format(bank.BANK))
  println("Bank branch: %s".format(bank.BRANCH))
  println("Bank address: %s".format(bank.ADDRESS))
  println("Bank city: %s".format(bank.CITY))
