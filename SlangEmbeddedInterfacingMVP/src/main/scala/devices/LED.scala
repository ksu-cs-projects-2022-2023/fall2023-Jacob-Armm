// #Sireum
package devices

import org.sireum._
import board.Board
import utils.FirmataUtil.PinMode

@record class LED(pin: String) {
  def on(): Unit = {
    Board.write(pin, PinMode.OUTPUT, 1)
  }

  def off(): Unit = {
    Board.write(pin, PinMode.OUTPUT, 0)
  }
}

object LED {
  def createDevice(pin: String): LED = {
    assert(Board.pinExist(pin), s"Pin Alias $pin does not exist")
    assert(Board.pinModeCheck(pin, PinMode.OUTPUT), s"Invalid pin mode")

    return LED(pin)
  }
}
