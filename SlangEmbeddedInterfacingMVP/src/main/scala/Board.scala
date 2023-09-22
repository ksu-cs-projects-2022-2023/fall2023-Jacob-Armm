// #Sireum

import org.sireum._
import FirmataUtil._

@ext object Board {

  def init(port: String): Unit = $

  def ready: B = $

  def analogRead(pin: String, mode: PinMode.Type): Z = $

  def analogWrite(pin: String, mode: PinMode.Type, value: Z): Unit = $
}
