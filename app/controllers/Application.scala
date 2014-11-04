package controllers

import domain.{DefaultShorteningService, ShorteningService}
import play.api.mvc._

object Application extends Controller {

  val svc: ShorteningService = new DefaultShorteningService

  def index = Action {
    //    Ok(views.html.index("Your new application is ready."))
    //    Ok(views.html.urlview("http://www.bbc.co.uk", "abdef"))
    Ok(views.html.urlListView(svc.getShortenedUrls))
  }

  //  def getAll = Action {
  //    svc.getShortenedUrls
  //  }


}