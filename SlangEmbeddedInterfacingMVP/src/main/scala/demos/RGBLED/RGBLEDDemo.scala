// #Sireum
package demos.RGBLED

import org.sireum._
import platform._
import platform.impl._
import devices._
import utils.PinModeUtil.PinMode
import utils.{Config, Wait}

object RGBLEDDemo extends App {
  def main(args: ISZ[String]): Z = {
    val pinR = Pin("RedChannel", PinMode.PWM)
    val pinG = Pin("GreenChannel", PinMode.PWM)
    val pinB = Pin("BlueChannel", PinMode.PWM)

    val pinMap: Map[String, Z] = Map.empty[String, Z] ++ ISZ(
      "RedChannel" ~> 9,
      "GreenChannel" ~> 10,
      "BlueChannel" ~> 11
    )

    val config = Config(pinMap, implGetter.getImpl(pinMap), None())

    LPConn.init(config, ISZ(pinR, pinG, pinB))

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
          redIncreasing = red != 255
        } else {
          red = red - 1
          redIncreasing = red == 0
        }
        if (greenIncreasing) {
          green = green + 1
          greenIncreasing = green != 255
        } else {
          green = green - 1
          greenIncreasing = green == 0
        }
        if (blueIncreasing) {
          blue = blue + 1
          blueIncreasing = blue != 255
        } else {
          blue = blue - 1
          blueIncreasing = blue == 0
        }

        rgb.setColor(red, green, blue)
        Wait.waitInMS(5)

      }
    }

    return 0
  }

  @ext object implGetter {
    def getImpl(pinMap: Map[String, Z]): PlatformImpl = $
  }
}
