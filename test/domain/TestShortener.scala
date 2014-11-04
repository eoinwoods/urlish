package domain

import org.scalatest.FlatSpec

/**
 * Created by eoin on 11/10/2014.
 */
class TestShortener extends FlatSpec {

  val shortener = new Shortener()

  "The shortener" must "be empty initially" in {
    assert(shortener.storeSize == 0)
  }

  it must "store and retrieve a URL" in {
    val url = "http://www.oracle.com"
    val key = shortener.shorten(url);
    assert(url == shortener.unshorten(key))
  }

  it must "return a short form of at least 2 characters" in {
    assert(shortener.shorten("http://www.site1.com").length > 1)
  }

  it must "store and retrieve two URLs" in {
    val url1 = "http://www.site1.com"
    val url2 = "http://www.site2.com"
    val key1 = shortener.shorten(url1)
    val key2 = shortener.shorten(url2)
    assert(url1 == shortener.unshorten(key1))
    assert(url2 == shortener.unshorten(key2))
  }

  it must "return different keys for different URLs" in {
    assert(shortener.shorten("http://www.12345") != shortener.shorten("http://www.54321"))
  }

}
