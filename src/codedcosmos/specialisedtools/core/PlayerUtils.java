package codedcosmos.specialisedtools.core;

import codedcosmos.specialisedtools.enums.Orientation;
import org.bukkit.entity.Player;

public class PlayerUtils {
	public static Orientation getCardinalDirection(Player player) {
		double rotation = (player.getLocation().getYaw() - 90) % 360;
		if (rotation < 0) {
			rotation += 360.0;
		}
		if (0 <= rotation && rotation < 45) {
			return Orientation.N;
		} else if (45 <= rotation && rotation < 135) {
			return Orientation.E;
		} else if (135 <= rotation && rotation < 225) {
			return Orientation.S;
		} else if (225 <= rotation && rotation < 315) {
			return Orientation.W;
		} else if (315 <= rotation && rotation < 360.0) {
			return Orientation.N;
		} else {
			return null;
		}
	}
}
