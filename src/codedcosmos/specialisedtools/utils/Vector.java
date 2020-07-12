package codedcosmos.specialisedtools.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Vector {
	private int x;
	private int y;
	private int z;
	
	public Vector() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vector(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(Location location) {
		this.x = location.getBlockX();
		this.y = location.getBlockY();
		this.z = location.getBlockZ();
	}
	
	public Vector(Vector vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
	}
	
	public Vector(Block block) {
		this.x = block.getLocation().getBlockX();
		this.y = block.getLocation().getBlockY();
		this.z = block.getLocation().getBlockZ();
	}
	
	public Vector add(int x, int y, int z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getZ() {
		return z;
	}
	
	public Location getLocation(World world) {
		return new Location(world, x, y, z);
	}
	
	public boolean equalsVector(Vector vector) {
		if (this.x != vector.x) return false;
		if (this.y != vector.y) return false;
		if (this.z != vector.z) return false;
		return true;
	}
}
