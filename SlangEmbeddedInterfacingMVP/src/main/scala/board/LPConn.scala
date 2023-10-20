// #Sireum
package board

import org.sireum._
import utils.PinModeUtil.PinMode
import devices.Pin

@ext object LPConn {

  def init(port: Option[String], numRequiredPins: Z, logicalPins: ISZ[Pin]): Unit = $

  def ready: B = $

  def read(pin: String, mode: PinMode.Type): Z = $

  def write(pin: String, mode: PinMode.Type, value: Z): Unit = $

  def holdWait(ms: Z): Unit = $
}
