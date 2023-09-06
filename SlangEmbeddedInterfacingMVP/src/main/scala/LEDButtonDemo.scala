package scala

import org.sireum._
import FirmataUtil._

object LEDButtonDemo extends App {
  def main(args: ISZ[String]): Z = {
    LEDButtonDeviceBridge.ready
    if(Board.ready){

      var buttonState: Z = 0

      while(true) {

        if(LEDButtonDeviceBridge.getButton) {
          while (LEDButtonDeviceBridge.getButton) { }
          if (buttonState < 2)
            buttonState += 1
          else
            buttonState = 0
        }

        if (buttonState == 0) {
          LEDButtonDeviceBridge.setRed(LEDMode.On)
          LEDButtonDeviceBridge.setBlue(LEDMode.Off)
          LEDButtonDeviceBridge.setGreen(LEDMode.Off)
        }

        if (buttonState == 1) {
          LEDButtonDeviceBridge.setRed(LEDMode.Off)
          LEDButtonDeviceBridge.setBlue(LEDMode.Off)
          LEDButtonDeviceBridge.setGreen(LEDMode.On)
        }

        if (buttonState == 2) {
          LEDButtonDeviceBridge.setRed(LEDMode.Off)
          LEDButtonDeviceBridge.setBlue(LEDMode.On)
          LEDButtonDeviceBridge.setGreen(LEDMode.Off)
        }
      }
    }
    return 0
  }
}
