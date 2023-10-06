// #Sireum
package devices

import org.sireum._
import board.LPConn
import utils.FirmataUtil._


@record class Potentiometer(pin: Pin) {
  def getPotValue: Z = {
    def map(x: Z, in_min: Z, in_max: Z, out_min: Z, out_max: Z): Z = {
      return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min
    }

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
