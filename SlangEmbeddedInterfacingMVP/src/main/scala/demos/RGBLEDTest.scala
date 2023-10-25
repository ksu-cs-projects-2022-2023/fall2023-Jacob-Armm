// #Sireum
package demos

import org.sireum._
import architecture.LPConn
import devices._
import utils.PinModeUtil.PinMode

object RGBLEDTest extends App {
  def main(args: ISZ[String]): Z = {
    val pinR = Pin("RedChannel", PinMode.PWM)
    val pinG = Pin("GreenChannel", PinMode.PWM)
    val pinB = Pin("BlueChannel", PinMode.PWM)

    //LPConn.init(None(), ISZ(pinR, pinG, pinB))

    val rgb = RGBLED.createDevice(pinR, pinG, pinB)

    if(LPConn.ready) {

      var red: Z = 60
      var green: Z = 120
      var blue: Z = 0
      var redIncreasing: B = F
      var greenIncreasing: B = F
      var blueIncreasing: B = T

      while(true) {
        if (redIncreasing) {
          red = red + 1
          redIncreasing = red != 120
        } else {
          red = red - 1
          redIncreasing = red == 0
        }
        if (greenIncreasing) {
          green = green + 1
          greenIncreasing = green != 120
        } else {
          green = green - 1
          greenIncreasing = green == 0
        }
        if (blueIncreasing) {
          blue = blue + 1
          blueIncreasing = blue != 120
        } else {
          blue = blue - 1
          blueIncreasing = blue == 0
        }

        LPConn.holdWait(5)
        rgb.setColor(red, green, blue)

      }
    }

    return 0
  }
}
