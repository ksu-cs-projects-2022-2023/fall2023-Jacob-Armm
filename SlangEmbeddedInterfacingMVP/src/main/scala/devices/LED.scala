// #Sireum
package devices

import org.sireum._
import board.LPConn
import utils.PinModeUtil.PinMode

@record class LED(pin: Pin) {
  def on(): Unit = {
    pin.write(1)
  }

  def off(): Unit = {
    pin.write(0)
  }
}

object LED {
  def createDevice(pin: Pin): LED = {
    return LED(pin)
  }
}
