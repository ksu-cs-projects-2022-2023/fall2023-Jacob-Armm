package platform.impl.builtin

import jssc.{SerialNativeInterface, SerialPortList}
import org.firmata4j.firmata.FirmataDevice
import org.firmata4j.{IODevice, Pin}
import org.sireum.$internal.MutableMarker
import org.sireum._
import platform.impl.PlatformImpl
import utils.PinModeUtil.PinMode

import java.awt.GridBagLayout
import java.util.regex.Pattern
import javax.swing._

case class FirmataImpl(pinMap: Map[String, Z]) extends PlatformImpl {
  var device: IODevice = _

  override def init(p: Option[String]): Unit = {
    var initRun: B = F
    var port: String = ""

    port = p match {
      case Some(s) => s
      case _ => requestPort
    }

    this.synchronized {
      try {
        if (!initRun) {
          initRun = T
          device = new FirmataDevice(port.native)
          device.start()
          device.ensureInitializationIsDone()
        }
      } catch {
        case e: Throwable =>
          cprintln(T, e)
      }
    }

    if (!ready) {
      JOptionPane.showMessageDialog(null, s"${port} isn't ready\nNote: You may need to give rwx access to the device (e.g. sudo chmod 777 ${port})", "Error", JOptionPane.ERROR_MESSAGE)
      System.exit(1)
    }
  }

  override def ready: B = {
    return device.isReady
  }

  override def read(pin: String, mode: PinMode.Type): Z = {
    // for now just let exception halt the program
    val mappedPin = pinMap.get(pin)
    val p = device.getPin(mappedPin.get.toInt)
    p.setMode(Pin.Mode.valueOf(mode.name.native))
    return Z(p.getValue)
  }

  override def write(pin: String, mode: PinMode.Type, value: Z): Unit = {
    // for now just let exception halt the program
    val p = device.getPin(pinMap.get(pin).get.toInt)
    p.setMode(Pin.Mode.valueOf(mode.name.native))
    p.setValue(value.toInt)
  }

  override def retievePinList: Map[Z, ISZ[PinMode.Type]] = {
    val x = device.getPins
    var pinModeMap: ISZ[(Z, ISZ[PinMode.Type])] = ISZ()

    def pinSetToISZ(set: java.util.Set[Pin.Mode]): ISZ[PinMode.Type] = {
      var supportedModes: ISZ[PinMode.Type] = ISZ()
      set.forEach(mode => supportedModes = supportedModes :+ PinMode.byName(mode.name()).get)
      return supportedModes
    }

    x.forEach(pin => pinModeMap = pinModeMap :+ (Z(pin.getIndex.toInt), pinSetToISZ(pin.getSupportedModes)))
    return Map.empty ++ pinModeMap
  }

  private def requestPort: String = {
    val portNameSelector = new JComboBox[String]()
    portNameSelector.setModel(new DefaultComboBoxModel[String]())
    var portNames: List[Predef.String] = List[Predef.String] {
      "SIMULATE"
    }
    // TODO: maybe use jSerialComm so that the ports can be filtered by vendor ID
    //for (p <- SerialPort.getCommPorts.toList) {
    //  println(s"  ${p.getSystemPortPath}${if (p.getVendorID == 0x2341) " by Arduino LLC" else ""}")
    //}
    if (SerialNativeInterface.getOsType == SerialNativeInterface.OS_MAC_OS_X) {
      // for MAC OS default pattern of jssc library is too restrictive
      portNames = portNames.appendedAll(SerialPortList.getPortNames("/dev/", Pattern.compile("tty\\..*")).toList)
    }
    else {
      portNames = portNames.appendedAll(SerialPortList.getPortNames.toList)
    }
    for (portName <- portNames) {
      portNameSelector.addItem(portName)
    }
    val panel = new JPanel()
    panel.setLayout(new GridBagLayout())
    panel.add(new JLabel("Port "))
    panel.add(portNameSelector)
    if (JOptionPane.showConfirmDialog(null, panel, "Select the port", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
      return portNameSelector.getSelectedItem.toString
    }
    else {
      System.exit(0)
      halt("")
    }
  }

  override def $clonable: Boolean = false

  override def $clonable_=(b: Boolean): MutableMarker = this

  override def $owned: Boolean = false

  override def $owned_=(b: Boolean): MutableMarker = this

  override def $clone: MutableMarker = this

  override def string: String = this.toString
}
