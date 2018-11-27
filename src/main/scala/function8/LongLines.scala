package function8

import scala.io.Source

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/25
  */
object LongLines {


  def processFile(filename: String, width: Int): Unit = {

    def processLine(line: String): Unit = {
      if (line.length > width)
        println(filename + ":" + line.trim)
    }

    val resources = Source.fromFile(filename)
    for (line <- resources.getLines())
      processLine(line)
  }

  def test(f: Int => Unit): Unit = {
    f
  }


}
