// #Sireum
package devices

import org.sireum._
import board.LPConn
import utils.FirmataUtil._
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
