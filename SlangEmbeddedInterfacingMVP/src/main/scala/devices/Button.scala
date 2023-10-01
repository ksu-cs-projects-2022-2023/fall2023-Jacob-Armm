// #Sireum
package devices

import org.sireum._
import board.Board
import utils.FirmataUtil._
import org.sireum.B

@record class Button(pin: String) {
  def isPressed: B = {
    return Board.read(pin, PinMode.INPUT) == 1
  }
}

object Button {
  def createDevice(pin: String): Button = {
    assert(Board.pinExist(pin), s"Pin Alias $pin does not exist")
    assert(Board.pinModeCheck(pin, PinMode.INPUT), s"Invalid pin mode")
    return Button(pin)
  }
}
