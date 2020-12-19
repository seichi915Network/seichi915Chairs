package net.seichi915.seichi915chairs

import net.seichi915.seichi915chairs.listener._
import net.seichi915.seichi915chairs.sitdata.SitData
import net.seichi915.seichi915chairs.task._
import net.seichi915.seichi915chairs.util.Implicits._
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

import scala.collection.mutable

object Seichi915Chairs {
  var instance: Seichi915Chairs = _

  var sitDataMap: mutable.HashMap[Player, SitData] = mutable.HashMap()
}

class Seichi915Chairs extends JavaPlugin {
  Seichi915Chairs.instance = this

  override def onEnable(): Unit = {
    Seq(
      new BlockBreakListener,
      new EntityDismountListener,
      new PlayerDeathListener,
      new PlayerInteractListener,
      new PlayerQuitListener,
      new PlayerTeleportListener
    ).foreach(Bukkit.getPluginManager.registerEvents(_, this))
    Map(
      (1000, 1000) -> new ResitTask
    ).foreach {
      case ((delay: Int, period: Int), bukkitRunnable: BukkitRunnable) =>
        bukkitRunnable.runTaskTimer(this, delay, period)
    }

    getLogger.info("seichi915Chairsが有効になりました。")
  }

  override def onDisable(): Unit = {
    Seichi915Chairs.sitDataMap.foreach {
      case (player: Player, _: SitData) =>
        player.unsit(force = true, teleport = true)
    }

    getLogger.info("seichi915Chairsが無効になりました。")
  }
}
