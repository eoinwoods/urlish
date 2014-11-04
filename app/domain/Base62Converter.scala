package domain

object Base62Converter {


  val ALPHABET = ('0' to '9') ++ ('a' to 'z') ++ ('A' to 'Z')

  def base(): Int = {
    ALPHABET.size
  }

  def toBase62(base10Val: Long): String = {
    generateBase62Characters(base10Val, List()).reverse.mkString
  }

  def toBase10(base62Val: String) = {
    convertToBase10(base62Val, 0)
  }

  private def generateBase62Characters(base10Val: Long, digits: Seq[Char]): Seq[Char] = {
    if (base10Val < base) {
      digits :+ mapBase10Digit(base10Val)
    } else {
      ALPHABET(base10Val.toInt % base) +: generateBase62Characters(base10Val.toInt / base, digits)
    }

  }

  private def mapBase10Digit(digit: Long): Char = {
    ALPHABET(digit.toInt)
  }

  private def convertToBase10(base62Val: String, acc: Long): Long = {
    if (base62Val.length == 0) {
      acc
    } else {
      val value = mapBase62Digit(base62Val.head) * math.pow(base, base62Val.length - 1).toInt
      convertToBase10(base62Val.tail, acc + value)
    }
  }

  private def mapBase62Digit(digit: Char): Long = {
    ALPHABET.indexOf(digit)
  }

}
