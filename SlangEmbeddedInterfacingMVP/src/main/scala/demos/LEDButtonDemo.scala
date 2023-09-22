// #Sireum
package demos

import org.sireum._
import utils.FirmataUtil._
import board.Board
import devices._
import utils.FirmataUtil

object LEDButtonDemo extends App {
  def main(args: ISZ[String]): Z = {
    Board.init("COM5")
    val redLED = LED("RedLed")
    val greenLED = LED("GreenLed")
    val blueLED = LED("BlueLed")
    val button = Button("Button")

    if(Board.ready) {
      var buttonState: Z = 0

      while(true) {

        if(button.getState == DigitalState.On) {
          while (button.getState == DigitalState.On) { }
          if (buttonState < 2) {
            buttonState = buttonState + 1
          }
          else {
            buttonState = 0
          }
        }

        if (buttonState == 0) {
          redLED.setStateDigital(DigitalState.On)
          blueLED.setStateDigital(DigitalState.Off)
          greenLED.setStateDigital(DigitalState.Off)
        }

        if (buttonState == 1) {
          redLED.setStateDigital(DigitalState.Off)
          blueLED.setStateDigital(DigitalState.Off)
          greenLED.setStateDigital(DigitalState.On)
        }

        if (buttonState == 2) {
          redLED.setStateDigital(DigitalState.Off)
          blueLED.setStateDigital(DigitalState.On)
          greenLED.setStateDigital(DigitalState.Off )
        }
      }
    }
    return 0
  }
}
