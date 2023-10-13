package board.impl

import org.sireum._
import utils.PinModeUtil.PinMode

trait BoardImpl {
  def init(port: Option[String]): Unit

  def ready: B

  def read(pin: String, mode: PinMode.Type): Z

  def write(pin: String, mode: PinMode.Type, value: Z): Unit
}
