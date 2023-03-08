package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	int mapTileNum[][];
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];//creates 10 tiles.
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; //stores the numbers of our map txt file.
		getTileImage();
		loadMap("/maps/map.txt");
	}
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/walkway.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/water.png"));
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wood.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png"));
			
	}catch(IOException e) {
		e.printStackTrace();
	}	
}
	public void loadMap(String fp) {
		try {
			InputStream is = getClass().getResourceAsStream(fp);
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); //reads context of the text file.
			int col = 0;
			int row = 0;
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine(); //reads 1 line of the text file
				
				//While loop converts map file to tiles one column at a time.
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");//puts each number of the file into an array by spliting the string.
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col =0;
					row++;
				}
			}
			br.close();
	}catch(Exception e) {
		
	}	
	}
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol* gp.tileSize;
			int worldY = worldRow*gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			//Only renders tiles within the players view boundry;
			if(worldX +gp.tileSize > gp.player.worldX - gp.player.screenX && 
					worldX- gp.tileSize < gp.player.worldX + gp.player.screenX && 
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;
		
			//If the column reaches the end of the screen it will reset to 0, going back to the top and increase the row by one.
			if(worldCol==gp.maxWorldCol) {
				worldCol = 0;
				
				worldRow++;
				
			}
		}
	}
}
