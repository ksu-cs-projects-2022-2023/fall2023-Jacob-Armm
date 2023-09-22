//
//package scala
//
//import org.sireum._
//import FirmataUtil._
//
//object ServoPotDemo extends App {
//  def main(args: ISZ[String]): Z = {
//    ServoPotDeviceBridge.ready
//    if(Board.ready){
//      while (true) {
//        ServoPotDeviceBridge.setServoPos(ServoPotDeviceBridge.getPotScaledValue())
//      }
//    }
//    return 0
//  }
//}
