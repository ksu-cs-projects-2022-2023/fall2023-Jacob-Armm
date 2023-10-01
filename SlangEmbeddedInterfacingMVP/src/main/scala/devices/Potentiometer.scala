// #Sireum
package devices

import org.sireum._
import board.Board
import utils.FirmataUtil._


@record class Potentiometer(pin: String) {
  def getPotValue: Z = {
    def map(x: Z, in_min: Z, in_max: Z, out_min: Z, out_max: Z): Z = {
      return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min
    }

    var accum: Z = 0
    val numReads: Z = 100
    for (i <- 0 to numReads) {
      accum = accum + Board.read(pin, PinMode.ANALOG)
    }

    return accum/numReads
  }
}

object Potentiometer{
  def createDevice(pin: String): Potentiometer = {
    assert(Board.pinExist(pin), s"Pin Alias $pin does not exist")
    assert(Board.pinModeCheck(pin, PinMode.ANALOG), s"Invalid pin mode")

    return Potentiometer(pin)
  }
}
