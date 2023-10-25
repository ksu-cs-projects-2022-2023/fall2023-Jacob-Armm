// #Sireum
package devices

import org.sireum._
import architecture.LPConn
import utils.PinModeUtil.PinMode

@record class LEDPWM(pin: Pin) {
  def setValue(v: Z): Unit = {
    pin.write(v)
  }
}

object LEDPWM {
  def createDevice(pin: Pin): LEDPWM = {
    return LEDPWM(pin)
  }
}
