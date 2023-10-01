// #Sireum
package demos

import org.sireum._
import board.Board
import devices._

object LEDPWMDemo extends App {
  def main(args: ISZ[String]): Z = {
    Board.init("COM3")
    val rLED = LEDPWM.createDevice("RedLed")
    val gLED = LEDPWM.createDevice("GreenLed")
    val bLED = LEDPWM.createDevice("BlueLed")
    val button = Button.createDevice("Button")

    if(Board.ready) {

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
