// #Sireum
package utils

import org.sireum._
import platform.impl._

@record class Config (pinMap: Map[String, Z], impl: PlatformImpl, port: Option[String])
