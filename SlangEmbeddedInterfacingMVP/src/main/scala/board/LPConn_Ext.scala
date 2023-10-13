package board

import org.firmata4j._
import org.firmata4j.firmata._
import board.impl.BoardImpl
import board.impl.builtin.firmata._
import org.sireum._
import utils.PinModeUtil.PinMode

import java.util.concurrent.TimeUnit
object LPConn_Ext {

  // DO NOT UNCOMMENT
//  val pinMap: Map[String, Z] = Map.empty ++ ISZ(
//    string"RedLed" ~> z"13",
//    string"GreenLed" ~> z"12",
//    string"BlueLed" ~> z"11",
//    string"Button" ~> z"2"
//  )
  // DO NOT UNCOMMENT
//  val pinMap: Map[String, Z] = Map.empty ++ ISZ(
//    string"potPin" ~> z"14",
//    string"servoPin" ~> z"9"
//  )


  // Assignment 1
  val pinMap: Map[String, Z] = Map.empty ++ ISZ(
    string"BoardLed" ~> z"13"
  )

  //Assignment 2
//  val pinMap: Map[String, Z] = Map.empty ++ ISZ(
//    string"RedLed" ~> z"13",
//    string"Button" ~> z"2"
//  )

  //Assignment 3
//  val pinMap: Map[String, Z] = Map.empty ++ ISZ(
//    //Do It Yourself
//  )


  var boardImpl: BoardImpl = FirmataImpl(pinMap)

  def init(port: Option[String]): Unit = {
    boardImpl.init(port)
  }

  def ready: B = {
    boardImpl.ready
  }

  def read(pin: String, mode: PinMode.Type): Z = {
    boardImpl.read(pin, mode)
  }

  def write(pin: String, mode: PinMode.Type, value: Z): Unit = {
    boardImpl.write(pin, mode, value)
  }

  def holdWait(ms: Z): Unit = {
    TimeUnit.MILLISECONDS.sleep(ms.toLong)
  }
}
