// #Sireum
package devices

import org.sireum._
import platform.LPConn
import utils.PinModeUtil._
import org.sireum.B

@record class Button(pin: Pin) {
  def isPressed: B = {
    return pin.read == 1
  }
}

object Button {
  def createDevice(pin: Pin): Button = {
    assert(pin.mode == PinMode.INPUT, "Invalid pinMode for Button")
    return Button(pin)
  }
}
