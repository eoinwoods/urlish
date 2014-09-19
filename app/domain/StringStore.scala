/**
 * Created by eoin on 05/09/2014.
 */
trait StringStore {

  def store(v : String): String

  def retrieve(k : String) : Option[String]

  def size : Long

}
