package scala

import org.sireum._
import FirmataUtil._

object DemoStateMachine extends App {
  def main(args: ISZ[String]): Z = {
    DemoDeviceBridge.ready
    if(Board.ready){

      var buttonState: Z = 0

      while(true) {

        if(DemoDeviceBridge.getButton) {
          while (DemoDeviceBridge.getButton) { }
          if (buttonState < 2)
            buttonState += 1
          else
            buttonState = 0
        }

        if (buttonState == 0) {
          DemoDeviceBridge.setRed(LEDMode.On)
          DemoDeviceBridge.setBlue(LEDMode.Off)
          DemoDeviceBridge.setGreen(LEDMode.Off)
        }

        if (buttonState == 1) {
          DemoDeviceBridge.setRed(LEDMode.Off)
          DemoDeviceBridge.setBlue(LEDMode.Off)
          DemoDeviceBridge.setGreen(LEDMode.On)
        }

        if (buttonState == 2) {
          DemoDeviceBridge.setRed(LEDMode.Off)
          DemoDeviceBridge.setBlue(LEDMode.On)
          DemoDeviceBridge.setGreen(LEDMode.Off)
        }
      }
    }
    return 0
  }
}
