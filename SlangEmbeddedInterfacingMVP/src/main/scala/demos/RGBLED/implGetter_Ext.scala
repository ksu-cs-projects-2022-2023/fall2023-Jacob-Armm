package demos.RGBLED

import architecture.impl.ArchImpl
import architecture.impl.builtin.firmata.FirmataImpl
import org.sireum._

object implGetter_Ext {
  def getImpl(pinMap: org.sireum.Map[String, Z]): ArchImpl = {
    return FirmataImpl(pinMap)
  }
}
