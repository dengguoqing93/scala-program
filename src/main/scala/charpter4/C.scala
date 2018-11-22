package charpter4

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/19
  */
object M {
  implicit object IntMarker // ➊
  implicit object StringMarker
  def m(seq: Seq[Int])(implicit i: IntMarker.type): Unit = // ➋
    println(s"Seq[Int]: $seq")
  def m(seq: Seq[String])(implicit s: StringMarker.type): Unit = // ➌
    println(s"Seq[String]: $seq")
}