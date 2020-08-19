package com.company;

import java.awt.*;
import java.util.Scanner;

/**
 * Main class where actual game play happens
 *
 * Sample dashboard:
 * - 1 2 3 4 5 6 7 8 9 10          - 1 2 3 4 5 6 7 8 9 10
 * A + + + + + + + + + +           A + + + + + + + + + +
 * B + + + + + + + + + +           B + + + + + + + + + +
 * C + + + + + + + + + +           C + + + + + + + + + +
 * D + + + + + + + + + +           D + + + + + + + + + +
 * E + + + + + + + + + +           E + + + + + + + + + +
 * F + + + + + + + + + +           F + + + + + + + + + +
 * G + + + + + + + + + +           G + + + + + + + + + +
 * H + + + + + + + + + +           H + + + + + + + + + +
 * I + + + + + + + + + +           I + + + + + + + + + +
 * J + + + + + + + + + +           J + + + + + + + + + +
 * */
public class Game {

    /**
     * Initialize 2 players as this is a two player game Ships for player 1 will be Black
     * in color whereas ships for player 2 will be Gray
    **/
    Player player1 = new Player(Color.BLACK,1);
    Player player2 = new Player(Color.GRAY,2);

    /**
     * This will reset everything to the beginning. All ships will be added to both players
     * ships arraylist.
    **/
    public void reset(){
        player1.getPlayerShips().clear();
        player2.getPlayerShips().clear();

        player1.getPlayerShips().add(Ship.Carrier());
        player1.getPlayerShips().add(Ship.Battleship());
        player1.getPlayerShips().add(Ship.Destroyer());
        player1.getPlayerShips().add(Ship.Submarine());
        player1.getPlayerShips().add(Ship.PatrolBoat());

        player2.getPlayerShips().add(Ship.Carrier());
        player2.getPlayerShips().add(Ship.Battleship());
        player2.getPlayerShips().add(Ship.Destroyer());
        player2.getPlayerShips().add(Ship.Submarine());
        player2.getPlayerShips().add(Ship.PatrolBoat());
    }

    /**
     * This is where the initial board is setup. Each player places there ships at the preferred
     * location. First player 1 places all their ships then player 2 places all their ships.
    **/
    public void setup() {
        reset();
        Scanner scan = new Scanner(System.in);
        System.out.println("--------- Player 1: Start placing your ships ---------\n\n");
        for (int i = 0; i < player1.getPlayerShips().size(); ){
            try{
                System.out.print("Player 1: Enter X coordinate for your " + player1.getPlayerShips().get(i).getName() + " ship (A-J): ");
                char x = scan.next().charAt(0);
                System.out.print("Player 1: Enter Y coordinate for your " + player1.getPlayerShips().get(i).getName() + " ship (1-10): ");
                int y = scan.nextInt();
                System.out.print("Player 1: Enter orientation for your " + player1.getPlayerShips().get(i).getName() + " ship (H/V): ");
                char o = scan.next().charAt(0);
                setLocations(player1,player1.getPlayerShips().get(i),x,y,o);
                i++;
            } catch (Exception e) {
                System.out.println("!!!!! Player 1: You can't place two or more ships on the same location !!!!!");
            }
        }

        System.out.println("\n\n--------- Player 2: Start placing your ships ---------\n\n");
        for (int i = 0; i < player2.getPlayerShips().size(); ){
            try{
                System.out.print("Player 2: Enter X coordinate for your " + player2.getPlayerShips().get(i).getName() + " ship: ");
                char x = scan.next().charAt(0);
                System.out.print("Player 2: Enter Y coordinate for your " + player2.getPlayerShips().get(i).getName() + " ship: ");
                int y = scan.nextInt();
                System.out.print("Player 2: Enter orientation for your " + player2.getPlayerShips().get(i).getName() + " ship: ");
                char o = scan.next().charAt(0);
                setLocations(player2,player2.getPlayerShips().get(i),x,y,o);
                i++;
            } catch (Exception e) {
                System.out.println("!!!!! Player 2: You can't place two or more ships on the same location !!!!!");
            }
        }
    }

    /**
     * Each player tries to guess the position of other players ship. This internally calls checkGuess
     * which will check if the guess was a Hit, Sink or Miss. Each player is expected to input the X and
     * Y coordinate of their guess. This will continue till all ships of any 1 player are sunk. Once all
     * ships of any one player are sunk this will call result method that will display the name of the winner.
    **/
    public void gamePlay() {
        while (!player1.getPlayerShips().isEmpty() && !player2.getPlayerShips().isEmpty()) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Player 1: Enter X coordinate (A-J): ");
            char x1 = scan.next().charAt(0);
            System.out.print("Player 1: Enter Y coordinate (1-10): ");
            int y1 = scan.nextInt();
            checkGuess(player2, x1, y1);
            System.out.println("\n-----------------------------------\n");
            System.out.print("Player 2: Enter X coordinate (A-J): ");
            char x2 = scan.next().charAt(0);
            System.out.print("Player 2: Enter Y coordinate (1-10): ");
            int y2 = scan.nextInt();
            checkGuess(player1, x2, y2);
            System.out.println("\n-----------------------------------\n");
        }
        result();
    }

    /**
     * This will display the outcome of the match. It will display the name of the player who won.
    **/
    public void result(){
        System.out.println("\n \n********** ********** ********** \n********** ********** **********");
        if(player1.getPlayerShips().isEmpty()) System.out.println("***** Player 2 is the winner *****");
        else if(player2.getPlayerShips().isEmpty()) System.out.println("***** Player 1 is the winner *****");
        System.out.println("********** ********** ********** \n********** ********** **********");
        player1.getDrawingPanel().exit();
    }

    /**
     * This will add the ships at the location given by the user. If there is a ship already
     * at the location or the index is out of bound this method will throw an exception.
    **/
    private void setLocations(Player player, Ship ship,char x,int y,char orientation) throws Exception{
        if(orientation!='H'&&orientation!='V'){
            throw new Exception("Invalid Entry");
        }
        int temp_x = convertToInt(x);
        int temp_y = y-1;
        for (int i = 0; i < ship.getSize(); i++){
            if(orientation=='V'){
                if(player.getLocations()[temp_x][temp_y] != null || temp_y >= 10){
                    throw new Exception("Invalid Entry"); //System.out.println("Invalid entry");
                    //return;
                }
                temp_x++;
            }
            else {
                if(player.getLocations()[temp_x][temp_y]!=null|| temp_x==10){
                    throw new Exception("Invalid Entry"); //System.out.println("Invalid entry");
                    //return;
                }
                temp_y++;
            }
        }
        for (int i = 0; i < ship.getSize(); i++) {

            if(orientation=='V'){
                player.getLocations()[convertToInt(x)][y-1]=ship.getName();
                player.getDrawingPanel().getDebuggingGraphics().setColor(player.getColor());
                player.getDrawingPanel().getDebuggingGraphics().fillRect((y-1)*50,convertToInt(x)*50,50,50);//Color.BLACK);
                x++;
            }
            else {
                player.getLocations()[convertToInt(x)][y-1]=ship.getName();
                player.getDrawingPanel().getDebuggingGraphics().setColor(player.getColor());
                player.getDrawingPanel().getDebuggingGraphics().fillRect((y-1)*50,convertToInt(x)*50,50,50);
                y++;
            }
        }
    }

    /**
     * Converts the row char index to corresponding int index
    **/
    public int convertToInt(char index) {
        switch (index) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            default:
                return 10;
        }
    }


    /**
     * This method checks if the coordinates given by the user were coordinates of Hit, Miss or Sink.
     * If there is a ship present at the location given as input and the same ship is present in the
     * adjacent boxes, it is a Hit. If there is a ship name present in the location given as input but
     * it is not present in any other cell, it is a Sink. If the location given is empty, it is a Miss.
    **/
    private void checkGuess(Player player, char x, int y){
        String shipName=player.getLocations()[convertToInt(x)][y-1];
        if(player.getLocations()[convertToInt(x)][y-1]==null){
            System.out.println("\nIt was a miss.");
        }
        else{
            player.getAlreadyHit().add(shipName);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    boolean sameCell=(i==convertToInt(x) && j==y-1);
                    if(player.getLocations()[i][j]!=null && !sameCell &&player.getLocations()[i][j].equals(shipName)){
                        player.getLocations()[convertToInt(x)][y-1]=null;
                        player.getDrawingPanel().getDebuggingGraphics().setColor(Color.RED);
                        player.getDrawingPanel().getDebuggingGraphics().fillRect((y-1)*50,convertToInt(x)*50,50,50);//Color.BLACK);
                        System.out.println("\nIt was a hit!");
                        return;
                    }
                }
            }
            sunkShip(player,shipName);
            player.getLocations()[convertToInt(x)][y-1]=null;
            player.getDrawingPanel().getDebuggingGraphics().setColor(Color.RED);
            player.getDrawingPanel().getDebuggingGraphics().fillRect((y-1)*50,convertToInt(x)*50,50,50);
            System.out.println("\nA ship has sunk!");
        }
    }

    /**
     * Removes a sunk ship from the players ship list
    **/
    private void sunkShip(Player player, String name){
        player.getPlayerShips().removeIf(ship -> ship.getName().equals(name));
    }

    /**
     * Main method to play the game.
    * */
    public static void main(String[] args) {
        Game game = new Game();
        game.setup();

        System.out.println("\n-------------------------------------");
        System.out.println("Ships are placed, start guessing now.");
        System.out.println("-------------------------------------\n");

        game.gamePlay();
    }
}
