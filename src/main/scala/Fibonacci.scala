import scala.annotation.tailrec

/**
 * Fibonacci sequence with a functional language
 */
class Fibonacci {

  /**
   * Calculate the nth Fibonacci number using streams
   *
   * @param n number of the Fibonacci sequence element to return
   * @return nth Fibonacci number
   */
  def fibonacci(n: Long): Long = {

    @tailrec
    def fibTail(n: Long, first: Long, second: Long): Long = n match {
      case 0 => first
      case _ => fibTail(n - 1, second, first + second)
    }

    fibTail(n, 0, 1)
  }

  /**
   * Calculate the nth Fibonacci number using a for loop
   *
   * @param n number of the Fibonacci sequence element to return
   * @return nth Fibonacci number
   */
  def fibonacciLoop(n: Long): Long = {
    var result = n

    if (n > 1) {
      var curr = 1
      var prev = 1

      for (_ <- 2 until n.toInt) {
        val temp = curr
        curr += prev
        prev = temp
      }

      result = curr
    }

    result
  }
}

/**
 * Companion object
 */
object Fibonacci {

  def apply(): Fibonacci = new Fibonacci()
}
