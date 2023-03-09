package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;
public class GamePanel extends JPanel implements Runnable{
	
	// Screen Settings
	final int originalTileSize = 16; // 16 x 16 tile. default size
	final int scale = 3; //Scale the small tile to look larger on big screens. 16x3=48
	
	public final int tileSize = originalTileSize * scale; //Accessed by Player and entity package so public.
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768px
	public final int screenHeight = tileSize * maxScreenRow; //576px
	
	
	//Map settings
	public final int maxWorldCol=50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	int FPS = 60;
	KeyHandler keyH = new KeyHandler();
	Thread gameThread; //Will keep game time, can be started and stopped. 
	public CollisionCheck colCheck = new CollisionCheck(this); 
	public Player player = new Player(this, keyH);
	public AssetSetter aSet = new AssetSetter(this);
	public SuperObject obj[] = new SuperObject[10];
	TileManager tileMan = new TileManager(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true); //Allows the game panel to be focused by the keyboard
	}
	public void setupGame() {
		aSet.setObject();
	}
	public void startGameThread() {
		gameThread = new Thread(this); //Passing game panel class into thread constructor
		gameThread.start(); //Starts thread, calls run method.
	}
	//Calls when a thread is created. Game loop. 
	public void run() {
		double drawInterval = 1000000000/FPS; //1 Second / 60. 60 FPS
		double nextDrawTime = System.nanoTime() + drawInterval; //Ensure draw happens after update
		while(gameThread != null) { //loop repeats as long as the game thread has not been stopped.
			long currentTime = System.nanoTime(); // Returns the value of the run JVM time in nano sec.
			//Update Info. IE position of the character
			update();
			//Draw Screen with updated info.
			repaint(); //Calls the paintComponent method.
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();// Sleep the loop to ensure it is only 60 FPS;
				remainingTime = remainingTime / 1000000; //Convert remainingTime to milliseconds to match Thread.sleep params.
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval; 
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		
	}
	public void update() {
		player.update();
	}
	//Default method from Java. Graphics is a class that has functions to draw objects on the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; //Graphics 2D extends the Graphics class and provides better control over geometry, colors and text, etc.
		//Draw Tiles
		tileMan.draw(g2);
		
		//Draw Objects
		for(int i =0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		//Draw Player
		player.draw(g2);
		g2.dispose(); //Dispose of the graphics context and release any system resources that it is using. Good for memory management.
	}
}
