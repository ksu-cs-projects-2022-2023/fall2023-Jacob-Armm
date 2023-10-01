// #Sireum
package devices

import org.sireum._
import board.Board
import utils.FirmataUtil._
import org.sireum.B

@record class Button(pin: String) {

  def getState: DigitalState.Type = {
    val out = Board.read(pin, PinMode.INPUT)
    return if (out == 1) DigitalState.On else DigitalState.Off
  }
}
