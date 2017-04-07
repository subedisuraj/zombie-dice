/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author surajs
 */
public class Die {

    int colour; //1 = green, 2 = yellow, 3= red
    int value; //1= brain; 2= runner; 3 = shot
    boolean inCup;
    
    final int[]  greenDie = {1,1,1,2,2,3};
    final int[]  yellowDie = {1,1,2,2,3,3};
    final int[]  redDie = {1,2,2,3,3,3};
    

    
    Die()
    {}
    
    Die(int c) {
        inCup =true;
        colour = c;
    }

    boolean isRunner() {
        if (value == 2) {
            return true;
        }
        return false;
    }
    
    String getColour()
    {
        switch(colour)
        {
            case 1:
                return "Green";
            case 2:
                return "Yellow";
            case 3:
                return "Red";
            default:
                return "";
        }
        
    }
    
    String getDiceOutput()
    {
        switch(value)
        {
            case 1:
                return "Brain";
            case 2:
                return "Runner";
            case 3:
                return "Shot";
            default:
                return "";
        }
        
    }
    

    int rollDie() {
        switch (colour) {
            case 1:
                value = greenDie[1 + (int) (Math.random() * 6) -1];
                break;
            case 2:
                value = yellowDie[1 + (int) (Math.random() * 6) -1];
                break;
            case 3:
                value = redDie[1 + (int) (Math.random() * 6) -1];
                break;
            default:
                break;
        }
        return value;
    }

    boolean isBrain() {
            if (value == 1) {
            return true;
        }
        return false;
        
    }

    boolean isShot() {
        if (value == 3) {
            return true;
        }
        return false;
    }

}
