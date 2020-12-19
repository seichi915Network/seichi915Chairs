package net.seichi915.seichi915chairs.task

import net.seichi915.seichi915chairs.Seichi915Chairs
import net.seichi915.seichi915chairs.sitdata.SitData
import net.seichi915.seichi915chairs.util.Util
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class ResitTask extends BukkitRunnable {
  override def run(): Unit =
    Seichi915Chairs.sitDataMap.foreach {
      case (player: Player, sitData: SitData) =>
        val previousArrow = sitData.getArrow
        val newArrow = Util.spawnChairsArrowAndGet(previousArrow.getLocation)
        newArrow.addPassenger(player)
        sitData.setArrow(newArrow)
        previousArrow.remove()
    }
}
