// #Sireum
package board

import org.sireum._
import utils.FirmataUtil.PinMode

@ext object LPConn {

  def init(port: String): Unit = $

  def ready: B = $

  def read(pin: String, mode: PinMode.Type): Z = $

  def write(pin: String, mode: PinMode.Type, value: Z): Unit = $
}
