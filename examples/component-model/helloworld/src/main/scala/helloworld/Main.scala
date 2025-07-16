package helloworld

import scala.scalajs.component._
import scala.scalajs.component.annotation._
import scala.collection.mutable.ArrayBuffer

object Main {
  @ComponentExport("wasi:cli/run@0.2.0", "run")
  def main(): Result[Unit, Unit] = {
    val str = "α".repeat(100)
    val iterations = 30
    val warmup = 10
    val times = new ArrayBuffer[Long](iterations)

    for (_ <- 1 to warmup) {
      val start = System.nanoTime()
      val _ = greet(str)
      val end = System.nanoTime()
    }

    for (_ <- 1 to iterations) {
      val start = System.nanoTime()
      val _ = greet2(str)
      val end = System.nanoTime()
      times += (end - start)
    }

    val average = (times.sum.toDouble / iterations)
    val variance = times.map(t => (t - average.toLong) * (t - average.toLong)).sum / iterations

    println(s"Average time: ${average.toLong} ns")
    println(s"Variance: ${variance.toLong} ns²")

    new Ok(())
  }

  @ComponentImport("component:scala/greeting", "greet")
  def greet(a: String): String = native

  def greet2(a: String): String = a
}
