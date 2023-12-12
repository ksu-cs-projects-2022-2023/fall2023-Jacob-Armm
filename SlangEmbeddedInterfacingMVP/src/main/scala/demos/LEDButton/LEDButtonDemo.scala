// #Sireum
package demos.LEDButton

import org.sireum._
import platform.LPConn
import devices._
import metaconfig.Conf
import platform.impl.PlatformImpl
import utils.Config
import utils.PinModeUtil.PinMode

object  LEDButtonDemo extends App {
  def main(args: ISZ[String]): Z = {

    val pin1 = Pin("RedLed", PinMode.OUTPUT)
    val pin2 = Pin("GreenLed", PinMode.OUTPUT)
    val pin3 = Pin("BlueLed", PinMode.OUTPUT)
    val pin4 = Pin("Button", PinMode.INPUT)

    val pinMap = Map.empty ++ ISZ(
      pin1.pinAlias ~> 13,
      pin2.pinAlias ~> 12,
      pin3.pinAlias ~> 11,
      pin4.pinAlias ~> 2
    )

    val conf: Config = Config(pinMap, implGetter.getImpl(pinMap), None())

    LPConn.init(conf, ISZ(pin1, pin2, pin3, pin4))
    val redLED = LED.createDevice(pin1)
    val greenLED = LED.createDevice(pin2)
    val blueLED = LED.createDevice(pin3)
    val button = Button.createDevice(pin4)

    if(LPConn.ready) {
      var buttonState: Z = 0

      while(true) {

        if(button.isPressed) {
          while (button.isPressed) { }
          if (buttonState < 2) {
            buttonState = buttonState + 1
          }
          else {
            buttonState = 0
          }
        }

        if (buttonState == 0) {
          redLED.on()
          blueLED.off()
          greenLED.off()
        }

        if (buttonState == 1) {
          redLED.off()
          blueLED.off()
          greenLED.on()
        }

        if (buttonState == 2) {
          redLED.off()
          blueLED.on()
          greenLED.off()
        }
      }
    }
    return 0
  }

  @ext object implGetter {
    def getImpl(pinMap: Map[String, Z]): PlatformImpl = $
  }
}
