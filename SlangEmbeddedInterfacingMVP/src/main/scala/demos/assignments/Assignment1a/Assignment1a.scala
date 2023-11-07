// #Sireum
package demos.assignments.Assignment1a

import devices.{LED, Pin}
import org.sireum._
import utils.PinModeUtil._

object Assignment1a extends App {
  override def main(args: ISZ[String]): Z = {
    val boardPin: Pin = Pin("BoardLed", PinMode.OUTPUT)
    //LPConn.init(None())
    val boardLed: LED = LED.createDevice(boardPin)

    while(true) {
      // TODO: Write A State Machine that flashes the on-board led every 3 seconds ( LPConn.holdWait(ms: Z) )
    }

    return 0
  }
}
