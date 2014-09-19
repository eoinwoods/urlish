class Shortener(stringStore : StringStore = new DefaultStringStore()) {

  def shorten(string : String): String = {
    stringStore.retrieve(string) match {
      case None => {
        stringStore.store(string)
      }
      case Some(s) => {
        s
      }
    }
  }

}
