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
    assert(pin.mode == PinMode.PWM, "Invalid pinMode for LED")
    return Servo(pin)
  }
}
