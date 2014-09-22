package domain

class DefaultStringStore extends StringStore {

  var strings = Map[String, String]()

  override def store(value: String): String = {
    if (strings.values.toList.contains(value)) {
      //strings.map(_.swap)(value) -- this fails with a type error for reasons I don't understand
      val invMap = strings.map(_.swap)
      invMap(value)
    } else {
      val key = generateKey()
      strings = strings + (key -> value)
      key
    }
  }

  override def size: Long = {
    strings.size
  }

  override def find(k: String): Option[String] = {
    strings.get(k)
  }

  private def generateKey(): String = {
    val next = strings.size + 1000
    Base62Converter.toBase62(next)
  }
}
