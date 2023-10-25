// #Sireum
package devices

import org.sireum._
import architecture.LPConn
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
    return Potentiometer(pin)
  }
}
