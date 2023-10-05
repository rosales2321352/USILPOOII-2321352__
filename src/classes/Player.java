package classes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.Entity.Entity;
import core.Key.KeyHandler;
import core.Panel.GamePanel;

public class Player extends Entity {
  
  GamePanel gamePanel;
  KeyHandler keyH;

  public final int screenX;
  public final int screenY;

  public Player(GamePanel gamePanel, KeyHandler keyH) {
    this.gamePanel = gamePanel;
    this.keyH = keyH;

    screenX = gamePanel.screenWidth /2 - (gamePanel.tileSize/2);
    screenY = gamePanel.screenHeight /2 - (gamePanel.tileSize/2);

    solidArea = new Rectangle();
    solidArea.x = 8;
    solidArea.y = 16;
    solidArea.width = 20;
    solidArea.height = 20;
    

    setDefaultValues(); 
    getPlayerImage();
  }

  public void setDefaultValues(){
    worldX = gamePanel.tileSize * 23;
    worldY = gamePanel.tileSize * 21;
    speed = 4;
    direction = "down";
  }


  public void getPlayerImage(){
    try{
      up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_01_left.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_02_left.png"));
      up3 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_03_left.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_01_down_left.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_02_down_left.png"));
      down3 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_03_down_left.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_01_left.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_02_left.png"));
      left3 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_03_left.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_01_right.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_02_right.png"));
      right3 = ImageIO.read(getClass().getResourceAsStream("/res/player/duck_03_right.png"));
    }catch (IOException e){
      e.printStackTrace();
    }
  }

  public void update(){
    if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
      if(keyH.upPressed == true){
        direction = "up";
      }else if(keyH.downPressed == true){
        direction = "down";
      }else if(keyH.leftPressed == true){
        direction = "left";
      }else if(keyH.rightPressed == true){
        direction = "right";
      }

      collisionOn = false;
      gamePanel.collisionChecker.checkTile(this);

      if(collisionOn == false){
        switch(direction){
          case "up": worldY -= speed; break;
          case "down": worldY += speed; break;
          case "left": worldX -= speed; break;
          case "right": worldX += speed; break;
        }
      }

      spriteCounter ++;
      if(spriteCounter > 12){
        if(spriteNum == 1){
          spriteNum = 2;
        }else if(spriteNum == 2){
          spriteNum = 3;
        }else if(spriteNum == 3){
          spriteNum = 1;
        }
        spriteCounter = 0;
      }
    }

    
  }
  public void draw(Graphics2D g2){

    BufferedImage image = null;
    switch(direction){
      case "up":
        if(spriteNum == 1){
          image = down1;  
        }
        if(spriteNum == 2){
          image = down2;
        }
        if(spriteNum == 3){
          image = down3;
        }
        break;
      case "down":
        if(spriteNum == 1){
          image = up1;   
        }
        if(spriteNum == 2){
          image = up2;
        }
        if(spriteNum == 3){
          image = up3;
        }
        break;
      case "left":
        if (spriteNum == 1){
          image = left1;  
        }
        if(spriteNum == 2){
          image = left2;
        }
        if(spriteNum == 3){
          image = left3;
        }
        break;
      case "right":
        if(spriteNum == 1){
          image = right1;
        }
        if(spriteNum == 2){
          image = right2;
        }
        if(spriteNum == 3){
          image = right3;
        }
        break;
    }

    g2.drawImage(image, screenX,screenY, gamePanel.tileSize, gamePanel.tileSize, null);

  }

}
