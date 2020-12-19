package net.seichi915.seichi915chairs.listener

import net.seichi915.seichi915chairs.util.Implicits._
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class PlayerTeleportListener extends Listener {
  @EventHandler(priority = EventPriority.LOWEST)
  def onPlayerTeleport(event: PlayerTeleportEvent): Unit =
    if (event.getPlayer.isSitting)
      event.getPlayer.unsit(force = true, teleport = false)
}
