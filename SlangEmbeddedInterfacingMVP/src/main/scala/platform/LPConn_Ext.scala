package platform

import platform.impl.PlatformImpl
import devices.Pin
import org.sireum._
import utils.Config
import utils.PinModeUtil.PinMode

object LPConn_Ext {

  private var pinMap: Map[String, Z] = _

  private var architectureImpl: PlatformImpl = _

  def init(conf: Config, logicalPins: ISZ[Pin]): Unit = {
    architectureImpl = conf.impl
    architectureImpl.init(conf.port)
    pinMap = conf.pinMap

    val physicalPins = architectureImpl.retievePinList

    for(pin <- logicalPins){
      assert(ops.ISZOps(pinMap.keys).contains(pin.pinAlias), s"PinAlias ${pin.pinAlias} does not exist in pin map")
    }

    for(pin <- pinMap.keys){
      assert(ops.ISZOps(logicalPins).exists(p => p.pinAlias == pin), s"${pin} is not a logically defined pin")
    }

    for (pinNum <- pinMap.values) {
      assert(ops.ISZOps(physicalPins.keys).contains(pinNum), s"Pin $pinNum does not exist for this implementation")
    }

    for(pin <- logicalPins) {
      assert(ops.ISZOps(physicalPins.get(pinMap.get(pin.pinAlias).get).get).contains(pin.mode), s"\nPinMode Map Mismatch: pinMode ${pin.mode} is an invalid pinMode for ${pinMap.get(pin.pinAlias).get} which can only accept ${physicalPins.get(pinMap.get(pin.pinAlias).get).get}")
    }


  }

  def ready: B = {
    architectureImpl.ready
  }

  def read(pin: String, mode: PinMode.Type): Z = {
    architectureImpl.read(pin, mode)
  }

  def write(pin: String, mode: PinMode.Type, value: Z): Unit = {
    architectureImpl.write(pin, mode, value)
  }

}
