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
