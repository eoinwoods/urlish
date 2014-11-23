import java.net.URLEncoder

import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.libs.json._
import play.api.test.Helpers._
import play.api.test._


/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beNone
    }

    "render the index page" in new WithApplication{
      val home = route(FakeRequest(GET, "/")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "text/html")
      contentAsString(home) must contain("URL Shortener")
    }

    "shorten a known URL to the right short form" in new WithApplication {
      val formParams = Map("url" -> "http://www.bbc.co.uk")
      val req = FakeRequest(POST, "/shorten").withFormUrlEncodedBody(formParams.toList: _*)
      val output = route(req).get
      status(output) must equalTo(OK)
      contentType(output) must beSome.which(_ == "text/html")
      contentAsString(output) must contain("Short Form: aa")
    }

    "return JSON for a URL list web service" in new WithApplication {
      val home = route(FakeRequest(GET, "/json/urls")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "application/json")
      contentAsString(home) must contain("oracle.com")
    }

    "return JSON for a URL lookup web service" in new WithApplication {
      val home = route(FakeRequest(GET, "/json/url/aa")).get

      status(home) must equalTo(OK)
      contentType(home) must beSome.which(_ == "application/json")
      val json = Json.parse(contentAsString(home))
      (json \ "shortForm").toString() equals ("\"aa\"")
      (json \ "url").toString() must contain("bbc.co.uk")
    }

    "shorten a URL via a REST service" in new WithApplication {
      val requestPath = "/json/shorten/" + URLEncoder.encode("http://www.bbc.co.uk", "UTF-8")
      val result = route(FakeRequest(POST, requestPath)).get
      status(result) must be equalTo(OK)
      contentType(result) must beSome.which(_ == "application/json")
      val json = Json.parse(contentAsString(result))
      (json \ "url").toString() must contain("www.bbc.co.uk")
      (json \ "shortForm").toString equals("\"aa\"")
    }

  }
}
