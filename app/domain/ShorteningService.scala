package domain

/**
 * Created by eoin on 19/09/2014.
 */
trait ShorteningService {

  def shorten(url: String): (String, String)

  def unshorten(shortForm: String): (String, String)

  def getShortenedUrls: List[(String, String)]

  def getCount: Long

}
