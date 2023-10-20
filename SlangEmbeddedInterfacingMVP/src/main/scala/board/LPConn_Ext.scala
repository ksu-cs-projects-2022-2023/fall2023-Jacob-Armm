package board

import org.firmata4j._
import org.firmata4j.firmata._
import board.impl.BoardImpl
import board.impl.builtin.firmata._
import devices.Pin
import org.sireum._
import utils.PinModeUtil.PinMode

import java.util.concurrent.TimeUnit
object LPConn_Ext {

   //DO NOT UNCOMMENT
  val pinMap: Map[String, Z] = Map.empty ++ ISZ(
    string"RedLed" ~> z"13",
    string"GreenLed" ~> z"12",
    string"BlueLed" ~> z"11",
    string"Button" ~> z"2"
  )

  var boardImpl: BoardImpl = FirmataImpl(pinMap)

  def init(port: Option[String], logicalPins: ISZ[Pin]): Unit = {
    boardImpl.init(port)

    val physicalPins = boardImpl.retievePinList

    for(pin <- logicalPins){
      assert(ops.ISZOps(pinMap.keys).contains(pin.pinAlias), s"PinAlias ${pin.pinAlias} does not exist in pin map")
    }

    for(pin <- pinMap.keys){
      assert(ops.ISZOps(logicalPins).exists(p => p.pinAlias == pin), s"${pin} is not a logically defined pin")
    }

    for (pinNum <- pinMap.values) {
      assert(ops.ISZOps(physicalPins.keys).contains(pinNum), s"Pin $pinNum does not exist for this implementation")
    }

    for(pin <- logicalPins) {
      assert(ops.ISZOps(physicalPins.get(pinMap.get(pin.pinAlias).get).get).contains(pin.mode), s"\nPinMode Map Mismatch: pinMode ${pin.mode} is an invalid pinMode for ${pinMap.get(pin.pinAlias).get} which can only accept ${physicalPins.get(pinMap.get(pin.pinAlias).get).get}")
    }
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
