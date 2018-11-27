package composeAndInheritance

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/26
  */
abstract class Element {
  def contents: Array[String]

  def height: Int = contents.length

  def width: Int = if (height == 0) 0 else contents(0).length


}
