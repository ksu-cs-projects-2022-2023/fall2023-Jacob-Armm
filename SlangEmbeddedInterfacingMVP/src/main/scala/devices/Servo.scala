// #Sireum
package devices

import org.sireum._
import board.LPConn
import utils.FirmataUtil._

@record class Servo(pin: Pin) {
  def setServoPos(pos: Z): Unit = {
    pin.write(pos)
  }
}

object Servo{
  def createDevice(pin: Pin): Servo = {
    return Servo(pin)
  }
}
