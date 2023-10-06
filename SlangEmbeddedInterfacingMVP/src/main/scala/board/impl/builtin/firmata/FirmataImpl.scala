package board.impl.builtin.firmata

import org.sireum._
import board.impl.BoardImpl
import org.firmata4j.firmata.FirmataDevice
import org.firmata4j.{IODevice, Pin}
import utils.FirmataUtil.PinMode

case class FirmataImpl(pinMap: Map[String, Z]) extends BoardImpl {
  var port: String = _
  var device: IODevice = _
  var initRun: B = F

  override def init(port: String): Unit = {
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

  override def ready: B = {
    return device.isReady
  }

  override def read(pin: String, mode: PinMode.Type): Z = {
    // for now just let exception halt the program
    val mappedPin = pinMap.get(pin)
    val p = device.getPin(mappedPin.get.toInt)
    p.setMode(Pin.Mode.valueOf(mode.name.native))
    return Z(p.getValue)
  }

  override def write(pin: String, mode: PinMode.Type, value: Z): Unit = {
    // for now just let exception halt the program
    val p = device.getPin(pinMap.get(pin).get.toInt)
    p.setMode(Pin.Mode.valueOf(mode.name.native))
    p.setValue(value.toInt)
  }
}
