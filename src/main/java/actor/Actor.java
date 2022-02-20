/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actor;

import blueboyadventure.Screen;
import java.awt.Rectangle;

/**
 *
 * @author mansoordongarkar
 */
public class Actor {
    //this is the abstract class for all actor elements of the game.

    public int worldXPosition, worldYPositon, speed, screenXPosition, screenYPosition;
    public Rectangle solidArea; // this defines the solid area for entity which will be used to detect collisions

    public boolean checkCollision(Screen screen, Actor actor, String direction) {
        //determine current position of actor on world map
        int worldX = actor.worldXPosition + (screen.tileSize / 2);
        int worldY = actor.worldYPositon + (screen.tileSize / 2);
        //System.out.println("world x "+worldX);
        //System.out.println("world y "+worldY);

        //based on direction what would be the new position
        switch (direction) {
            case "Up" ->
                worldY = worldY - actor.speed - (screen.tileSize / 2);
            case "Down" ->
                worldY = worldY + actor.speed + screen.tileSize - (screen.tileSize / 2);
            case "Left" ->
                worldX = worldX - actor.speed - (screen.tileSize / 2);
            case "Right" ->
                worldX = worldX + actor.speed + screen.tileSize - (screen.tileSize / 2);
        }

        //find the corresponding row and col for the said world position
        int row = worldY / screen.tileSize < 0 ? 0 : worldY / screen.tileSize;//rows cannot be negative
        int col = worldX / screen.tileSize < 0 ? 0 : worldX / screen.tileSize;//cols cannot be negative

        //find the tile corresponding to this row and col matrix
        int tileNum = screen.tileManager.map[row][col];

        if (row == 0 || col == 0) {
            return true;
        } else {
            return screen.tileManager.tile[tileNum].collision;
        }
    }

    public boolean checkObjectCollision(Screen screen, Actor actor, String direction) {
        //System.out.println("in Object Collision");
        boolean returnVal = false;

        //determine current position of actor on world map
        int worldX = actor.worldXPosition + (screen.tileSize / 2);
        int worldY = actor.worldYPositon + (screen.tileSize / 2);

        //based on direction what would be the new position
        switch (direction) {
            case "Up" ->
                worldY = worldY - actor.speed - (screen.tileSize / 2);
            case "Down" ->
                worldY = worldY + actor.speed + screen.tileSize - (screen.tileSize / 2);
            case "Left" ->
                worldX = worldX - actor.speed - (screen.tileSize / 2);
            case "Right" ->
                worldX = worldX + actor.speed + screen.tileSize - (screen.tileSize / 2);
        }

        //check if the actor position is on the object posiiton
        for (int i = 0; i < screen.objectManager.object.length; i++) {
            if (screen.objectManager.object[i] != null
                    && (screen.objectManager.object[i].worldX / screen.tileSize == worldX / screen.tileSize)
                    && (screen.objectManager.object[i].worldY / screen.tileSize == worldY / screen.tileSize)) {
                //System.out.println("reached");
                switch (screen.objectManager.object[i].name) {
                    case "Key":
                        returnVal = screen.objectManager.object[i].collision;
                        if (i == 0) {
                            screen.objectManager.object[4].collision = false;
                        } else if (i == 1) {
                            screen.objectManager.object[5].collision = false;
                        } else if (i == 2) {
                            screen.objectManager.object[3].collision = false;
                        }
                        screen.objectManager.object[i] = null;
                        break;
                    case "Door":
                        returnVal = screen.objectManager.object[i].collision;
                        if (returnVal == false) {
                            screen.objectManager.object[i] = null;
                        }
                        break;
                    case "Chest" :
                        screen.objectManager.object[i] = null;
                        break;
                }
            }
        }

        return returnVal;
    }
}
