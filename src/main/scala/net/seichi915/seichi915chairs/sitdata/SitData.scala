package net.seichi915.seichi915chairs.sitdata

import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.entity.Entity

case class SitData(block: Block,
                   sitLocation: Location,
                   teleportBackLocation: Location,
                   var arrow: Entity) {
  def getBlock: Block = block

  def getSitLocation: Location = sitLocation.clone()

  def getTeleportBackLocation: Location = teleportBackLocation.clone()

  def getArrow: Entity = arrow

  def setArrow(newArrow: Entity): Unit = arrow = newArrow
}
