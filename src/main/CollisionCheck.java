package main;

import entity.Entity;

public class CollisionCheck {
	GamePanel gp;
	public CollisionCheck(GamePanel gp) {
		this.gp  = gp;
	}
	public void checkTile(Entity entity) {
		//Cords of where the rectangle is in the world.
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		//only need to consider two tiles when checking if it is a collision, dependent on the players current direction.
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileMan.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileMan.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileMan.tile[tileNum1].collision || gp.tileMan.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileMan.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileMan.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileMan.tile[tileNum1].collision || gp.tileMan.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileMan.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileMan.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileMan.tile[tileNum1].collision || gp.tileMan.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileMan.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileMan.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileMan.tile[tileNum1].collision || gp.tileMan.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		}
	}
}
