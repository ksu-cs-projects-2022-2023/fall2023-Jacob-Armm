package bc

import bc.BuildingControl.FanCmd
import bc.BuildingControl.device.{Board, DemoDeviceBridge}
import org.sireum._

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
          DemoDeviceBridge.setRed(FanCmd.On)
          DemoDeviceBridge.setBlue(FanCmd.Off)
          DemoDeviceBridge.setGreen(FanCmd.Off)
        }

        if (buttonState == 1) {
          DemoDeviceBridge.setRed(FanCmd.Off)
          DemoDeviceBridge.setBlue(FanCmd.Off)
          DemoDeviceBridge.setGreen(FanCmd.On)
        }

        if (buttonState == 2) {
          DemoDeviceBridge.setRed(FanCmd.Off)
          DemoDeviceBridge.setBlue(FanCmd.On)
          DemoDeviceBridge.setGreen(FanCmd.Off)
        }
      }
    }
    return 0
  }
}
