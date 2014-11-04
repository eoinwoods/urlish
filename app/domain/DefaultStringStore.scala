package domain

class DefaultStringStore(initialContent: Map[String, String]) extends StringStore {

  var strings = initialContent

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

  private def generateKey(): String = {
    val next = strings.size + 1000
    Base62Converter.toBase62(next)
  }

  override def size: Long = {
    strings.size
  }

  override def find(k: String): Option[String] = {
    strings.get(k)
  }

  override def getAll: List[(String, String)] = {
    strings.toList
  }
}


