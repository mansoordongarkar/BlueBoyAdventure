/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import blueboyadventure.Screen;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author mansoordongarkar
 */
public class Obj_Key extends SuperObject{
    
    Screen screen;

    public Obj_Key(Screen screen) {
        this.screen = screen;
        this.name = "Key";
        try {
            this.image = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/objects/key.png").getFile()));
        } catch (IOException ex) {
            Logger.getLogger(Obj_Key.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
