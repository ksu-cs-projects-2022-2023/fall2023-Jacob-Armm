//package scala
//
//import org.sireum._
//
//import FirmataUtil._
//
//case class ServoPotBoardPinOut(rotorPin: Z, potPin: Z)
//
//object ServoPotDeviceBridge {
//  //val MEGA2560 = DemoBoardPinOut(9, 2, 13, 12, 11) // https://www.electronicshub.org/arduino-mega-pinout/
//  val UNO = ServoPotBoardPinOut(9, 14)
//
//  val board = UNO
//  val port: String = "COM4"
//
//  def init(): Unit = {
//    Board.init(port)
//  }
//
//  def ready: B = {
//    init()
//    return Board.ready
//  }
//  def getPotScaledValue(): Z = {
//    init()
//
//    // potentiometer connection is flaky so get average sampled reading
//    var accum: Z = 0
//    val numReads: Z = 100
//    for (i <- 0 to numReads) {
//      accum = accum + Board.analogRead(board.potPin, PinMode.ANALOG)
//    }
//
//    val n = map((accum/numReads), 0, 1023, 0, 180)
//    return n
//  }
//
//  def setServoPos(pos: Z): Unit = {
//    Board.analogWrite(board.rotorPin, PinMode.SERVO, pos)
//  }
//
//  def map(x: Z, in_min: Z, in_max: Z, out_min: Z, out_max: Z): Z = {
//    return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min
//  }
//}