package domain

import play.api.libs.json.{Json, JsString, JsObject}

/**
 * Created by eoin on 19/09/2014.
 */
class DefaultShorteningService extends ShorteningService {

  val shortener = new Shortener() ;

  override def shorten(shorteningRequestJson: String): String = {
    val request = Json.parse(shorteningRequestJson)
    val url = request \ "url"
    val shortForm = shortener.shorten(url.toString())

    Json.obj("url" -> url, "shortForm" -> shortForm).toString
  }

  override def getCount: String = {
    Json.obj("urlCount" -> shortener.storeSize).toString
  }

  override def unshorten(unshortenRequestJson: String): String = {
    val request = Json.parse(unshortenRequestJson)
    val shortForm = request \ "shortForm"
    val longForm = shortener.unshorten(shortForm.toString)

    Json.obj("url" -> JsString(longForm.getOrElse("")), "shortForm" -> shortForm).toString
  }
}
