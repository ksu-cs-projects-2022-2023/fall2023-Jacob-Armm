// #Sireum
package devices

import org.sireum._
import board.LPConn
import utils.FirmataUtil.PinMode

@record class Pin(pinAlias: String, mode: PinMode.Type) {
  def read: Z = {
    return LPConn.read(pinAlias, mode)
  }

  def write(value: Z): Unit = {
    LPConn.write(pinAlias, mode, value)
  }
}
