// #Sireum
package devices

import org.sireum._
import board.Board
import utils.FirmataUtil.{DigitalState, PinMode}

@datatype class LED(pin: String) {
  def setStateDigital(cmd: DigitalState.Type): Unit = {
    cmd match {
      case DigitalState.On =>
        Board.analogWrite(pin, PinMode.OUTPUT, 255)
      case DigitalState.Off =>
        Board.analogWrite(pin, PinMode.OUTPUT, 0)
    }
  }

  def setStatePWM(freq: Z): Unit = {
    assert(freq >= 0 && freq <= 255)
    Board.analogWrite(pin, PinMode.PWM, freq)
  }
}
