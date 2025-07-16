package plugin

import scala.scalajs.component._
import scala.scalajs.component.annotation._

object Plugin {
  @ComponentExport("component:scala/greeting", "greet")
  def greet(a: String): String = a
}