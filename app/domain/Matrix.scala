package domain

/**
 * Created by eoin on 17/09/2014.
 */
class Matrix(private val repr: Array[Array[Double]]) {
  def row(idx: Int): Seq[Double] = {
    repr(idx)
  }

  def col(idx: Int): Seq[Double] = {
    colRecur(repr, idx)
  }

  def colRecur(m: Array[Array[Double]], colIdx: Int): Array[Double] = {
    if (m.length == 0) {
      Array[Double]()
    } else {
      Array(m.head(colIdx)) ++ colRecur(m.tail, colIdx)
    }
  }

  lazy val rowRank = repr.size
  lazy val colRank = if (rowRank > 0) repr(0).size else 0

  override def toString = "Matrix" + repr.foldLeft("") { (msg, row) => msg + row.mkString("\n|", " | ", "|")}

}

