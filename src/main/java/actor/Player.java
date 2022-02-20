/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actor;

import blueboyadventure.Screen;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author mansoordongarkar
 */
public class Player extends Actor {

    Screen screen;
    BufferedImage upOne, upTwo, downOne, downTwo, leftOne, leftTwo, rightOne, rightTwo, img1, img2;
    boolean keyPressed;
    int spriteCounter; // for step animation of player
    //public final int screenXPosition, screenYPosiiton;

    public Player(Screen screen) {
        this.screen = screen;
        this.worldXPosition = 8 * screen.tileSize;
        this.worldYPositon = 6 * screen.tileSize;
        //System.out.println("original world Y Pos "+this.worldYPositon / screen.tileSize);
        this.screenXPosition = (int) ((this.screen.getPreferredSize().getWidth() - this.screen.tileSize) / 2);
        this.screenYPosition = (int) ((this.screen.getPreferredSize().getHeight() - this.screen.tileSize) / 2);
        this.speed = 5;
        this.spriteCounter = 0;
        this.solidArea = new Rectangle(this.worldXPosition, this.worldYPositon, screen.tileSize, screen.tileSize);
        loadImage();
        img1 = downOne;
    }

    private void loadImage() {
        try {
            upOne = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/player/boy_up_1.png").getFile()));
            //upOne = ImageIO.read(getClass().getResourceAsStream("images/player/boy_up_1.png"));
            upTwo = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/player/boy_up_2.png").getFile()));
            downOne = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/player/boy_down_1.png").getFile()));
            downTwo = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/player/boy_down_2.png").getFile()));
            leftOne = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/player/boy_left_1.png").getFile()));
            leftTwo = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/player/boy_left_2.png").getFile()));
            rightOne = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/player/boy_right_1.png").getFile()));
            rightTwo = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/player/boy_right_2.png").getFile()));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Image loaded successfully");
    }

    public void draw(Graphics2D g) {
        BufferedImage imgTmp = img1;
        this.spriteCounter++;
        if (this.keyPressed) {
            if (this.spriteCounter <= 5) {
                imgTmp = img1;
                //this.spriteCounter++;
            } else if (this.spriteCounter > 5 && this.spriteCounter <= 10) {
                imgTmp = img2;
                //this.spriteCounter++;
            } else {
                imgTmp = img1;
                this.spriteCounter = 0;
            }

        } else {
            //will decide later
        }
        g.drawImage(imgTmp, this.screenXPosition, this.screenYPosition, screen.tileSize, screen.tileSize, screen);

    }

    public void move(boolean keyPressed, int xDirection, int yDirection) {
        this.keyPressed = keyPressed;
        //this.speed++;
        if (xDirection == 0 && yDirection == -1) {
            // moving up
            img1 = upOne;
            img2 = upTwo;
            if (!checkCollision(this.screen, this, "Up") && !checkObjectCollision(this.screen, this, "Up")) {
                    this.worldYPositon -= this.speed;    
            } else {
                this.keyPressed = false;
            }

        } else if (xDirection == 0 && yDirection == 1) {
            // moving down
            img1 = downOne;
            img2 = downTwo;
            if (!checkCollision(this.screen, this, "Down") && !checkObjectCollision(this.screen, this, "Down")) {
                this.worldYPositon += this.speed;
            } else {
                this.keyPressed = false;
            }

        } else if (xDirection == -1 && yDirection == 0) {
            // moving left
            img1 = leftOne;
            img2 = leftTwo;
            if (!checkCollision(this.screen, this, "Left") && !checkObjectCollision(this.screen, this, "Left")) {
                this.worldXPosition -= this.speed;
            } else {
                this.keyPressed = false;
            }

        } else if (xDirection == 1 && yDirection == 0) {
            //moving Right
            img1 = rightOne;
            img2 = rightTwo;
            if (!checkCollision(this.screen, this, "Right") && !checkObjectCollision(this.screen, this, "Right")) {
                this.worldXPosition += this.speed;
            } else {
                this.keyPressed = false;
            }

        } else {
            this.spriteCounter = 0;
            //this.speed = 5;
        }
    }

}
