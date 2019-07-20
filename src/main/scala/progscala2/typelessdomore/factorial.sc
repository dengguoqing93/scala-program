def factorial(int: Int): Long = {
  def fac(i: Int, accumulator: Int): Long = {
    if (i <= 1) {
      accumulator
    } else {
      fac(i - 1, accumulator * i)
    }
  }

  fac(int, 1)
}

(0 to 5) foreach (i => println(factorial(i)))
