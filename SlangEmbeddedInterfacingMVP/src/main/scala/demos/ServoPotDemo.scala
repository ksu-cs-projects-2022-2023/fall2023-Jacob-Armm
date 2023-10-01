// #Sireum
package demos

import org.sireum._
import board.Board
import devices._

object ServoPotDemo extends App {

  def main(args: ISZ[String]): Z = {
    Board.init("COM4")
    val pot = Potentiometer.createDevice("PotPin")
    val servo = Servo.createDevice("ServoPin")

    if(Board.ready) {
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
