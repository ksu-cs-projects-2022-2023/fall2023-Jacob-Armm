// #Sireum

package bc.BuildingControl

import org.sireum._
import art._
import bc._
import bc.BuildingControl.{TempControl_i_tcp_tempControl => component}

// This file was auto-generated.  Do not edit

@datatype class TempControl_i_tcp_tempControl_Bridge(
  val id: Art.BridgeId,
  val name: String,
  val dispatchProtocol: DispatchPropertyProtocol,
  val dispatchTriggers: Option[ISZ[Art.PortId]],

  currentTemp: Port[BuildingControl.Temperature_i],
  fanAck: Port[BuildingControl.FanAck.Type],
  setPoint: Port[BuildingControl.SetPoint_i],
  fanCmd: Port[BuildingControl.FanCmd.Type],
  tempChanged: Port[art.Empty]
  ) extends Bridge {

  val ports : Bridge.Ports = Bridge.Ports(
    all = ISZ(currentTemp,
              fanAck,
              setPoint,
              fanCmd,
              tempChanged),

    dataIns = ISZ(currentTemp),

    dataOuts = ISZ(),

    eventIns = ISZ(fanAck,
                   setPoint,
                   tempChanged),

    eventOuts = ISZ(fanCmd)
  )

  val initialization_api : TempControl_i_Initialization_Api = {
    val api = TempControl_i_Initialization_Api(
      id,
      currentTemp.id,
      fanAck.id,
      setPoint.id,
      fanCmd.id,
      tempChanged.id
    )
    TempControl_i_tcp_tempControl_Bridge.c_initialization_api = Some(api)
    api
  }

  val operational_api : TempControl_i_Operational_Api = {
    val api = TempControl_i_Operational_Api(
      id,
      currentTemp.id,
      fanAck.id,
      setPoint.id,
      fanCmd.id,
      tempChanged.id
    )
    TempControl_i_tcp_tempControl_Bridge.c_operational_api = Some(api)
    api
  }

  val entryPoints : Bridge.EntryPoints =
    TempControl_i_tcp_tempControl_Bridge.EntryPoints(
      id,

      currentTemp.id,
      fanAck.id,
      setPoint.id,
      fanCmd.id,
      tempChanged.id,

      dispatchTriggers,

      initialization_api,
      operational_api)
}

object TempControl_i_tcp_tempControl_Bridge {

  var c_initialization_api: Option[TempControl_i_Initialization_Api] = None()
  var c_operational_api: Option[TempControl_i_Operational_Api] = None()

  @datatype class EntryPoints(
    TempControl_i_tcp_tempControl_BridgeId : Art.BridgeId,
    currentTemp_Id : Art.PortId,
    fanAck_Id : Art.PortId,
    setPoint_Id : Art.PortId,
    fanCmd_Id : Art.PortId,
    tempChanged_Id : Art.PortId,
    dispatchTriggers : Option[ISZ[Art.PortId]],
    initialization_api: TempControl_i_Initialization_Api,
    operational_api: TempControl_i_Operational_Api) extends Bridge.EntryPoints {

    val dataInPortIds: ISZ[Art.PortId] = ISZ(currentTemp_Id)

    val eventInPortIds: ISZ[Art.PortId] = ISZ(fanAck_Id,
                                              setPoint_Id,
                                              tempChanged_Id)

    val dataOutPortIds: ISZ[Art.PortId] = ISZ()

    val eventOutPortIds: ISZ[Art.PortId] = ISZ(fanCmd_Id)

    def initialise(): Unit = {
      // implement the following method in 'component':  def initialise(api: TempControl_i_Initialization_Api): Unit = {}
      component.initialise(initialization_api)
      Art.sendOutput(eventOutPortIds, dataOutPortIds)
    }

    def compute(): Unit = {
      // transpiler friendly filter
      def filter(receivedEvents: ISZ[Art.PortId], triggers: ISZ[Art.PortId]): ISZ[Art.PortId] = {
        var r = ISZ[Art.PortId]()
        val opsTriggers = ops.ISZOps(triggers)
        for(e <- receivedEvents) {
          if(opsTriggers.contains(e)) {
            r = r :+ e
          }
        }
        return r
      }

      // fetch received events ordered by highest urgency then earliest arrival-time
      val EventTriggered(receivedEvents) = Art.dispatchStatus(TempControl_i_tcp_tempControl_BridgeId)

      // remove non-dispatching event ports
      val dispatchableEventPorts: ISZ[Art.PortId] =
        if(dispatchTriggers.isEmpty) receivedEvents
        else filter(receivedEvents, dispatchTriggers.get)

      Art.receiveInput(eventInPortIds, dataInPortIds)

      for(portId <- dispatchableEventPorts) {
        if(portId == fanAck_Id){
          val Some(BuildingControl.FanAck_Payload(value)) = Art.getValue(fanAck_Id)

          // implement the following in 'component':  def handle_fanAck(api: TempControl_i_Operational_Api, value: BuildingControl.FanAck.Type): Unit = {}
          component.handle_fanAck(operational_api, value)
        }
        else if(portId == setPoint_Id){
          val Some(BuildingControl.SetPoint_i_Payload(value)) = Art.getValue(setPoint_Id)

          // implement the following in 'component':  def handle_setPoint(api: TempControl_i_Operational_Api, value: BuildingControl.SetPoint_i): Unit = {}
          component.handle_setPoint(operational_api, value)
        }
        else if(portId == tempChanged_Id) {
          // implement the following in 'component':  def handle_tempChanged(api: TempControl_i_Operational_Api): Unit = {}
          component.handle_tempChanged(operational_api)
        }
      }

      Art.sendOutput(eventOutPortIds, dataOutPortIds)
    }

    def activate(): Unit = {
      // implement the following method in 'component':  def activate(api: TempControl_i_Operational_Api): Unit = {}
      component.activate(operational_api)
    }

    def deactivate(): Unit = {
      // implement the following method in 'component':  def deactivate(api: TempControl_i_Operational_Api): Unit = {}
      component.deactivate(operational_api)
    }

    def finalise(): Unit = {
      // implement the following method in 'component':  def finalise(api: TempControl_i_Operational_Api): Unit = {}
      component.finalise(operational_api)
    }

    def recover(): Unit = {
      // implement the following method in 'component':  def recover(api: TempControl_i_Operational_Api): Unit = {}
      component.recover(operational_api)
    }

    override
    def testInitialise(): Unit = {
      // implement the following method in 'component':  def initialise(api: TempControl_i_Initialization_Api): Unit = {}
      component.initialise(initialization_api)
      Art.releaseOutput(eventOutPortIds, dataOutPortIds)
    }

    override
    def testCompute(): Unit = {
      // transpiler friendly filter
      def filter(receivedEvents: ISZ[Art.PortId], triggers: ISZ[Art.PortId]): ISZ[Art.PortId] = {
        var r = ISZ[Art.PortId]()
        val opsTriggers = ops.ISZOps(triggers)
        for(e <- receivedEvents) {
          if(opsTriggers.contains(e)) {
            r = r :+ e
          }
        }
        return r
      }

      // fetch received events ordered by highest urgency then earliest arrival-time
      val EventTriggered(receivedEvents) = Art.dispatchStatus(TempControl_i_tcp_tempControl_BridgeId)

      // remove non-dispatching event ports
      val dispatchableEventPorts: ISZ[Art.PortId] =
        if(dispatchTriggers.isEmpty) receivedEvents
        else filter(receivedEvents, dispatchTriggers.get)

      Art.receiveInput(eventInPortIds, dataInPortIds)

      for(portId <- dispatchableEventPorts) {
        if(portId == fanAck_Id){
          val Some(BuildingControl.FanAck_Payload(value)) = Art.getValue(fanAck_Id)

          // implement the following in 'component':  def handle_fanAck(api: TempControl_i_Operational_Api, value: BuildingControl.FanAck.Type): Unit = {}
          component.handle_fanAck(operational_api, value)
        }
        else if(portId == setPoint_Id){
          val Some(BuildingControl.SetPoint_i_Payload(value)) = Art.getValue(setPoint_Id)

          // implement the following in 'component':  def handle_setPoint(api: TempControl_i_Operational_Api, value: BuildingControl.SetPoint_i): Unit = {}
          component.handle_setPoint(operational_api, value)
        }
        else if(portId == tempChanged_Id) {
          // implement the following in 'component':  def handle_tempChanged(api: TempControl_i_Operational_Api): Unit = {}
          component.handle_tempChanged(operational_api)
        }
      }

      Art.releaseOutput(eventOutPortIds, dataOutPortIds)
    }
  }
}