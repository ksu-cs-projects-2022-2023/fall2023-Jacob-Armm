// #Sireum
package devices

import org.sireum._
import board.Board
import utils.FirmataUtil._

@record class Servo(pin: String) {
  def setServoPos(pos: Z): Unit = {
    Board.write(pin, PinMode.SERVO, pos)
  }
}

object Servo{
  def createDevice(pin: String): Servo = {
    assert(Board.pinExist(pin), s"Pin Alias $pin does not exist")
    assert(Board.pinModeCheck(pin, PinMode.SERVO), s"Invalid pin mode")

    return Servo(pin)
  }
}
