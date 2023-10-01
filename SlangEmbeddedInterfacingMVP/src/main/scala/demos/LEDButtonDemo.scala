// #Sireum
package demos

import org.sireum._
import board.Board
import devices._

object LEDButtonDemo extends App {
  def main(args: ISZ[String]): Z = {

    Board.init("COM3")
    val redLED = LED.createDevice("RedLed")
    val greenLED = LED.createDevice("GreenLed")
    val blueLED = LED.createDevice("BlueLed")
    val button = Button.createDevice("Button")

    if(Board.ready) {
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
}
