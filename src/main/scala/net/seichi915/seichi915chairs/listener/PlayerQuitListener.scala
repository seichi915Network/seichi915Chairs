package net.seichi915.seichi915chairs.listener

import net.seichi915.seichi915chairs.util.Implicits._
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class PlayerQuitListener extends Listener {
  @EventHandler(priority = EventPriority.LOWEST)
  def onPlayerQuit(event: PlayerQuitEvent): Unit =
    if (event.getPlayer.isSitting)
      event.getPlayer.unsit(force = true, teleport = true)
}
