package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    /**
    * Player object to store available ships, already hit ships
    * and location of each ship stored on the board for each player.
    **/
    private ArrayList<Ship> playerShips = new ArrayList<>();
    private ArrayList<String> alreadyHit = new ArrayList<>();
    private String[][] locations = new String[10][10];
    private DrawingPanel drawingPanel;// = new DrawingPanel();
    private Color color;


    /**
     * Constructor for player class
     *
     * This will create the 10x10 drawingPanel with grid lines
     * for each player and will initialize the board with blue color
     **/
    public Player(Color color, int number) {
        drawingPanel = new DrawingPanel();
        drawingPanel.setHeight(500);
        drawingPanel.setBackground(Color.BLUE);
        drawingPanel.setGridLines(true,50);
        this.color = color;
    }

    public ArrayList<Ship> getPlayerShips() {
        return playerShips;
    }

    public void setPlayerShips(ArrayList<Ship> playerShips) {
        this.playerShips = playerShips;
    }

    public ArrayList<String> getAlreadyHit() {
        return alreadyHit;
    }

    public void setAlreadyHit(ArrayList<String> alreadyHit) {
        this.alreadyHit = alreadyHit;
    }

    public String[][] getLocations() {
        return locations;
    }

    public void setLocations(String[][] locations) {
        this.locations = locations;
    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public void setDrawingPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
