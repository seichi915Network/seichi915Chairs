package net.seichi915.seichi915chairs.sitdata

import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.entity.Entity

case class SitData(var sitting: Boolean,
                   block: Block,
                   sitLocation: Location,
                   teleportBackLocation: Location,
                   var arrow: Entity) {
  def isSitting: Boolean = sitting

  def setSitting(sitting: Boolean): Unit = this.sitting = sitting

  def getBlock: Block = block

  def getSitLocation: Location = sitLocation.clone()

  def getTeleportBackLocation: Location = teleportBackLocation.clone()

  def getArrow: Entity = arrow

  def setArrow(newArrow: Entity): Unit = arrow = newArrow
}
