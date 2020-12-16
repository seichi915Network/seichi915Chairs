package net.seichi915.seichi915chairs

import org.bukkit.plugin.java.JavaPlugin

object Seichi915Chairs {
  var instance: Seichi915Chairs = _
}

class Seichi915Chairs extends JavaPlugin {
  Seichi915Chairs.instance = this

  override def onEnable(): Unit = {
    getLogger.info("seichi915Chairsが有効になりました。")
  }

  override def onDisable(): Unit = {
    getLogger.info("seichi915Chairsが無効になりました。")
  }
}
