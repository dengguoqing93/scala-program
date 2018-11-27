package composeAndInheritance

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/26
  */
class ArrayElement(val contents: Array[String]) extends Element {
}

object ArrayElement {
  val e = new ArrayElement(Array("Hello"))
}
