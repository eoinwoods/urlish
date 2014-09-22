package domain

/**
 * Created by eoin on 19/09/2014.
 */
trait ShorteningService {

  def shorten(shortenRequestJson : String) : String
  def unshorten(unshortenRequestJson : String) : String
  def getCount: String

}
