package functionObject

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/24
  */
class Rational(n: Int, d: Int) extends Ordered[Rational] {


  override def compare(that: Rational): Int = {
    this.number * that.denon - that.number * this.denon
  }

  override def toString: String = number + "/" + denon

  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  val number: Int = n / g
  val denon: Int = d / g


  private def gcd(a: Int, b: Int): Int = {
    if (b == 0) a else gcd(b, a % b)
  }

  def this(n: Int) = this(n, 1)

  def +(that: Rational): Rational = {
    new Rational(number * that.denon + that.number * denon, denon * that.denon)
  }

  def *(that: Rational): Rational = {
    new Rational(number * that.number, denon * that.denon)
  }


  def lessThan(that: Rational): Boolean = {
    this.number * that.denon < that.number * this.denon
  }

  def +(i: Int): Rational = new Rational(number + i * denon, denon)

  def *(i: Int): Rational = new Rational(number * i, denon)

  def -(that: Rational): Rational = {
    new Rational(number * that.denon - that.number * denon,
      denon * that.denon)
  }

  def -(i: Int): Rational = new Rational(number - i * denon, denon)

  def /(that: Rational): Rational = new Rational(number * that.denon, denon * that.number)

  def /(i: Int): Rational = new Rational(number, denon * i)

  def max(that: Rational): Rational = {
    if (this.lessThan(that)) that else this
  }
}

object Rational extends App {
  val x = new Rational(1, 2)
  val y = new Rational(2, 3)
  println(x + y)

  println(x.+(y))
}
