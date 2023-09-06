package scala

import org.sireum._
import FirmataUtil._

case class DemoBoardPinOut(pwmPin: Z, buttonPin: Z, redPin: Z, greenPin: Z, bluePin: Z)

object DemoDeviceBridge {
  //val MEGA2560 = BoardPinOut(9, 13, 54) // https://www.electronicshub.org/arduino-mega-pinout/
  val UNO = DemoBoardPinOut(9, 2, 13, 12, 11)

  val board = UNO
  val port: String = "/dev/ttyACM0"

  def init(): Unit = {
    Board.init(port)
  }

  def ready: B = {
    init()
    return Board.ready
  }

  def getButton: B = {
    init()
    val out = Board.analogRead(board.buttonPin, PinMode.INPUT)
    return out == 1
  }

  def setRed(cmd: LEDMode.Type): Unit = {
    init()
    cmd match {
      case LEDMode.On =>
        Board.analogWrite(board.redPin, PinMode.OUTPUT, 255)
      case LEDMode.Off =>
        Board.analogWrite(board.redPin, PinMode.OUTPUT, 0)
    }
  }

  def setGreen(cmd: LEDMode.Type): Unit = {
    init()
    cmd match {
      case LEDMode.On =>
        Board.analogWrite(board.greenPin, PinMode.OUTPUT, 255)
      case LEDMode.Off =>
        Board.analogWrite(board.greenPin, PinMode.OUTPUT, 0)
    }
  }

  def setBlue(cmd: LEDMode.Type): Unit = {
    init()
    cmd match {
      case LEDMode.On =>
        Board.analogWrite(board.bluePin, PinMode.OUTPUT, 255)
      case LEDMode.Off =>
        Board.analogWrite(board.bluePin, PinMode.OUTPUT, 0)
    }
  }
}
