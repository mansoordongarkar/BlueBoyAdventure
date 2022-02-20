/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blueboyadventure;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author mansoordongarkar
 */
public class BlueBoyAdventure extends JFrame {

    public BlueBoyAdventure() throws HeadlessException {
        buildGUI();
    }

    private void buildGUI() {
        this.setTitle("Blue Boy Adventure");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(new Screen());
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BlueBoyAdventure::new);
    }
    
}
