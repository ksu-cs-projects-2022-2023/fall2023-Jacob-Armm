package demos.WaitDemo

import org.sireum._
import platform.impl.PlatformImpl
import platform.impl.builtin.{FirmataImpl, GUIExampleImpl}

object implGetter_Ext {
  def getImpl(pinMap: org.sireum.Map[String, Z]): PlatformImpl = {
    return GUIExampleImpl(pinMap)
  }
}
