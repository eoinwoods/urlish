package domain

import org.scalatest.FlatSpec

class TestSingleStringShortening extends FlatSpec {

  val shortener = new Shortener() ;

  "A single string" should "return the same short version when shortened twice" in {
    val url = "http://www.scala.org"
    assert(shortener.shorten(url) == shortener.shorten(url))
  }

  it should "be of roughly constant length irrespective of string length" in {
    val shortLength = shortener.shorten("url1").length
    val longLength = shortener.shorten("http://this-is-a-long-url.com/page1/page2/someaction.php").length
    assert(math.abs(shortLength - longLength) <= 2)
  }

  it should "be handled correctly if the empty string" in {
    assert(shortener.shorten("") == shortener.shorten("") &&
           shortener.shorten("") != shortener.shorten("abc"))
  }

  it should "be handled correctly if it is huge" in {
    val longUrl = "http://" + List.fill(10000)('a') + ".com"
    assert(shortener.shorten(longUrl) == shortener.shorten(longUrl) &&
      shortener.shorten(longUrl) != shortener.shorten("abc"))

  }

}