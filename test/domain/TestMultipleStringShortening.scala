package domain

import org.scalatest.FlatSpec

class TestMultipleStringShortening extends FlatSpec {

  val shortener = new Shortener()

  "Two strings" should "shorten to different strings" in {
    assert(shortener.shorten("url1") != shortener.shorten("url2"))
  }

  they should "shorten to the same string if equivalent" in {
    assert(shortener.shorten("url3") == shortener.shorten("url3"))
  }

}
