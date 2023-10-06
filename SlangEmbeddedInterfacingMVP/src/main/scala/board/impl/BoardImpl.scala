package board.impl

import org.sireum._
import utils.FirmataUtil.PinMode

trait BoardImpl {
  def init(port: String): Unit

  def ready: B

  def read(pin: String, mode: PinMode.Type): Z

  def write(pin: String, mode: PinMode.Type, value: Z): Unit
}
