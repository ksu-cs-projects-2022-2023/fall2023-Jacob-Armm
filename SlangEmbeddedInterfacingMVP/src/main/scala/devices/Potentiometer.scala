// #Sireum
package devices

import org.sireum._
import platform.LPConn
import utils.PinModeUtil._


@record class Potentiometer(pin: Pin) {
  def getPotValue: Z = {

    var accum: Z = 0
    val numReads: Z = 100
    for (i <- 0 to numReads) {
      accum = accum + pin.read
    }

    return accum/numReads
  }
}

object Potentiometer{
  def createDevice(pin: Pin): Potentiometer = {
    assert(pin.mode == PinMode.ANALOG, "Invalid pinMode for LED")
    return Potentiometer(pin)
  }
}
