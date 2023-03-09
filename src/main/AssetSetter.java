package main;

import object.Door;
import object.Key;

public class AssetSetter {
	GamePanel gp; 
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Key();
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		gp.obj[1] = new Key();
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 23 * gp.tileSize;
		gp.obj[2] = new Door();
		gp.obj[2].worldX = 25 * gp.tileSize;
		gp.obj[2].worldY = 25 * gp.tileSize;
	}
}
