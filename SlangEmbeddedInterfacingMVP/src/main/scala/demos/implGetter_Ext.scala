package demos

import org.sireum._
import architecture.impl.ArchImpl
import architecture.impl.builtin.firmata.FirmataImpl

object implGetter_Ext {
  def getImpl(pinMap: org.sireum.Map[String, Z]): ArchImpl = {
    return FirmataImpl(pinMap)
  }
}
