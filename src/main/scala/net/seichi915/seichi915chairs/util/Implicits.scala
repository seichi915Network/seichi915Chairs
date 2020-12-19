package net.seichi915.seichi915chairs.util

import net.seichi915.seichi915chairs.Seichi915Chairs
import net.seichi915.seichi915chairs.event._
import net.seichi915.seichi915chairs.sitdata.SitData
import org.bukkit.{Bukkit, ChatColor}
import org.bukkit.block.Block
import org.bukkit.block.data.Bisected
import org.bukkit.block.data.`type`.Stairs
import org.bukkit.entity.Player

object Implicits {
  implicit class PlayerOps(player: Player) {
    def isSitting: Boolean =
      Seichi915Chairs.sitDataMap.contains(player)

    def sit(block: Block): Boolean = {
      if (!block.getBlockData.isInstanceOf[Stairs]) return false
      val stairs = block.getBlockData.asInstanceOf[Stairs]
      if (!stairs.getHalf.equals(Bisected.Half.BOTTOM)) return false
      if (!stairs.getShape.equals(Stairs.Shape.STRAIGHT)) return false
      if (player.isSneaking) return false
      val sitLocation = Util.calcSitLocation(block)
      val playerChairSitEvent = new PlayerChairSitEvent(player, block)
      Bukkit.getPluginManager.callEvent(playerChairSitEvent)
      if (playerChairSitEvent.isCancelled) return false
      val arrow = Util.spawnChairsArrowAndGet(sitLocation)
      val sitData = SitData(block, sitLocation, player.getLocation, arrow)
      player.teleport(sitLocation)
      arrow.addPassenger(player)
      Seichi915Chairs.sitDataMap += player -> sitData
      true
    }

    def unsit(force: Boolean, teleport: Boolean): Boolean = {
      val sitData = Seichi915Chairs.sitDataMap(player)
      if (force) {
        val playerChairForceUnsitEvent =
          new PlayerChairForceUnsitEvent(player, sitData.getBlock)
        Bukkit.getPluginManager.callEvent(playerChairForceUnsitEvent)
      } else {
        val playerChairUnsitEvent =
          new PlayerChairUnsitEvent(player, sitData.getBlock)
        Bukkit.getPluginManager.callEvent(playerChairUnsitEvent)
        if (playerChairUnsitEvent.isCancelled) return false
      }
      player.leaveVehicle()
      sitData.getArrow.remove()
      player.setSneaking(false)
      if (teleport) player.teleport(sitData.getTeleportBackLocation)
      Seichi915Chairs.sitDataMap.remove(player)
      true
    }
  }

  implicit class BlockOps(block: Block) {
    def isOccupied: Boolean =
      Seichi915Chairs.sitDataMap.values
        .map(_.getBlock.getLocation)
        .toSet
        .contains(block.getLocation)
  }

  implicit class StringOps(string: String) {
    def toNormalMessage: String =
      s"${ChatColor.AQUA}[${ChatColor.WHITE}seichi915Chairs${ChatColor.AQUA}]${ChatColor.RESET} $string"

    def toSuccessMessage: String =
      s"${ChatColor.AQUA}[${ChatColor.GREEN}seichi915Chairs${ChatColor.AQUA}]${ChatColor.RESET} $string"

    def toWarningMessage: String =
      s"${ChatColor.AQUA}[${ChatColor.GOLD}seichi915Chairs${ChatColor.AQUA}]${ChatColor.RESET} $string"

    def toErrorMessage: String =
      s"${ChatColor.AQUA}[${ChatColor.RED}seichi915Chairs${ChatColor.AQUA}]${ChatColor.RESET} $string"
  }
}
