package core.Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import classes.Player;
import core.Key.KeyHandler;
import core.Manager.TileManager;
import core.Physics.CollisionChecker;

public class GamePanel extends JPanel implements Runnable {
  
  final int originalTileSize = 16; // 16x16
  final int scale = 3; 

  public final int tileSize = originalTileSize * scale; // 48x48 tiles
  public final int maxScreenCol = 16; // numbers of columns
  public final int maxScreenRow = 12; // numbers of rows
  public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
  public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

  KeyHandler keyH = new KeyHandler();
  Thread gameThread;
  public TileManager tileM = new TileManager(this);
  public CollisionChecker collisionChecker = new CollisionChecker(this);
  public Player player = new Player(this, keyH);


  //World Settings 
  public final int maxWorldCol = 50;
  public final int maxWorldRow = 50;
  public final int worldWidth = tileSize * maxWorldCol; // 768 pixels
  public final int worldHeight = tileSize * maxWorldRow; // 576 pixels

  /*FPS*/
  int FPS = 60;

  public GamePanel(){
    this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void startGameThread(){
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run(){
    double drawInterval = 1000000000 / FPS; // 0.0166667 seconds
    double delta  = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    long drawCount = 0;
    
    while(gameThread != null){
      currentTime = System.nanoTime();
      delta += (currentTime - lastTime) / drawInterval;
      timer += currentTime - lastTime;
      lastTime = currentTime;
      if(delta >= 1){
        update();
        repaint();
        delta--;
        drawCount++;
      }

      if(timer >= 1000000000){
        System.out.println("FPS: " + drawCount);
        drawCount = 0;
        timer = 0;
      }

    }
  }

  public void update(){
    player.update();
  }

  public void paintComponent(Graphics g){

    super.paintComponent(g);
    // Convert Graphics to Graphics2D
    Graphics2D g2 = (Graphics2D) g;
    tileM.draw(g2);
    player.draw(g2);
    g2.dispose();

  }




}
