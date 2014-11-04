package domain

/**
 * Created by eoin on 19/09/2014.
 */
class DefaultShorteningService(initialContent: Map[String, String] = Map()) extends ShorteningService {

  val SHORT_FORM_NAME = "shortForm"
  val URL_NAME = "url"
  val URL_COUNT_NAME = "urlCount"
  val CONTAINER_NAME = "shortenedUrls"

  val shortener = new Shortener(initialContent);

  override def shorten(url: String): (String, String) = {
    val shortForm = shortener.shorten(url)
    (url, shortForm)
  }

  override def getCount: Long = {
    shortener.storeSize
  }

  override def unshorten(shortForm: String): (String, String) = {
    val longForm = shortener.unshorten(shortForm)
    (longForm, shortForm)
  }

  override def getShortenedUrls: List[(String, String)] = {
    shortener.getEntries
  }
}
