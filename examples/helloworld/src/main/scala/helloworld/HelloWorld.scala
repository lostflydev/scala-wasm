/* Scala.js example code
 * Public domain
 * @author  Sébastien Doeraene
 */

package helloworld

import scala.scalajs.js
import js.annotation._

object HelloWorld {
  sealed abstract class Tree[+T] {
    def insert[U >: T](value: U)(implicit ord: Ordering[U]): Tree[U]
  }

  case class Node[+T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T] {
    def insert[U >: T](newValue: U)(implicit ord: Ordering[U]): Tree[U] = {
      if (ord.lt(newValue, value)) {
        Node(value, left.insert(newValue), right)
      } else if (ord.gt(newValue, value)) {
        Node(value, left, right.insert(newValue))
      } else {
        this
      }
    }
  }

  case object Empty extends Tree[Nothing] {
    def insert[U >: Nothing](value: U)(implicit ord: Ordering[U]): Tree[U] = Node(value, Empty, Empty)
  }

  def main(args: Array[String]): Unit = {
    val height = 100
    println(s"Constructing a tree of height $height.")

    val start = System.nanoTime()
    val tree = (1 to height).foldLeft(Empty: Tree[Int])((t, i) => t.insert(i))
    val end = System.nanoTime()

    val durationMs = (end - start) / 1000000
    println(s"Tree construction took: $durationMs ms")
  }
}
