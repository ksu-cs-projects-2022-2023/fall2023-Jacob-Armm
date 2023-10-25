// #Sireum
package architecture

import org.sireum._
import utils.PinModeUtil.PinMode
import architecture.impl._

@record class Config (pinMap: Map[String, Z], impl: ArchImpl, port: Option[String])
