/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package zombiedice;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author surajs
 */
public class Player {
    
    int brainCount;
    boolean gamestatus;
    String name;
    Roll newroll, oldroll;
    
    Cup c;
    int shotsCount;
    int runnerCount;
    
    
    Roll rollDice(Roll r) {
        for (int i = 0; i < 3; i++) {
            r.dc.get(i).value = r.dc.get(i).rollDie();
        }
        return r;
    }

    Roll selectDicetoRoll() {
        
        newroll = new Roll();

        if (oldroll != null) {
            for (int i = 0; i < 3; i++) {
                if (oldroll.dc.get(i).isRunner()) {
                    newroll.dc.add(oldroll.dc.get(i));
                }

            }
        }

        for (int i = 0; i < 3; i++) {
            if (newroll.dc.size() < 3) {
                newroll.dc.add(c.pickDieFromCup());
            }

        }

        return newroll;

    }
    

    void updateBoard()
    {
        for(int i=0;i<3;i++)
        {
            if (newroll.dc.get(i).isBrain())
                brainCount++;
            else if (newroll.dc.get(i).isShot())
                shotsCount++;
            else 
                runnerCount++;
                
            
        }
        oldroll = newroll;
    }
    
    
    
    Player(String nm)
    {
        name = nm;
        c = new Cup();
        oldroll = null;
        newroll = new Roll();
    }
    

    
   
    
}
