
object Upper{
  def uppper(strings: String*)= strings.map(_.toUpperCase())
}

println(Upper.uppper("Hello", "world"))