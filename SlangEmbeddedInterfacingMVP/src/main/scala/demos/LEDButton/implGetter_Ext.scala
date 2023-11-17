package demos.LEDButton

import org.sireum._
import platform.impl.PlatformImpl
import platform.impl.builtin.{FirmataImpl, GUIExampleImpl}

object implGetter_Ext {
  def getImpl(pinMap: org.sireum.Map[String, Z]): PlatformImpl = {
    return FirmataImpl(pinMap)
  }
}
