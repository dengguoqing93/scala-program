/**
  * $DESCRIPTION
  *
  * @author DGQ
  * @since 2018/11/19
  */
package progscala.implicits.payroll

sealed trait PreTaxDeductions

sealed trait PostTaxDeductions

sealed trait Final

// 为了简单起见，此处使用Float类型表示金额。不推荐大家这样做……
case class Employee(
                     name: String,
                     annualSalary: Float,
                     taxRate: Float, // 为了简化起见， 所有的税种税率相同。
                     insurancePremiumsPerPayPeriod: Float,
                     _401kDeductionRate: Float, // 税前扣除项，美国退休储蓄计划扣款。
                     postTaxDeductions: Float)

case class Pay[Step](employee: Employee, netPay: Float)

object Payroll {
  // 每两周发一次薪水。为了简化问题，我们认定每年正好有52周。
  def start(employee: Employee): Pay[PreTaxDeductions] = {
    Pay[PreTaxDeductions](employee, employee.annualSalary / 26.0F)
  }

  def minusInsurance(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - pay.employee.insurancePremiumsPerPayPeriod
    pay copy (netPay = newNet)
  }

  def minus401k(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
    val newNet = pay.netPay - (pay.employee._401kDeductionRate * pay.netPay)
    pay copy (netPay = newNet)
  }

  def minusTax(pay: Pay[PreTaxDeductions]): Pay[PostTaxDeductions] = {
    val newNet = pay.netPay - (pay.employee.taxRate * pay.netPay)
    pay copy (netPay = newNet)
  }

  def minusFinalDeductions(pay: Pay[PostTaxDeductions]): Pay[Final] = {

    val newNet = pay.netPay - pay.employee.postTaxDeductions
    pay copy (netPay = newNet)
  }
}

object CalculatePayroll {
  def main(args: Array[String]) = {
    val e = Employee("Buck Trends", 100000.0F, 0.25F, 200F, 0.10F, 0.05F)
    val pay1 = Payroll start e
    // 401K和保险扣除的顺序可以交换
    val pay2 = Payroll minus401k pay1
    val pay3 = Payroll minusInsurance pay2
    val pay4 = Payroll minusTax pay3
    val pay = Payroll minusFinalDeductions pay4
    val twoWeekGross = e.annualSalary / 26.0F
    val twoWeekNet = pay.netPay
    val percent = (twoWeekNet / twoWeekGross) * 100
    println(s"For ${e.name}, the gross vs. net pay every 2 weeks is:")
    println(
      f" $$${twoWeekGross}%.2f vs. $$${twoWeekNet}%.2f or ${percent}%.1f%%")
  }
}