package net.seichi915.seichi915chairs.listener

import net.seichi915.seichi915chairs.util.Implicits._
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class PlayerDeathListener extends Listener {
  @EventHandler(priority = EventPriority.LOWEST)
  def onPlayerDeath(event: PlayerDeathEvent): Unit =
    if (event.getEntity.isSitting)
      event.getEntity.unsit(force = true, teleport = false)
}
