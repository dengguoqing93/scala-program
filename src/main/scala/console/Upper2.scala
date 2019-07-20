package console

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2019/3/7
  */
object Upper2 {
  def main(args: Array[String]): Unit = {
    val output = args.map(_.toUpperCase()).mkString(" ")
    println(output)
  }
}
