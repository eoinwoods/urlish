package domain

import org.scalatest.FlatSpec

class TestShorteningService extends FlatSpec {

  def fixture = {
    new {
      val svc = new DefaultShorteningService()
    }
  }

  "The shortening service" must "have a size of zero when initialised" in {
    val f = fixture
    assert(f.svc.getCount == 0)
  }

  it must "return a key for a URL" in {
    val f = fixture
    val url = "http://www.artechra.com"
    assert(f.svc.shorten(url)._1 == url && f.svc.shorten(url)._2.length > 1)
  }

  it must "return a shortened value consistently" in {
    val f = fixture
    val url = "http://www.artechra.com"
    val shortForm = f.svc.shorten(url)._2
    assert(f.svc.unshorten(shortForm) == f.svc.unshorten(shortForm))
  }

  it must "return an empty value for unknown URLs" in {
    val f = fixture
    val resp = f.svc.unshorten("x")
    resp ===("", "x")
  }

  it must "return different short values for different URLs" in {
    val f = fixture
    assert(f.svc.shorten("http://www.bbc.co.uk") != f.svc.shorten("http://www.oracle.com"))
  }

  it must "report the right size for the URL store" in {
    val svc = new DefaultShorteningService()
    svc.shorten("http://www.bbc.co.uk")
    svc.shorten("http://www.oracle.com")
    assert(svc.getCount == 2)
  }

}
