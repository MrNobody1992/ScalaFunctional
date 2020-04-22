import org.scalatest.{FlatSpec, Matchers}

class FibonacciTest
  extends FlatSpec
    with Matchers {

  private val fib = Fibonacci()

  "Scala Fibonacci methods" should "return the correct values" in {
    val inputs = List(0, 1, 10, 20, 30, 40)
    val outputs = List(0, 1, 55, 6765, 832040, 102334155)

    inputs.map(x => fib.fibonacci(x) -> fib.fibonacciLoop(x)).zip(outputs).foreach {
      case (results@_, expected) => results.toList should contain only expected
    }
  }

  they should "offer a faster execution for a functional approach" in {
    val inputs = List(100000, 1000000, 10000000, 100000000, 1000000000)

    inputs.foreach {
      input =>
        timeIt("Fibonacci loop", fib.fibonacciLoop(input))
        timeIt("Fibonacci tail recursion", fib.fibonacci(input))
        println
    }
  }

  // -------------------------------------------------------
  // -                        Utils                        -
  // -------------------------------------------------------

  private def timeIt[T](method: String, f: => T): T = {
    val start = System.currentTimeMillis()
    val result = f
    val end = System.currentTimeMillis()

    println(s"$method elapsed time: ${end - start} ms")

    result
  }

  private implicit def toList(tup: (Long, Long)): List[Long] = List(tup._1, tup._2)
}
