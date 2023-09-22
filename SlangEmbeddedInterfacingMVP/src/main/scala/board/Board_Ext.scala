package board

import org.firmata4j._
import org.firmata4j.firmata._
import org.sireum._
import utils.FirmataUtil.PinMode
object Board_Ext {
  var port: String = _
  var device: IODevice = _
  var initRun: B = F

  // Make map to pins

  val pinMap: scala.collection.Map[java.lang.String, Int] = scala.collection.Map(
    "RedLed" -> 13,
    "GreenLed" -> 12,
    "BlueLed" -> 11,
    "Button" -> 2
  )

  def init(port: String): Unit = {
    this.synchronized {
      try {
        if (!initRun) {
          initRun = T
          device = new FirmataDevice(port.native)
          device.start()
          device.ensureInitializationIsDone()
        }
      } catch {
        case e: Throwable =>
          cprintln(T, e)
      }
    }
  }

  def ready: B = {
    return device.isReady
  }

  def analogRead(pin: String, mode: PinMode.Type): Z = {
    // for now just let exception halt the program
    val mappedPin = pinMap(pin.native)
    val p = device.getPin(mappedPin)
    p.setMode(Pin.Mode.valueOf(mode.name.native))
    return Z(p.getValue)
  }

  def analogWrite(pin: String, mode: PinMode.Type, value: Z): Unit = {
    // for now just let exception halt the program
    val p = device.getPin(pinMap(pin.native))
    p.setMode(Pin.Mode.valueOf(mode.name.native))
    p.setValue(value.toInt)
  }
}
