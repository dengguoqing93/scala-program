package casepattern

/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/12/8
  */
class ExprFormatter {
  private val opGroups = Array(Set("|", "||"), Set("&", "&&"), Set("^"), Set("==", "!="),
    Set("<", "<=", ">", ">="), Set("+", "-"), Set("*", "%"))
  private val precedence = {
    val assocs = for {
      i <- 0 until opGroups.length
      op <- opGroups(i)
    } yield op -> i
    assocs.toMap
  }

  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1

  import composeAndInheritance.Element
  import composeAndInheritance.Element.elem
  import Expr._

  private def format(e: Expr, enclPrec: Int): Element = {
    e match {
      case Var(name) => elem(name)

      case Number(num) =>
        def stripDot(s: String) = {
          if (s endsWith ".0") s
            .substring(0, s.length - 2)
          else
            s
        }

        elem(stripDot(num.toString))
      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence)

      case BinOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val frac = top above line above bot
        if (enclPrec != fractionPrecedence) frac
        else elem(" ") beside frac beside elem(" ")

      case BinOp(op, left, right) =>
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec + 1)
        val oper = l beside elem(" " + op + " ") beside r
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")
    }
  }

  def format(e: Expr): Element = format(e, 0)

}

import Expr._

object Express extends App {
  val f = new ExprFormatter
  val e1 = BinOp("*", BinOp("/", Number(1), Number(2)),
    BinOp("+", Var("3"), Number(1)))
  val e2 = BinOp("+", BinOp("/", Var("x"), Number(2)),
    BinOp("/", Number(1.5), Var("x")))

  val e3 = BinOp("/", e1, e2)

  def show(e: Expr) = println(f.format(e) + "\n\n")

  for (e <- Array(e1, e2, e3)) show(e)
}
