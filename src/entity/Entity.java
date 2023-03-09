package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//Used to store variables that will be used by players, NPCs, mobs, etc.
public class Entity {
	public int worldX, worldY; //represents the player position on the world map.
	
	public int speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea; //creates a rectangle that is invisible to the player, will be for player collision settings. 
	public boolean collisionOn = false;
}
