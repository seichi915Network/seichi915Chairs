package net.seichi915.seichi915chairs.listener

import net.seichi915.seichi915chairs.Seichi915Chairs
import net.seichi915.seichi915chairs.util.Implicits._
import org.bukkit.entity.Player
import org.bukkit.event.{EventHandler, EventPriority, Listener}
import org.spigotmc.event.entity.EntityDismountEvent

class EntityDismountListener extends Listener {
  @EventHandler(priority = EventPriority.LOWEST)
  def onExitVehicle(event: EntityDismountEvent): Unit =
    event.getEntity match {
      case player: Player
          if player.isSitting && Seichi915Chairs.sitDataMap(player).isSitting =>
        if (!player.unsit(force = false, teleport = true))
          event.setCancelled(true)
      case _ =>
    }
}
