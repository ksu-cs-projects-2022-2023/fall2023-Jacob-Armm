// #Sireum

package bc.BuildingControl

import org.sireum._
import bc._

// This file will not be overwritten so is safe to edit
object TempControl_i_tcp_tempControl {

  var setPoint: SetPoint_i =
    SetPoint_i(
      Temperature_i(Util.minTemp, TempUnit.Fahrenheit),
      Temperature_i(Util.maxTemp, TempUnit.Fahrenheit))

  def initialise(api: TempControl_i_Initialization_Api): Unit = {
    setPoint = SetPoint_i(
      Temperature_i(Util.lowTemp, TempUnit.Fahrenheit),
      Temperature_i(Util.highTemp, TempUnit.Fahrenheit))
  }

  def handle_fanAck(api: TempControl_i_Operational_Api, value: BuildingControl.FanAck.Type): Unit = {
    api.logInfo(s"received fanAck $value")
    if (value == FanAck.Error) {
      // mitigate
      api.logError("Actuation failed!")
    } else {
      api.logInfo("Actuation worked.")
    }
  }

  def handle_setPoint(api: TempControl_i_Operational_Api, value: BuildingControl.SetPoint_i): Unit = {
    api.logInfo(s"received setPoint $value")
    setPoint = value
  }

  def handle_tempChanged(api: TempControl_i_Operational_Api): Unit = {
    val tempInF = Util.toFahrenheit(api.get_currentTemp().get)
    val setPointLowInF = Util.toFahrenheit(setPoint.low)
    val setPointHighInF = Util.toFahrenheit(setPoint.high)
    val cmdOpt: Option[FanCmd.Type] =
      if (tempInF.degrees > setPointHighInF.degrees) Some(FanCmd.On)
      else if (tempInF.degrees < setPointLowInF.degrees) Some(FanCmd.Off)
      else None[FanCmd.Type]()
    cmdOpt match {
      case Some(cmd) =>
        api.put_fanCmd(cmd)
        api.logInfo(s"Sent fan command: $cmd")
      case _ =>
        api.logInfo(s"Temperature ok: ${tempInF.degrees} F")
    }
  }

  def activate(api: TempControl_i_Operational_Api): Unit = {}

  def deactivate(api: TempControl_i_Operational_Api): Unit = {}

  def finalise(api: TempControl_i_Operational_Api): Unit = {}

  def recover(api: TempControl_i_Operational_Api): Unit = {}
}

@ext object TempControlNative {
  def fanAckLog(success: FanAck.Type): Unit = $
}