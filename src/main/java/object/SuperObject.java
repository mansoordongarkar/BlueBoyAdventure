/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import blueboyadventure.Screen;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author mansoordongarkar
 *
 * This is the parent class for all objects such as keys, doors etc. found along
 * the game map
 */
public class SuperObject {

    public BufferedImage image; // for holding the object image.
    public String name; // for holding the object name.
    public boolean collision = false; // for detecting collision status.
    public int worldX, worldY; // The Global X and Y co-ordinates for the objects
    public int screenX, screenY;

    public void draw(Graphics2D gg, Screen screen) {
        //determine where to draw with respect to player's position
        screenX = this.worldX - screen.player.worldXPosition + screen.player.screenXPosition;
        screenY = this.worldY - screen.player.worldYPositon + screen.player.screenYPosition;

        //condition to draw tiles on screen
        if (screenX + screen.tileSize >= 0
                && screenX < screen.getPreferredSize().getWidth()
                && screenY + screen.tileSize >= 0
                && screenY < screen.getPreferredSize().getHeight()) {
            gg.drawImage(image, screenX, screenY, screen.tileSize, screen.tileSize, screen);
        }
    }
}
