package casepattern

/**
  * 样例类和模式匹配简单示例
  *
  * @author DGQ
  * @since 2018/12/6
  */
abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object Expr {

  def main(args: Array[String]): Unit = {
    test()
  }

  def test(): Unit = {
    val v = Var("x")
    val op = BinOp("+", Number(1), v)
    op.copy(operator = "-")
    println(op)
    println("Hello")
  }
}