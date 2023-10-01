// #Sireum
package devices

import org.sireum._
import board.Board
import utils.FirmataUtil.PinMode

@record class LEDPWM(pin: String) {
  def setValue(v: Z): Unit = {
    Board.write(pin, PinMode.PWM, v)
  }
}

object LEDPWM {
  def createDevice(pin: String): LEDPWM = {
    assert(Board.pinExist(pin), s"Pin Alias $pin does not exist")
    assert(Board.pinModeCheck(pin, PinMode.OUTPUT), s"Invalid pin mode")

    return LEDPWM(pin)
  }
}
