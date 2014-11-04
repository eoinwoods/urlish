package domain

import org.scalatest.FlatSpec

class TestSingleStringShortening extends FlatSpec {

  val shortener = new Shortener() ;

  "Two identical URLs" should "return the same short version when shortened twice" in {
    val url1 = "http://www.scala.org"
    val url2 = "http://www.scala.org"
    assert(shortener.shorten(url1) == shortener.shorten(url2))
  }

  "Two different URLs" should "return different shortened values" in {
    val shortUrlShortForm = shortener.shorten("url1")
    val longUrlShortForm = shortener.shorten("http://this-is-a-long-url.com/page1/page2/someaction.php")
    assert(shortUrlShortForm != longUrlShortForm)
  }

  it should "be of roughly constant length irrespective of string length" in {
    val shortUrlShortForm = shortener.shorten("url1")
    val longUrlShortForm = shortener.shorten("http://this-is-a-long-url.com/page1/page2/someaction.php")
    assert(math.abs(shortUrlShortForm.length - longUrlShortForm.length) <= 2)
  }

  it should "be handled correctly if it is huge" in {
    val longUrl = "http://" + List.fill(10000)('a') + ".com"
    assert(shortener.shorten(longUrl) == shortener.shorten(longUrl))
    assert(shortener.shorten(longUrl) != shortener.shorten("abc"))
  }

  it should "be handled correctly if the empty string" in {
    assert(shortener.shorten("") == shortener.shorten("") &&
           shortener.shorten("") != shortener.shorten("abc"))
  }


}