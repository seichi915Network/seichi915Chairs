package net.seichi915.seichi915chairs.event

import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.{Cancellable, HandlerList}
import org.bukkit.event.player.PlayerEvent

object PlayerChairUnsitEvent {
  private val handlers = new HandlerList
}

class PlayerChairUnsitEvent(who: Player, block: Block)
    extends PlayerEvent(who)
    with Cancellable {
  private var cancelled = false

  def getBlock: Block = block

  override def getHandlers: HandlerList = PlayerChairUnsitEvent.handlers

  override def isCancelled: Boolean = cancelled

  override def setCancelled(cancel: Boolean): Unit = cancelled = cancel
}
