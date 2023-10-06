// #Sireum
package demos

import org.sireum._
import board.LPConn
import devices._
import utils.FirmataUtil.PinMode

object ServoPotDemo extends App {

  def main(args: ISZ[String]): Z = {

    val pin1 = Pin("potPin", PinMode.ANALOG)
    val pin2 = Pin("servoPin", PinMode.SERVO)

    LPConn.init("/dev/ttyACM0")
    val pot = Potentiometer.createDevice(pin1)
    val servo = Servo.createDevice(pin2)

    if(LPConn.ready) {
      while(T) {
        servo.setServoPos(map(pot.getPotValue, 0, 1023, 0, 180))
      }
    }

    assert(F, "Board was not ready")

    return 0
  }

  def map(x: Z, in_min: Z, in_max: Z, out_min: Z, out_max: Z): Z = {
    return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min
  }
}
