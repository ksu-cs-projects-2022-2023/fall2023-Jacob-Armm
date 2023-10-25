// #Sireum
package devices

import org.sireum._
import architecture.LPConn
import utils.PinModeUtil._
import org.sireum.B

@record class Button(pin: Pin) {
  def isPressed: B = {
    return pin.read == 1
  }
}

object Button {
  def createDevice(pin: Pin): Button = {
    return Button(pin)
  }
}
