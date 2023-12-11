//#Sireum
package demos.assignments.Assignment3

import org.sireum._
import platform.impl.PlatformImpl

object Asiignment3 extends App {

/*
  The point of this assignment is to show that if behavior code is written for one implementation
  it can be easily switched to another implementation without modifying the behavior code
*/

  override def main(args: ISZ[String]): Z = {

    // 1) Define a pins
    // 2) Define implGetter_Ext.scala to get GUI Example Implementation
    // 3) Define pinMap based on GUIDescription.txt
    // 4) Define Config
    // 5) Initialize Devices
    // 6) Write a state machine that turns an led on and off when the button is pressed again
    // 7) Modify implGetter_Ext to get Firmata Implementation
    // 8) Modify pinMap to make the code usable with the firmata implementation
    // 9) Build board to recreate the behavior seen on the GUI

    return 0
  }
}

//@ext object implGetter {
//  def getImpl(pinMap: Map[String, Z]): PlatformImpl = $
//}