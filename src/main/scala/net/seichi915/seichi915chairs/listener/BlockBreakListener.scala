package net.seichi915.seichi915chairs.listener

import net.seichi915.seichi915chairs.util.Implicits._
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class BlockBreakListener extends Listener {
  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  def onBlockBreak(event: BlockBreakEvent): Unit =
    if (event.getBlock.isOccupied)
      event.getPlayer.unsit(force = true, teleport = true)
}
