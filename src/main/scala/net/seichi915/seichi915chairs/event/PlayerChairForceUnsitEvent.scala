package net.seichi915.seichi915chairs.event

import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

object PlayerChairForceUnsitEvent {
  private val handlers = new HandlerList
}

class PlayerChairForceUnsitEvent(who: Player, block: Block)
    extends PlayerEvent(who) {
  def getBlock: Block = block

  override def getHandlers: HandlerList = PlayerChairForceUnsitEvent.handlers
}
