package controllers

import domain.{DefaultShorteningService, ShorteningService}
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc._

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

  case class ShortenedUrl(
    url : String,
    shortForm : String
  )

  val urlForm = Form(
    single(
      "url" -> text
    )
  )

  val indexPage = views.html.urlShorteningPage(urlForm.fill(""))

  def index = Action {
    Ok(indexPage)
  }

  def shortenUrlFormHandler = Action { implicit request =>
      urlForm.bindFromRequest.fold(
        formWithErrors => Ok("Error: " + formWithErrors),
        submittedForm => {
          if (submittedForm.length == 0) {
            Ok(indexPage)
          } else {
            val (shortForm, originalUrl) = svc.shorten(submittedForm)
            Ok(views.html.shortenedUrlPage(originalUrl, shortForm))
          }
        }
      )
  }

  def urlList = Action {
    Ok(views.html.urlListView(svc.getShortenedUrls))
  }

  def shortenUrl(url : String) = Action {
    val (shortForm, _) = svc.shorten(url)
    Ok(Json.toJson((shortForm, url)))
  }

  def getUrls = Action {
    Ok(Json.toJson(svc.getShortenedUrls))
  }

  def getUrl(shortForm: String) = Action {
    Ok(Json.toJson(svc.unshorten(shortForm)))
  }

}

