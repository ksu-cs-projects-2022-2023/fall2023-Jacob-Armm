// #Sireum
package devices

import org.sireum._
import utils.PinModeUtil.PinMode

@record class RGBLED(pinR: Pin, pinG: Pin, pinB: Pin) {
  def setColor(r: Z, g: Z, b: Z): Unit = {
    pinR.write(r)
    pinG.write(g)
    pinB.write(b)
  }
}

object RGBLED {
  def createDevice(pinR: Pin, pinG: Pin, pinB: Pin): RGBLED = {
    assert(pinR.mode == PinMode.PWM, "Invalid pinMode for Red Channel")
    assert(pinG.mode == PinMode.PWM, "Invalid pinMode for Green Channel")
    assert(pinB.mode == PinMode.PWM, "Invalid pinMode for Blue Channel")
    return RGBLED(pinR, pinG, pinB)
  }
}
