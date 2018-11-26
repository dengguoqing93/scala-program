package function8

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/25
  */
object FindLongLines {
  def main(args: Array[String]): Unit = {
    val width = 20
    LongLines.processFile("src/main/scala/function8/LongLines.scala", width)
  }

}
