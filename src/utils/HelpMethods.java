package utils;

import java.awt.geom.Rectangle2D;
import main.Game;

public class HelpMethods {
	public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if (!isSolid(x, y, lvlData))
			if (!isSolid(x + width, y + height, lvlData))
				if (!isSolid(x + width, y, lvlData))
					if (!isSolid(x, y + height, lvlData))
						return true;
		return false;

	}

	private static boolean isSolid(float x, float y, int[][] lvlData) {
		int maxWidth = lvlData[0].length * Game.TILES_SIZE;
		if (x < 0 || x >= maxWidth) {
			return true;
		}
		if (y < 0 || y >= Game.GAME_HEIGHT) {
			return true;
		}

		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;

		int value = lvlData[(int) yIndex][(int) xIndex];

		if (value >= 48 || value < 0 || value != 11) {
			return true;
		} else {
			return false;
		}

	}

	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitBox, Float xSpeed) {
		int currentTile = (int) hitBox.x / Game.TILES_SIZE;
		if (xSpeed > 0) {
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffset = (int) (Game.TILES_SIZE - hitBox.width);
			return tileXPos + xOffset -1;
		} else {
			return currentTile * Game.TILES_SIZE;
		}

	}

	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitBox, Float airSpeed) {
		int currentTile = (int) hitBox.y / Game.TILES_SIZE;
		if (airSpeed > 0) {
			// Falling
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int) (Game.TILES_SIZE - hitBox.height);
			return tileYPos + yOffset - 1;
		} else {
			// Jumping
			return currentTile * Game.TILES_SIZE;
		}
	}

	public static boolean IsEntityOnFloor(Rectangle2D.Float hitBox, int[][] lvlData) {
		// Check the pixel bottom left & bottom right
		if (!isSolid(hitBox.x, hitBox.y + hitBox.height + 1, lvlData)) {
			if (!isSolid(hitBox.x + hitBox.width, hitBox.y + hitBox.height + 1, lvlData)) {
				return false;
			}
		}
		return true;
	}
}
