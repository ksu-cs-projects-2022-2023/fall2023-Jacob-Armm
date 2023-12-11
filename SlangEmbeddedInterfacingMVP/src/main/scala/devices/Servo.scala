// #Sireum
package devices

import org.sireum._
import platform.LPConn
import utils.PinModeUtil._

@record class Servo(pin: Pin) {
  def setServoPos(pos: Z): Unit = {
    pin.write(pos)
  }
}

object Servo{
  def createDevice(pin: Pin): Servo = {
    assert(pin.mode == PinMode.SERVO, "Invalid pinMode for Servo")
    return Servo(pin)
  }
}
