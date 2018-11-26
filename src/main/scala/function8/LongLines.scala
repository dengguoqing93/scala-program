package function8

import scala.io.Source

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/25
  */
object LongLines {

  private def processLine(filename: String, width: Int, line: String) = {
    if (line.length > width)
      println(filename + ":" + line.trim)
  }

  def processFile(filename: String, width: Int) = {
    val resources = Source.fromFile(filename)
    for (line <- resources.getLines())
      processLine(filename, width, line)
  }

}
