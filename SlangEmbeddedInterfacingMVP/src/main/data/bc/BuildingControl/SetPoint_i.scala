// #Sireum

package bc.BuildingControl

import org.sireum._
import bc._

// This file was auto-generated.  Do not edit

object SetPoint_i {
  def example(): BuildingControl.SetPoint_i = {
    return BuildingControl.SetPoint_i(
      low = BuildingControl.Temperature_i.example(),
      high = BuildingControl.Temperature_i.example())
  }
}

@datatype class SetPoint_i(
  val low: BuildingControl.Temperature_i,
  val high: BuildingControl.Temperature_i) {
}

object SetPoint_i_Payload {
  def example(): SetPoint_i_Payload = {
    return SetPoint_i_Payload(BuildingControl.SetPoint_i.example())
  }
}

@datatype class SetPoint_i_Payload(value: BuildingControl.SetPoint_i) extends art.DataContent
