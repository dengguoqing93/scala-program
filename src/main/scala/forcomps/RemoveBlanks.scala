package forcomps

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/22
  */
class RemoveBlanks {

}

object RemoveBlanks {
  /**
    * 从指定的输入文件中移除空行
    *
    * @param path 输入文件
    * @param compressWhiteSpace
    * @return
    */
  def apply(path: String, compressWhiteSpace: Boolean = false): Seq[String] = {
    for {
      line <- scala.io.Source.fromFile(path).getLines.toSeq
      if line.matches("""^\s*$""") == false
      line2 = if (compressWhiteSpace) line replaceAll("\\s", " ") else line
    } yield line2
  }

  /**
    * 从指定的输入文件中移除空行，并将其他行内容依次发送给标准输出。
    *
    * @param 参数列表中包含了文件路径 。为每一个文件路径都增加了可选的"-"前缀，
    *                     并会压缩以"-"前缀开头文件中的剩余空白符。
    */
  def main(args: Array[String]) = {
    for {
      path2 <- args
      (compress, path) = if (path2 startsWith "-") (true, path2.substring(1))
      else (false, path2)
      line <- apply(path, compress)
    } println(line)
  }
}