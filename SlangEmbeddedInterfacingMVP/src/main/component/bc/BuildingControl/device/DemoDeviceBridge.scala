package bc.BuildingControl.device

import bc.BuildingControl.device.FirmataUtil.PinMode
import bc.BuildingControl.{FanAck, FanCmd, Util}
import org.sireum._

case class DemoBoardPinOut(pwmPin: Z, buttonPin: Z, redPin: Z, greenPin: Z, bluePin: Z)

object DemoDeviceBridge {
  //val MEGA2560 = BoardPinOut(9, 13, 54) // https://www.electronicshub.org/arduino-mega-pinout/
  val UNO = DemoBoardPinOut(9, 2, 13, 12, 11)

  val board = UNO
  val port: String = "/dev/ttyACM0"

  val minTempZ: Z = Z(Util.minTemp.value.toInt)
  val maxTempZ: Z = Z(Util.maxTemp.value.toInt)

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

  def setRed(cmd: FanCmd.Type): FanAck.Type = {
    init()
    cmd match {
      case FanCmd.On =>
        Board.analogWrite(board.redPin, PinMode.OUTPUT, 255)
      case FanCmd.Off =>
        Board.analogWrite(board.redPin, PinMode.OUTPUT, 0)
    }

    return FanAck.Ok
  }

  def setGreen(cmd: FanCmd.Type): FanAck.Type = {
    init()
    cmd match {
      case FanCmd.On =>
        Board.analogWrite(board.greenPin, PinMode.OUTPUT, 255)
      case FanCmd.Off =>
        Board.analogWrite(board.greenPin, PinMode.OUTPUT, 0)
    }

    return FanAck.Ok
  }

  def setBlue(cmd: FanCmd.Type): FanAck.Type = {
    init()
    cmd match {
      case FanCmd.On =>
        Board.analogWrite(board.bluePin, PinMode.OUTPUT, 255)
      case FanCmd.Off =>
        Board.analogWrite(board.bluePin, PinMode.OUTPUT, 0)
    }

    return FanAck.Ok
  }
}
