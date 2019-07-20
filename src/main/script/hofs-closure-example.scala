
var factor = 2
val multiplier = (i: Int) => i * factor
var result = (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)

println(result)
factor = 3
result = (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)

println(result)

def factorial(i: BigInt): BigInt = {
  def loop(l: BigInt, init: BigInt): BigInt = {
    if (l == 1) init
    else loop(l-1, init * l)
  }
  loop(i, 1)
}

println(factorial(5))


val map = Map("key"->"value")
