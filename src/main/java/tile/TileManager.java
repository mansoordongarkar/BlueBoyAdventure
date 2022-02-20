/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile;

import blueboyadventure.Screen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author mansoordongarkar
 */
public class TileManager {

    Screen screen;
    public Tile[] tile;
    public int[][] map;

    final int worldRows = 50;
    final int worldColumns = 50;
    Dimension worldDimension;

    public TileManager(Screen screen) {
        this.screen = screen;
        tile = new Tile[10];
        this.worldDimension = new Dimension(this.worldRows * screen.tileSize, this.worldColumns * screen.tileSize);
        loadImage();
        map = buildMap("Map_World.csv");
    }

    private void loadImage() {

        try {
            tile[0] = new Tile();
            //this is the earth grass
            tile[0].image = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/tile/earth.png").getFile())); // this is the earth tile

            tile[1] = new Tile();
            //this is the grass tile
            tile[1].image = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/tile/grass.png").getFile())); // this is the grass tile

            tile[2] = new Tile();
            //this is the sand tile
            tile[2].image = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/tile/sand.png").getFile())); // this is the sand tile

            tile[3] = new Tile();
            //this is the tree tile
            tile[3].image = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/tile/tree.png").getFile())); // this is the tree tile
            tile[3].collision = true;

            tile[4] = new Tile();
            //this is the wall tile
            tile[4].image = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/tile/wall.png").getFile())); // this is the wall tile
            tile[4].collision = true;

            tile[5] = new Tile();
            //this is the water tile
            tile[5].image = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource("images/tile/water.png").getFile())); // this is the water tile
            tile[5].collision = true;

        } catch (IOException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void draw(Graphics2D g) {
        for (int row = 0; row < this.worldRows; row++) {
            for (int column = 0; column < this.worldColumns; column++) {
                //determine tile to draw
                int tileNum = map[row][column];
                
                //determine where to draw with respect to player's position
                int xPos = (column * screen.tileSize) - screen.player.worldXPosition + screen.player.screenXPosition;
                int yPos = (row * screen.tileSize) - screen.player.worldYPositon + screen.player.screenYPosition;
                
                //condition to draw tiles on screen
                if (xPos + screen.tileSize >= 0
                    && xPos < screen.getPreferredSize().getWidth()
                    && yPos + screen.tileSize >= 0
                    && yPos < screen.getPreferredSize().getHeight()) {
                    g.drawImage(tile[tileNum].image, xPos, yPos, screen.tileSize, screen.tileSize, screen);
                }
            }
        }
    }

    private int[][] buildMap(String mapFile) {
        map = new int[this.worldRows][this.worldColumns];

        try (BufferedReader mapInputFile = new BufferedReader(new FileReader(new File(ClassLoader.getSystemClassLoader().getResource("map/" + mapFile).getFile())))) {

            for (int row = 0; row < this.worldRows; row++) {
                String line = mapInputFile.readLine();
                String[] num = line.split(",");
                for (int column = 0; column < this.worldColumns; column++) {
                    map[row][column] = Integer.valueOf(num[column]);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

}
