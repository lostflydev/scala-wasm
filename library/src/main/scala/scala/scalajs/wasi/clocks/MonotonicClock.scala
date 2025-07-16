package scala.scalajs.wasi.clocks

import scala.scalajs.component.annotation._
import scala.scalajs.component.unsigned._
import scala.scalajs.{component => cm}

object MonotonicClock {
  @ComponentImport("wasi:clocks/monotonic-clock@0.2.0", "now")
  def now(): ULong = cm.native
}
