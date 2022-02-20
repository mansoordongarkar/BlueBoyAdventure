/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import blueboyadventure.Screen;
import java.awt.Graphics2D;

/**
 *
 * @author mansoordongarkar
 */
public class ObjectManager {

    Screen screen;
    public SuperObject[] object;

    public ObjectManager(Screen screen) {
        this.screen = screen;
        object = new SuperObject[10];
        setUpObject();
    }

    private void setUpObject() {
        //steup objects along with their positioning on the game map...
        object[0] = new Obj_Key(this.screen);
        object[0].worldX = 1 * this.screen.tileSize;
        object[0].worldY = 30 * this.screen.tileSize;
        
        object[1] = new Obj_Key(this.screen);
        object[1].worldX = 28 * this.screen.tileSize;
        object[1].worldY = 17 * this.screen.tileSize;
        
        object[2] = new Obj_Key(this.screen);
        object[2].worldX = 26 * this.screen.tileSize;
        object[2].worldY = 44 * this.screen.tileSize;
        
        object[3] = new Obj_Door(this.screen);
        object[3].collision = true;
        object[3].worldX = 29 * this.screen.tileSize;
        object[3].worldY = 18 * this.screen.tileSize;
        
        object[4] = new Obj_Door(this.screen);
        object[4].collision = true;
        object[4].worldX = 32 * this.screen.tileSize;
        object[4].worldY = 44 * this.screen.tileSize;
        
        object[5] = new Obj_Door(this.screen);
        object[5].collision = true;
        object[5].worldX = 15 * this.screen.tileSize;
        object[5].worldY = 4 * this.screen.tileSize;
        
        object[6] = new Obj_Chest(this.screen);
        object[6].worldX = 12 * this.screen.tileSize;
        object[6].worldY = 2 * this.screen.tileSize;
        
        
    }

    public void draw(Graphics2D gg) {
        for (var obj : object) {
            if (obj != null) {
                obj.draw(gg, screen);
            }
        }
    }

}
