package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	//Indicate where on the screen the player will be displayed.
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2); //divided by two to represent the halfway point of the screen and place the character in the center at all times. since it is final it will not change and the player will always be in the center. 
		screenY=gp.screenHeight/2 -  (gp.tileSize/2);
		setDefaultValues();
		getPlayerImage();
		direction = "down";
	}
	public void setDefaultValues() {
		//Starts player close to the middle of the map
		worldX = gp.tileSize * 23;
		worldY=gp.tileSize * 21;
		speed = 4;
	}
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/Player/p1up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/Player/p1up2.png"));
		down1 = ImageIO.read(getClass().getResourceAsStream("/Player/p1down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/Player/p1down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/Player/p1left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/Player/p1left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/Player/p1right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/Player/p1right2.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() { //Called 60 times per second from game loop.
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
				worldY -= speed;
			}
			else if(keyH.downPressed == true) {
				direction = "down";
				worldY += speed;
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
				worldX -=speed;
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
				worldX += speed;
			}
			
			spriteCounter++;
			//Every 10 frames the sprite changes to give an animation.
			if(spriteCounter > 10) {
				if(spriteNum ==1) {
					spriteNum =2;
				}
				else if(spriteNum==2) {
					spriteNum =1;
				}
				spriteCounter = 0;
			}
		}
		}
		
	public void draw(Graphics2D g2) {
		BufferedImage image =null;
		switch(direction) {
		case  "up":
			if(spriteNum ==1) {
				image = up1;
			}
			if(spriteNum ==2) {
				image = up2;
			}
			break;
		case  "down":
			if(spriteNum ==1) {
				image = down1;
			}
			if(spriteNum ==2) {
				image = down2;
			}
			break;
		case  "left":
			if(spriteNum ==1) {
				image = left1;
			}
			if(spriteNum ==2) {
				image = left2;
			}
			break;
			
		case  "right":
			if(spriteNum ==1) {
				image = right1;
			}
			if(spriteNum ==2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);
	
	}
	}

