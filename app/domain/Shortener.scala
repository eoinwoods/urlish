package domain

import scala.collection.{Map, mutable};

class Shortener(initialContent: Map[String, String] = Map()) {

  var stringStore: Map[String, String] = new mutable.HashMap[String, String]()

  if (initialContent.size > 0) {
    stringStore = stringStore ++ initialContent
  }

  def shorten(url: String): String = {
    if (stringStore.values.toList.contains(url)) {
      stringStore.map {
        _.swap
      } getOrElse(url, "")
    } else {
      val newKey = generateKey()
      stringStore = stringStore + (newKey -> url)
      newKey
    }
  }

  private def generateKey(): String = {
    val next = stringStore.keys.size + 1000
    Base62Converter.toBase62(next)
  }

  def unshorten(shortForm: String): String = {
    stringStore.get(shortForm).getOrElse("")
  }

  def storeSize = {
    stringStore.size
  }

  def getEntries: List[(String, String)] = {
    stringStore.toList
  }

}

object Shortener {
  def defaultContent: Map[String, String] = {
    Map(
      "http://www.bbc.co.uk" -> "aa",
      "http://www.oracle.com" -> "ab",
      "http://www.sybase.com" -> "ac",
      "http://www.intertrust.com" -> "ad",
      "http://www.eoinwoods.info" -> "ae"
    )
  }
}
