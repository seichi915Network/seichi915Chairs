package net.seichi915.seichi915chairs.util

import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.block.BlockFace._
import org.bukkit.block.data.`type`.Stairs
import org.bukkit.entity.{AbstractArrow, Entity}
import org.bukkit.util.Vector

object Util {
  def spawnChairsArrowAndGet(location: Location): Entity = {
    val arrow = location.getWorld.spawnArrow(location, new Vector(), 0, 0)
    arrow.setGravity(false)
    arrow.setInvulnerable(true)
    arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED)
    arrow
  }

  def calcSitLocation(block: Block): Location = {
    val blockData = block.getBlockData
    val stairs = blockData.asInstanceOf[Stairs]
    val ascendingFacing = stairs.getFacing
    val yaw =
      ascendingFacing.getOppositeFace match {
        case NORTH => 180
        case EAST  => -90
        case SOUTH => 0
        case WEST  => 90
        case _     => throw new IllegalArgumentException
      }
    val location = block.getLocation
    location.setYaw(yaw)
    location.add(0.5D, 0D, 0.5D)
    location
  }
}
