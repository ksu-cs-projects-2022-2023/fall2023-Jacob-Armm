package bc.BuildingControl.device

import bc.BuildingControl.device.FirmataUtil.PinMode
import bc.BuildingControl.{FanAck, FanCmd, Util}
import org.sireum._

case class BoardPinOut(pwmPin: Z, ledPin: Z, analog0Pin: Z)

object DeviceBridge {
  val MEGA2560 = BoardPinOut(9, 13, 54) // https://www.electronicshub.org/arduino-mega-pinout/
  val UNO = BoardPinOut(9, 13, 14)

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

  def getCurrentTemp(): F32 = {
    init()

    // potentiometer connection is flaky so get average sampled reading
    var accum: Z = 0
    val numReads: Z = 100
    for (i <- 0 to numReads) {
      accum = accum + Board.analogRead(board.analog0Pin, PinMode.ANALOG)
    }

    // scale value so that it's within led range
    val ledScaled = map(accum / numReads, 0, 1033, 0, 255)

    // use pwm to display room temp: brighter == hotter
    Board.analogWrite(board.pwmPin, PinMode.PWM, ledScaled)

    // scale value so that it's within the absolute min/max of the sensor
    val tempScaled = map(ledScaled, 0, 255, minTempZ, maxTempZ)
    return org.sireum.F32(tempScaled.toInt * 1.0f)
  }

  def sendFanCmd(cmd: FanCmd.Type): FanAck.Type = {
    init()

    cmd match {
      case FanCmd.On =>
        Board.analogWrite(board.ledPin, PinMode.OUTPUT, 255)
      case FanCmd.Off =>
        Board.analogWrite(board.ledPin, PinMode.OUTPUT, 0)
    }

    return FanAck.Ok
  }

  def map(x: Z, in_min: Z, in_max: Z, out_min: Z, out_max: Z): Z = {
    return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min
  }
}
