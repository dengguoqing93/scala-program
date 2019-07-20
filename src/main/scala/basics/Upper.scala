package basics

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2019/2/28
  */
class Upper {
  def upper(strings: String*): Seq[String] = {
    strings.map((s:String) => s.toUpperCase())
  }
}
