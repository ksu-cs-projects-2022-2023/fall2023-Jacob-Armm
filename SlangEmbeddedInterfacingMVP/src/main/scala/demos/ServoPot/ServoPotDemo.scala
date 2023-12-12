// #Sireum
package demos.ServoPot

import org.sireum._
import platform.impl.PlatformImpl
import platform.LPConn
import devices._
import utils.Config
import utils.PinModeUtil.PinMode

object ServoPotDemo extends App {

  def main(args: ISZ[String]): Z = {

    val pin1 = Pin("potPin", PinMode.ANALOG)
    val pin2 = Pin("servoPin", PinMode.SERVO)

    val pinMap: Map[String, Z] = Map.empty[String, Z] ++ ISZ(
      "potPin" ~> 14,
      "servoPin" ~> 9
    )

    val config = Config(pinMap, implGetter.getImpl(pinMap), None())

    LPConn.init(config, ISZ(pin1, pin2))
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

@ext object implGetter {
  def getImpl(pinMap: Map[String, Z]): PlatformImpl = $
}