package com.company;

/**
* Ship class: It has ship attributes like name and size.
* It also has the definition for the 5 ships used during game play:
* Carrier, Battleship, Destroyer, Submarine, Patrol Ship
 **/

public class Ship {

    private String name;
    private int size;

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }


    public static Ship Carrier(){
        return new Ship("Carrier",5);
    }

    public static Ship Battleship(){
        return new Ship("Battleship",4);
    }

    public static Ship Destroyer(){
        return new Ship("Destroyer",3);
    }

    public static Ship Submarine(){
        return new Ship("Submarine",3);
    }

    public static Ship PatrolBoat(){
        return new Ship("Patrol Boat",2);
    }
}

