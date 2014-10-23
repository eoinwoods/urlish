package domain

import org.scalatest.FlatSpec

class TestBase62Conversion extends FlatSpec {

  "A Base62 Converter" must "convert 0[10] to 0[62]" in {
    assert(Base62Converter.toBase62(0) == "0")
  }

  it must "also convert 0[62] back to zero" in {
    assert(Base62Converter.toBase10("0") == 0)
  }

  it must "convert 100[10] to 1C[62]" in {
    assert(Base62Converter.toBase62(100) == "1C")
  }

  it must "convert 321[62] to 11657[10]" in {
    assert(Base62Converter.toBase10("321") == 11657)
  }

  it must "be consistent with arithmetic" in {
    val smallerVal = "ab8"
    val largerVal = "ab9"
    assert(Base62Converter.toBase10(largerVal) - Base62Converter.toBase10(smallerVal) == 1)
  }
}
