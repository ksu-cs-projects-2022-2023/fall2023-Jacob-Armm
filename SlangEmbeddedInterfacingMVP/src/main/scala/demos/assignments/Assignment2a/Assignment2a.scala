//#Sireum
package demos.assignments.Assignment2a

import org.sireum._

object Assignment2a extends App {
  def main(args: ISZ[String]): Z = {

    // In this example we will be blinking the on board LED on for 1 second then off for 1 second on repeat

    // 1) Define your logical pin
    // Example: val pin: Pin = Pin("pinName", PinMode.MODE)

    // 2) Define a pin map from your logical pins to physical pins
    /* Example:

          val pinMap: Map[String, Z] = Map.empty[String, Z] ++ ISZ(
            "pinAlias1" ~> assignOutNumber1,
            "pinAlias2" ~> assignOutNumber2
          )

     */

    // 4) Define a config that takes in your pin map, an architecture implementation, and a port
    // Example: val config = Config(pinMap, implGetter.getImpl(pinMap), None())

    // 5) Initialize the architecture with the configuration and list of logical pins
    // Example: LPConn.init(config, ISZ(pin1, pin2, ...))

    // 6) Create an LED object
    // Example: val device: Device = Device.createDevice(devicePin1, devicePin2, ...)

    // 7) Write a state machine to blink the onboard

    return 0
  }
}

// 3) Define an extension to the firmata implementation
    /* Example:

        @ext object implGetter {
          def getterMethodName(pinMap: Map[String, Z]): ArchImpl = $
        }

     */