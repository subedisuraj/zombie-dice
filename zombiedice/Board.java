/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package zombiedice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author surajs
 */
public class Board {

    boolean gameOver;

    int noOfPlayers;

    List<Player> players = new ArrayList<>();

    Player currentPlayer;
    int turnIdx;

    Roll newroll, lastroll;

    Board() {
        turnIdx = 0;
    }

    void initialize() {
        gameOver = false;

    }

    void gameplay() {
        System.out.println("ZOMBIE DICE");

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the number of players:");
        noOfPlayers = reader.nextInt();

        for (int i = 1; i <= noOfPlayers; i++) {
            Scanner namereader = new Scanner(System.in);
            System.out.println("Enter player " + i + " name:");
            String name = namereader.next();
            Player p = new Player(name);
            players.add(p);
        }

        currentPlayer = players.get(turnIdx);
        displayScore();

        Scanner inputreader = new Scanner(System.in);
        String userinput;
        while (!gameOver) {
            currentPlayer.runnerCount = 0;
            System.out.println("_______________________________________________________________________");
            System.out.println("Current Player is: " + currentPlayer.name);
            System.out.println("\nPress P to pick the dice.");
            userinput = inputreader.next();
            if (userinput.equalsIgnoreCase("P")) {
                boolean dead = false;
                boolean allrunner = false;
                newroll = currentPlayer.selectDicetoRoll();
                System.out.println("\nYour dice for this roll are: ");
                displaySelectedDice();
                while (!userinput.equalsIgnoreCase("R")) {
                    System.out.println("\nPress R to roll the dice. ");
                    userinput = inputreader.next();
                    if (userinput.equalsIgnoreCase("R")) {
                        currentPlayer.rollDice(newroll);
                        displayDiceValues();
                        currentPlayer.updateBoard();

                        dead = checkShotsLimitCrossed();
                      

                        gameOver = checkGameOver();
                       

                        allrunner = checkRunnerCount();
                       
                        displayScore();
                          if (dead) {
                            changeTurn();
                        }
                           if (allrunner) {
                            changeTurn();
                        }
                            if (gameOver) {
                            System.out.println("GAME OVER");
                            System.out.println(currentPlayer.name + " is the winner.");
                        }
                        Scanner continueoption = new Scanner(System.in);
                        if (!dead) {
                            while (true) {
                                System.out.println("\nDo you want to continue? Y / N");
                                String continueTurn = continueoption.next();
                                if (continueTurn.equalsIgnoreCase("N")) {
                                    changeTurn();
                                    break;
                                }
                                if (continueTurn.equalsIgnoreCase("Y")) {
                                    break;
                                }
                                
                            }
                        }
                    }
                }
            }
        }

    }

    void displayScore() {
        System.out.println("\n\n\t\t\tS C O R E B O A R D\n");
        System.out.println("Players  \t\tTotal Brains Eaten \t\tShotguns Taken");
        for (int i = 0; i < noOfPlayers; i++) {
            System.out.println(players.get(i).name + "\t\t\t\t" + players.get(i).brainCount + "\t\t\t\t" + players.get(i).shotsCount);

        }
        System.out.println("\n");
    }

    private void displaySelectedDice() {
        for (int i = 0; i < 3; i++) {
            System.out.println(currentPlayer.newroll.dc.get(i).getColour());
        }
        System.out.print("\n");
    }

    private void displayDiceValues() {

        for (int i = 0; i < 3; i++) {
            System.out.println(currentPlayer.newroll.dc.get(i).getColour() + "\t" + currentPlayer.newroll.dc.get(i).getDiceOutput());
        }
        System.out.println("\n");

    }

    private boolean checkShotsLimitCrossed() {
        if (currentPlayer.shotsCount >= 3) {
            System.out.println("\nYou were shot 3 times. You are dead. Next player's turn.");
            currentPlayer.brainCount = 0;
            return true;
        }
        return false;
    }

    private void changeTurn() {

        currentPlayer.shotsCount = 0;

        turnIdx = (turnIdx + 1) % noOfPlayers;
        currentPlayer = players.get(turnIdx);
    }

    private boolean checkRunnerCount() {
        if (currentPlayer.runnerCount >= 3) {
            System.out.println("All your targets ran away. Next player's turn.");
            return true;
        }
        return false;
    }

    private boolean checkGameOver() {
        if (currentPlayer.brainCount >= 13) {
            return true;
        }
        return false;
    }

}
