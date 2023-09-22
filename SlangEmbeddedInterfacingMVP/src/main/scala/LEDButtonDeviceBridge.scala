// #Sireum

import org.sireum._
import FirmataUtil._

object LEDButtonDeviceBridge {

  def init(port: String): Unit = {
    Board.init(port)
  }

  def ready(): B = {
    return Board.ready
  }

  def getButton: B = {
    val out = Board.analogRead("Button", PinMode.INPUT)
    return out == 1
  }

  def setRed(cmd: LEDMode.Type): Unit = {
    cmd match {
      case LEDMode.On =>
        Board.analogWrite("RedLed", PinMode.OUTPUT, 255)
      case LEDMode.Off =>
        Board.analogWrite("RedLed", PinMode.OUTPUT, 0)
    }
  }

  def setGreen(cmd: LEDMode.Type): Unit = {
    cmd match {
      case LEDMode.On =>
        Board.analogWrite("GreenLed", PinMode.OUTPUT, 255)
      case LEDMode.Off =>
        Board.analogWrite("GreenLed", PinMode.OUTPUT, 0)
    }
  }

  def setBlue(cmd: LEDMode.Type): Unit = {
    cmd match {
      case LEDMode.On =>
        Board.analogWrite("BluePin", PinMode.OUTPUT, 255)
      case LEDMode.Off =>
        Board.analogWrite("BluePin", PinMode.OUTPUT, 0)
    }
  }
}
