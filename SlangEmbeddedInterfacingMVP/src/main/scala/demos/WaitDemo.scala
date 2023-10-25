// #Sireum
package demos

import org.sireum._
import architecture.LPConn
import devices._
import utils.PinModeUtil.PinMode

object  WaitDemo extends App {
  def main(args: ISZ[String]): Z = {

    val pin1 = Pin("RedLed", PinMode.OUTPUT)

//    LPConn.init(None())
    val redLED = LED.createDevice(pin1)

    if(LPConn.ready) {

      while(true) {
        redLED.on()
        LPConn.holdWait(1000)
        redLED.off()
        LPConn.holdWait(1000)
      }
    }
    return 0
  }
}