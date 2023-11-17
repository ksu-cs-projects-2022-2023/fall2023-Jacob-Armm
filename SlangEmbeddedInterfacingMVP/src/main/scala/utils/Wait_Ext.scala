package utils

import org.sireum._
import java.util.concurrent.TimeUnit

object Wait_Ext {
  def waitInMS(ms: Z): Unit = {
    TimeUnit.MILLISECONDS.sleep(ms.toLong)
  }

  def waitInS(seconds: Z): Unit = {
    TimeUnit.SECONDS.sleep(seconds.toLong)
  }

  def waitInM(minutes: Z): Unit = {
    TimeUnit.MINUTES.sleep(minutes.toLong)
  }

  def waitInH(hours: Z): Unit = {
    TimeUnit.HOURS.sleep(hours.toLong)
  }
}
