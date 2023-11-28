package platform.impl.builtin

import org.sireum.$internal.MutableMarker
import org.sireum._
import platform.impl.PlatformImpl
import utils.PinModeUtil.PinMode

import java.awt.{BorderLayout, Color, Dimension, FlowLayout, Font, GridBagLayout}
import java.util.regex.Pattern
import javax.swing._
import java.awt.event.{KeyAdapter, KeyEvent}


case class GUI() {
  var f: JFrame = _
  var spaceBarPressed: B = F
  val l1: JButton = new JButton()
  val l2: JButton = new JButton()
  val l3: JButton = new JButton()



  def init(): Unit = {
    f = new JFrame() //creating instance of JFrame
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

    f.addKeyListener(new KeyAdapter() {
      override def keyPressed(e: KeyEvent): Unit = {
        val keyCode = e.getKeyCode
        if (keyCode == KeyEvent.VK_SPACE) {
          spaceBarPressed = true
        }
      }

      override def keyReleased(e: KeyEvent): Unit = {
        val keyCode = e.getKeyCode
        if (keyCode == KeyEvent.VK_SPACE) {
          spaceBarPressed = false
        }
      }
    })

    l1.setBackground(new Color(255, 0, 0, 50))
    l1.setOpaque(true)
    l1.setBorderPainted(false)
    l2.setBackground(new Color(0, 255, 0, 50))
    l2.setOpaque(true)
    l2.setBorderPainted(false)
    l3.setBackground(new Color(0, 0, 255, 50))
    l3.setOpaque(true)
    l3.setBorderPainted(false)

    l1.setPreferredSize(new Dimension(200, 200))
    l1.setFont(l1.getFont.deriveFont(Font.PLAIN, 150f))
    l2.setPreferredSize(new Dimension(200, 200))
    l2.setFont(l2.getFont.deriveFont(Font.PLAIN, 150f))
    l3.setPreferredSize(new Dimension(200, 200))
    l3.setFont(l3.getFont.deriveFont(Font.PLAIN, 150f))


    f.setSize(400, 650) //400 width and 500 height
    f.setLayout(new FlowLayout()) //using no layout managers
    f.add(l1); //adding button in JFrame
    f.add(l2);
    f.add(l3);
    f.setVisible(true) //making the frame visible
  }
}

case class GUIExampleImpl(pinMap: Map[String, Z]) extends PlatformImpl {

  var gui: GUI = _

  override def init(port: Option[String]): Unit = {
    gui = GUI()
    gui.init()
    gui.f.requestFocusInWindow()
  }

  override def retievePinList: Map[Z, _root_.org.sireum.ISZ[PinMode.Type]] = {
    return Map.empty ++ ISZ(
      1 ~> ISZ(PinMode.OUTPUT, PinMode.PWM),
      2 ~> ISZ(PinMode.OUTPUT, PinMode.PWM),
      3 ~> ISZ(PinMode.OUTPUT, PinMode.PWM),
      4 ~> ISZ(PinMode.INPUT, PinMode.PWM)
    )
  }

  override def ready: B = {
    return T
  }

  override def read(pin: String, mode: PinMode.Type): Z = {
    val mappedPin = pinMap.get(pin)
    val pn = mappedPin match {
      case Some(x) => x
      case _ => halt("Invalid Pin")
    }


    return pn match {
      case z"4" => if(gui.spaceBarPressed) z"1" else z"0"
      case _ => halt("Nothing to read on pin")
    }
  }

  override def write(pin: String, mode: PinMode.Type, value: Z): Unit = {
    val mappedPin = pinMap.get(pin)
    val pn = mappedPin match {
      case Some(x) => x
      case _ => halt("Invalid Pin")
    }

    if(mode == PinMode.OUTPUT) {
      pn match {
        case z"1" => if (value == z"1") gui.l1.setBackground(new Color(255, 0, 0, 255)) else gui.l1.setBackground(new Color(255, 0, 0, 50))
        case z"2" => if (value == z"1") gui.l2.setBackground(new Color(0, 255, 0, 255)) else gui.l2.setBackground(new Color(0, 255, 0, 50))
        case z"3" => if (value == z"1") gui.l3.setBackground(new Color(0, 0, 255, 255)) else gui.l3.setBackground(new Color(0, 0, 255, 50))
        case _ => halt("Nothing to read on pin")
      }
    }

    if (mode == PinMode.PWM) {
      pn match {
        case z"1" => gui.l1.setBackground(new Color(255, 0, 0, value.toInt))
        case z"2" => gui.l2.setBackground(new Color(0, 255, 0, value.toInt))
        case z"3" => gui.l3.setBackground(new Color(0, 0, 255, value.toInt))
        case _ => halt("Nothing to read on pin")
      }
    }

    gui.f.invalidate()
    gui.f.validate()
    gui.f.repaint()
  }

  override def $clonable: Boolean = false

  override def $clonable_=(b: Boolean): MutableMarker = this

  override def $owned: Boolean = false

  override def $owned_=(b: Boolean): MutableMarker = this

  override def $clone: MutableMarker = this

  override def string: String = this.toString
}
