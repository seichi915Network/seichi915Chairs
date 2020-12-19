package net.seichi915.seichi915chairs.listener

import net.seichi915.seichi915chairs.util.Implicits._
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.{EventHandler, EventPriority, Listener}
import org.bukkit.inventory.EquipmentSlot

class PlayerInteractListener extends Listener {
  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  def onPlayerInteract(event: PlayerInteractEvent): Unit = {
    if (!event.getAction.equals(Action.RIGHT_CLICK_BLOCK)) return
    if (event.getHand.equals(EquipmentSlot.OFF_HAND)) return
    val player = event.getPlayer
    if (player.isSitting) return
    val clickedBlock = event.getClickedBlock
    if (clickedBlock.isOccupied) {
      player.sendMessage("この椅子は使用中です。".toErrorMessage)
      return
    }
    if (player.sit(clickedBlock)) event.setCancelled(true)
  }
}
