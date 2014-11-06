package controllers

import domain.{DefaultShorteningService, ShorteningService}
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._

object Application extends Controller {


  val initialUrls = Map(
    "aa" -> "http://www.bbc.co.uk",
    "ab" -> "http://www.amazon.co.uk",
    "ac" -> "http://www.oracle.com",
    "ad" -> "http://www.endava.com",
    "ae" -> "http://www.ubs.com"
  )
  val svc: ShorteningService = new DefaultShorteningService(initialUrls)

  def index = Action {
    //    Ok(views.html.index("Your new application is ready."))
    //    Ok(views.html.urlview("http://www.bbc.co.uk", "abdef"))
    Ok(views.html.urlListView(svc.getShortenedUrls))
  }

  def getUrls = Action {
    Ok(Json.toJson(svc.getShortenedUrls))
  }

  def getUrl(shortForm: String) = Action {
    Ok(Json.toJson(svc.unshorten(shortForm)))
  }

  implicit object UrlWrites extends Writes[(String, String)] {

    def writes(url: (String, String)): JsValue = {
      Json.obj(
        "shortForm" -> url._1,
        "url" -> url._2
      )
    }
  }



  //  def getAll = Action {
  //    svc.getShortenedUrls
  //  }
}

