// #Sireum
package demos.WaitDemo

import org.sireum._
import platform.impl.PlatformImpl
import utils.PinModeUtil.PinMode
import utils.{Config, Wait}
import platform.LPConn
import devices._

object  WaitDemo extends App {
  def main(args: ISZ[String]): Z = {

    val pin1 = Pin("RedLed", PinMode.OUTPUT)

    val pinMap = Map.empty ++ ISZ(
      pin1.pinAlias ~> 1
    )

    val conf = Config(pinMap, implGetter.getImpl(pinMap), None())

    LPConn.init(conf, ISZ(pin1))
    val redLED = LED.createDevice(pin1)

    if(LPConn.ready) {

      while(true) {
        redLED.on()
        Wait.waitInMS(1000)
        redLED.off()
        Wait.waitInMS(1000)
      }
    }
    return 0
  }

  @ext object implGetter {
    def getImpl(pinMap: Map[String, Z]): PlatformImpl = $
  }
}