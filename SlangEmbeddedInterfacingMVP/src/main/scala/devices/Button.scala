// #Sireum
package devices

import org.sireum._
import board.Board
import utils.FirmataUtil._
import org.sireum.B

@datatype class Button(pin: String) {
  def getState: DigitalState.Type = {
    val out = Board.analogRead(pin, PinMode.INPUT)
    return if (out == 1) DigitalState.On else DigitalState.Off
  }
}
