package controlAbstract

import java.io.File

/**
  * 文件筛选
  *
  * @author DGQ
  * @since 2018/11/26
  */
object FileMatcher {
  private def filesHere = {
    (new File(".")).listFiles
  }

  private def filesMatching(matcher: String => Boolean) = {
    for {
      file <- filesHere
      if matcher(file.getName)}
      yield file
  }

  def filesEnding(query: String) = filesMatching(_.endsWith(query))

  def filesContains(query: String) = filesMatching(_.contains(query))

  def fileRegex(query: String) = filesMatching(_.matches(query))
}
