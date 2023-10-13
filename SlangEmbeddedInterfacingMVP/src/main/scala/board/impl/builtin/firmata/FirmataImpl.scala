package board.impl.builtin.firmata

import org.sireum._
import board.impl.BoardImpl
import org.firmata4j.firmata.FirmataDevice
import org.firmata4j.{IODevice, Pin}
import utils.PinModeUtil.PinMode
import jssc.{SerialNativeInterface, SerialPortList}
import java.awt.GridBagLayout
import java.util.regex.Pattern
import javax.swing._

case class FirmataImpl(pinMap: Map[String, Z]) extends BoardImpl {
  var port: String = _
  var device: IODevice = _
  var initRun: B = F

  override def init(p: Option[String]): Unit = {

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
}
