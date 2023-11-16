// #Sireum
package demos.LEDPWM

import org.sireum._
import platform.LPConn
import devices._
import utils.PinModeUtil.PinMode

object LEDPWMDemo extends App {
  def main(args: ISZ[String]): Z = {
    val pin1 = Pin("RedLed", PinMode.PWM)
    val pin2 = Pin("GreenLed", PinMode.PWM)
    val pin3 = Pin("BlueLed", PinMode.PWM)
    val pin4 = Pin("Button", PinMode.INPUT)

//    LPConn.init(None())

    val rLED = LEDPWM.createDevice(pin1)
    val gLED = LEDPWM.createDevice(pin2)
    val bLED = LEDPWM.createDevice(pin3)
    val button = Button.createDevice(pin4)

    if(LPConn.ready) {

      var buttonState = 0
      while(T) {
        if(button.isPressed) {
          while(button.isPressed) {}

          buttonState = buttonState + 50
          if(buttonState > 250) {
            buttonState = 0
          }
        }

        rLED.setValue(buttonState)
        gLED.setValue(buttonState)
        bLED.setValue(buttonState)
      }
    }

    return 0
  }
}
