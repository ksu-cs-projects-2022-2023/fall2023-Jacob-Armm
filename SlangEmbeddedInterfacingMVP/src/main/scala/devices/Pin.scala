// #Sireum
package devices

import org.sireum._
import platform.LPConn
import utils.PinModeUtil.PinMode

@datatype class Pin(pinAlias: String, mode: PinMode.Type) {
  def read: Z = {
    return LPConn.read(pinAlias, mode)
  }

  def write(value: Z): Unit = {
    LPConn.write(pinAlias, mode, value)
  }
}
