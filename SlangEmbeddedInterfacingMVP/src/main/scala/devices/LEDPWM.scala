// #Sireum
package devices

import org.sireum._
import platform.LPConn
import utils.PinModeUtil.PinMode

@record class LEDPWM(pin: Pin) {
  def setValue(v: Z): Unit = {
    pin.write(v)
  }
}

object LEDPWM {
  def createDevice(pin: Pin): LEDPWM = {
    assert(pin.mode == PinMode.PWM, "Invalid pinMode for LED")
    return LEDPWM(pin)
  }
}
