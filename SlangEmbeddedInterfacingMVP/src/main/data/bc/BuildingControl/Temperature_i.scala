// #Sireum

package bc.BuildingControl

import org.sireum._
import bc._

// This file was auto-generated.  Do not edit

object Temperature_i {
  def example(): BuildingControl.Temperature_i = {
    return BuildingControl.Temperature_i(
      degrees = Base_Types.Float_32_example(),
      unit = BuildingControl.TempUnit.byOrdinal(0).get)
  }
}

@datatype class Temperature_i(
  val degrees: F32,
  val unit: BuildingControl.TempUnit.Type) {
}

object Temperature_i_Payload {
  def example(): Temperature_i_Payload = {
    return Temperature_i_Payload(BuildingControl.Temperature_i.example())
  }
}

@datatype class Temperature_i_Payload(value: BuildingControl.Temperature_i) extends art.DataContent
