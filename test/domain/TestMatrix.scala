package domain

import org.scalatest.{Matchers, FunSuite}

/**
 * Created by eoin on 17/09/2014.
 */
class TestMatrix extends FunSuite with Matchers {

  test("An empty matrix has no rows") {
    assert(new Matrix(Array[Array[Double]]()).rowRank == 0)
  }

  test("An empty matrix has no cols") {
    assert(new Matrix(Array[Array[Double]]()).colRank == 0)
  }

  test("A 2x2 matrix allows its elements to be retrieved") {
    val m = new Matrix(Array(Array(1.0, 2.0), Array(3.0, 4.0)))
    assert(m.col(0)(0) == 1.0 && m.col(1)(1) == 4.0)
  }

  test("A 1 x 3 matrix has one 3 item row") {
    val m = new Matrix(Array(Array(1.0, 10.0, 100.0)))
    m.row(0) should equal(Array(1.0, 10.0, 100.0))
    assert(m.rowRank == 1)
  }

  test("A 1 x 3 matrix has three 1 item colums") {
    val m = new Matrix(Array(Array(1.0, 10.0, 100.0)))
    m.col(1) should equal(Array(10.0))
    assert(m.colRank == 3)
  }

  test("A 3 x 3 matrix returns its second column correctly") {
    val m = new Matrix(Array(Array(1.0, 10.0, 100.0), Array(2.0, 20.0, 200.0), Array(3.0, 30.0, 300.0)))
    m.col(1) should equal(Array(10.0, 20.0, 30.0))
  }

  test("A 2x2 matrix converts itself to a representative string") {
    val m = new Matrix(Array(Array(10.0, 11.0), Array(20.0, 21.0)))
    println(m.toString)
    assert(m.toString.matches(".*10.0.*11.0.*"))

  }


}
