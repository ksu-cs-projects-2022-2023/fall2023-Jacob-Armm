// #Sireum
package demos

import org.sireum._
import architecture.LPConn
import devices._
import utils.PinModeUtil._

object ButtonWaitDemo extends App {
  override def main(args: ISZ[String]): Z = {
    val pin1: Pin = Pin("RedLed", PinMode.PWM)
    val pin2: Pin = Pin("Button", PinMode.INPUT)
    val pin3: Pin = Pin("GreenLed", PinMode.PWM)
    val pin4: Pin = Pin("BlueLed", PinMode.PWM)
    val redLed: LEDPWM = LEDPWM(pin1)
    val greenLed: LEDPWM = LEDPWM(pin3)
    val blueLed: LEDPWM = LEDPWM(pin4)
    val button: Button = Button(pin2)


    //LPConn.init(None())

    var buttonState: B = F
    var redLedVal: Z = 0
    var greenLedVal: Z = 25
    var blueLedVal: Z = 50
    var redIncreasing: B = T
    var greenIncreasing: B = F
    var blueIncreasing: B = F

    while (true) {
      if(button.isPressed) {
        while (button.isPressed) {}
        buttonState = T
      }
      if (buttonState) {
        redLed.setValue(0)
        while (buttonState) {
          if(redIncreasing){
            redLedVal = redLedVal + 1
            redIncreasing = redLedVal != 100
          } else {
            redLedVal = redLedVal - 1
            redIncreasing = redLedVal == 0
          }
          if (greenIncreasing) {
            greenLedVal = greenLedVal + 1
            greenIncreasing = greenLedVal != 100
          } else {
            greenLedVal = greenLedVal - 1
            greenIncreasing = greenLedVal == 0
          }
          if (blueIncreasing) {
            blueLedVal = blueLedVal + 1
            blueIncreasing = blueLedVal != 100
          } else {
            blueLedVal = blueLedVal - 1
            blueIncreasing = blueLedVal == 0
          }

          LPConn.holdWait(5)
          redLed.setValue(redLedVal)
          greenLed.setValue(greenLedVal)
          blueLed.setValue(blueLedVal)

          if (button.isPressed) {
            while (button.isPressed) {}
            buttonState = F
            println(s"red: ${redLedVal}   blue: ${blueLedVal}   green: ${greenLedVal}")
          }
        }
      }
    }

    return 0
  }
}
