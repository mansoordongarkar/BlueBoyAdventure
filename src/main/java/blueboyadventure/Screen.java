/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blueboyadventure;

import actor.Player;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import object.ObjectManager;
import tile.TileManager;

/**
 *
 * @author mansoordongarkar
 */
public class Screen extends JComponent implements KeyListener, Runnable {

    final int originalTileSize = 16;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    final int scale = 3;
    public final int tileSize = scale * originalTileSize;
    final int fps = 60;

    int xPos = 100;
    int yPos = 100;
    long timer = 0;
    int frameCtr = 0;

    Rectangle2D.Double screenRectangle;
    Image img;
    Thread gameThread;
    KeyEvent keyEvent;

    public Player player;
    public TileManager tileManager;
    public ObjectManager objectManager;

    public Screen() {
        this.gameThread = new Thread(this);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(this);
        //this.keyEvent = null;
        timer = System.currentTimeMillis();

        //initialising actors;
        player = new Player(this);
        tileManager = new TileManager(this);
        objectManager = new ObjectManager(this);

        this.gameThread.start();
    }

    @Override
    public Dimension getPreferredSize() {
        int screenWidth = (int) (this.maxScreenCol * this.tileSize);
        int screenHeight = (int) (this.maxScreenRow * this.tileSize);
        screenRectangle = new Rectangle2D.Double(0, 0, screenWidth, screenHeight);
        return new Dimension(screenWidth, screenHeight);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //checkFrameCtr(); just to check on frame rates
        Graphics screenGraphics = g;
        img = this.createImage((int) this.getPreferredSize().getWidth(), (int) this.getPreferredSize().getHeight());
        g = this.img.getGraphics();
        draw(g);
        screenGraphics.drawImage(img, 0, 0, null);
    }

    private void draw(Graphics g) {
        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setColor(new Color(0, 128, 0));
        gg.fill(screenRectangle);
        //gg.drawImage(tileManager.tile[1].image, 0, 0, (int)this.screenRectangle.getWidth(), (int)this.screenRectangle.getHeight(), this);
        tileManager.draw(gg);
        objectManager.draw(gg);
        player.draw(gg);
        

        gg.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyEvent = e;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keyEvent = null;   
    }

    @Override
    public void run() {
        long drawInterval = 1000000000 / fps;
        long nextDrawTime = System.nanoTime() + drawInterval;

        while (this.gameThread != null) {
            //System.out.println("In a loop");
            repaint();
            update();

            try {
                long remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep(remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void checkFrameCtr() {
        //System.out.println(timer);
        long tmp = System.currentTimeMillis() - timer;
        //System.out.println(tmp);
        if (tmp <= 1000) {
            frameCtr++;
        } else {
            System.out.println(frameCtr);
            frameCtr = 0;
            timer = System.currentTimeMillis();
        }
    }

    private void update() {
        if (this.keyEvent != null) {
            switch (this.keyEvent.getKeyCode()) {

                case (KeyEvent.VK_UP) -> {
                    //code for up arrow key pressed
                    //this.yPos -= this.speed;
                    player.move(true, 0, -1);
                }

                case (KeyEvent.VK_DOWN) -> {
                    //code for down arrow key pressed
                    //this.yPos += this.speed;
                    player.move(true, 0, 1);
                }

                case (KeyEvent.VK_LEFT) -> {
                    //code for left arrow key pressed
                    //this.xPos -= this.speed;
                    player.move(true, -1, 0);
                }

                case (KeyEvent.VK_RIGHT) -> {
                    //code for right arrow key pressed
                    //this.xPos += this.speed;
                    player.move(true, 1, 0);
                }
                
            }
        }
        else{
            player.move(false, 0, 0);
        }
    }

}
