// #Sireum
package architecture.impl

import org.sireum._
import utils.PinModeUtil.PinMode


@msig trait ArchImpl {
  def init(port: Option[String]): Unit

  def retievePinList: Map[Z, ISZ[PinMode.Type]]

  def ready: B

  def read(pin: String, mode: PinMode.Type): Z

  def write(pin: String, mode: PinMode.Type, value: Z): Unit
}
