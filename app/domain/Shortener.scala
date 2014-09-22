package domain ;

class Shortener(stringStore : StringStore = new DefaultStringStore()) {

  def shorten(string : String): String = {
    stringStore.find(string) match {
      case None =>
        stringStore.store(string)
      case Some(s) =>
        s
    }
  }

  def unshorten(shortForm: String) : Option[String] = {
    stringStore.find(shortForm)
  }

  def storeSize = {
    stringStore.size
  }

}
