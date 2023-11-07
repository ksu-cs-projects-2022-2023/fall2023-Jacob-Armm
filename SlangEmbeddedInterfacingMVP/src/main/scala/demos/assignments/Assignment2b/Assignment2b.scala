//#Sireum
package demos.assignments.Assignment2b

import org.sireum._

object Assignment2b extends App {

  def map(x: Z, in_min: Z, in_max: Z, out_min: Z, out_max: Z): Z = {
    return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min
  }
  def main(args: ISZ[String]): Z = {
    /*

    For this assignment you will need to:

    1)  Setup the code similar to 2a before running the state machine

    2)  Write a state machine with the firmata implementation that using a button and potentiometer that controls
        the rgb values of an rgb led as follows:

        . Button: On release, button will switch between whether you are changing the value of Red, Green, or Blue

        . Potentiometer: The potentiometer changes the current value of the currently selected color

        . RGBLED: Update with current values of red, green, and blue
    */


    return 0
  }
}

