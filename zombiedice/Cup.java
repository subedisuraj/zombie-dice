
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author surajs
 */
public class Cup {

    List<Die> dice = new ArrayList<>();

    

    Cup() {

        for (int i = 0; i < 6; i++) {
            Die d = new Die(1);
            dice.add(d);
        }

        for (int i = 0; i < 4; i++) {
            Die d = new Die(2);
            dice.add(d);
        }

        for (int i = 0; i < 3; i++) {
            Die d = new Die(3);
            dice.add(d);
        }

       
    }

    Die pickDieFromCup() {

        Die d = new Die();
        boolean atleastOneincup = false;
        for(int i =0; i<13;i++)
        {
            
            if(dice.get(i).inCup)
                atleastOneincup = true;
            
        }
        
        if(!atleastOneincup)
        {
            for(int i = 0; i<=13;i++)
            {
                if (dice.get(i).value==1)
                    dice.get(i).inCup= false;
            }
        }
        while (true) {
            int index = 1 + (int) (Math.random() * 13) -1;
            d = dice.get(index);
            
            if ((d.inCup)) {
                dice.get(index).inCup = false;
            }
            break;
        }
        return d; 
    }

    

}
