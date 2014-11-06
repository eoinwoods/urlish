package controllers

import domain.{DefaultShorteningService, ShorteningService}
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._
import views.html

object Application extends Controller {

  implicit object UrlWrites extends Writes[(String, String)] {

    def writes(url: (String, String)): JsValue = {
      Json.obj(
        "shortForm" -> url._1,
        "url" -> url._2
      )
    }
  }

  val initialUrls = Map(
    "aa" -> "http://www.bbc.co.uk",
    "ab" -> "http://www.amazon.co.uk",
    "ac" -> "http://www.oracle.com",
    "ad" -> "http://www.endava.com",
    "ae" -> "http://www.ubs.com"
  )
  val svc: ShorteningService = new DefaultShorteningService(initialUrls)

  val urlForm = Form(
    tuple(
      "url" -> nonEmptyText,
      "shortForm" -> optional(text)
    )
  )

  def index = Action {
    //    Ok(views.html.index("Your new application is ready."))
    //    Ok(views.html.urlview("http://www.bbc.co.uk", "abdef"))
    Ok(views.html.urlShorteningPage(urlForm))
  }

  def urlFormSubmit = Action { implicit request =>
    urlForm.bindFromRequest.fold(
    formWithErrors => BadRequest(html.urlShorteningPage(formWithErrors)), { case (url, shortForm) => Ok(html.urlShorteningPage(urlForm))}
    )

  }

  def urlList = Action {
    Ok(views.html.urlListView(svc.getShortenedUrls))
  }

  def shortenUrl = Action {
    Ok
  }

  def getUrls = Action {
    Ok(Json.toJson(svc.getShortenedUrls))
  }

  def getUrl(shortForm: String) = Action {
    Ok(Json.toJson(svc.unshorten(shortForm)))
  }

}

