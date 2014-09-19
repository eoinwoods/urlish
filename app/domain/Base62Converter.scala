object Base62Converter {


  val ALPHABET = ('0' to '9') ++ ('a' to 'z') ++ ('A' to 'Z')

  def base() : Int = {
    ALPHABET.size
  }

  def toBase62(base10Val : Int) : String = {
    generateBase62Characters(base10Val, List()).reverse.mkString
  }

  private def generateBase62Characters(base10Val : Int, digits : Seq[Char]): Seq[Char] = {
    if (base10Val < base) {
      digits :+ mapBase10Digit(base10Val)
    } else {
      ALPHABET(base10Val % base) +: generateBase62Characters(base10Val / base, digits)
    }

  }

  private def mapBase10Digit(digit : Int) : Char = {
    ALPHABET(digit)
  }

  def toBase10(base62Val : String) = {
    convertToBase10(base62Val, 0)
  }

  private def convertToBase10(base62Val : String, acc : Int) : Int = {
    if (base62Val.length == 0) {
      acc
    } else {
      val value = mapBase62Digit(base62Val.head) * math.pow(base, base62Val.length - 1).toInt
      convertToBase10(base62Val.tail, acc + value)
    }
  }

  private def mapBase62Digit(digit : Char) : Int = {
    ALPHABET.indexOf(digit)
  }

}
