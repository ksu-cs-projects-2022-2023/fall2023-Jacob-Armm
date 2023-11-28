package demos.RGBLED

import platform.impl.PlatformImpl
import org.sireum._
import platform.impl.builtin.{FirmataImpl, GUIExampleImpl}

object implGetter_Ext {
  def getImpl(pinMap: org.sireum.Map[String, Z]): PlatformImpl = {
    return FirmataImpl(pinMap)
  }
}
