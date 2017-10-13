
import static java.lang.Math.min;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jinsong
 */
public class Transformation {
    String name;
    
    int strength;
    int intelligence;
    int speed;
    int endurance;
    int rank;
    int courage;
    int firepower;
    int skill;        
    boolean isEliminated;

    public Transformation() {
        name = "";
        isEliminated = false;
        strength = 0;
        intelligence = 0;
        speed = 0;
        endurance = 0;
        rank = 0;
        courage = 0;
        firepower = 0;
        skill = 0;            
    }
    
    int getTotalRate()
    {
        return strength + intelligence + speed + endurance + firepower;
    }

    Transformation cloneTransformation(Transformation tran)
    {
        Transformation clonedTrans = new Transformation();
        clonedTrans.name = tran.name; 
        clonedTrans.strength = tran.strength;    
        clonedTrans.intelligence = tran.intelligence;  
        clonedTrans.speed = tran.speed;  
        clonedTrans.endurance = tran.endurance;    
        clonedTrans.rank = tran.rank;    
        clonedTrans.courage = tran.courage;    
        clonedTrans.firepower = tran.firepower;  
        clonedTrans.skill = tran.skill;  
        return clonedTrans;       
    }
    
    void printOutput(Transformation[] trans, String teamName, boolean isWon)
    {
        if(isWon)
            System.out.print("Winning team (" + teamName + "): ");
        else
            System.out.print("Survivors from the losing team (" + teamName + "): ");
        
        for (int i = 0; i < trans.length; i ++)
        {
            if(trans[i].isEliminated == false )
                System.out.print(trans[i].name + ",");
        }
        System.out.println(" ");
    }
    
    ////The teams should be sorted by rank
    void sortTransformations(Transformation[] trans)
    {
        
        Transformation smallItem = cloneTransformation(trans[trans.length - 1]);
        Transformation largeItem = cloneTransformation(trans[0]);
        
        for (int j=0; j < trans.length - 1; j++){
            for (int i = j; i < trans.length - j - 1; i++)
            {
                if(trans[i].getTotalRate() > largeItem.getTotalRate())
                {
                    
                    largeItem = cloneTransformation(trans[i]);
                    trans[i] = trans[j];
                    trans[j] = largeItem;
                }

                if(trans[i].getTotalRate() < smallItem.getTotalRate())
                {
                    smallItem = cloneTransformation(trans[i]);
                    trans[i] = trans[trans.length - j - 1];
                    trans[trans.length - j - 1] = smallItem;
                }     
            }
                 
        }

    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //init
        Transformation[] autobots, decepticons;
        int sizeOfAutobots = 2;
        int sizeOfDecepticons = 1;
        autobots = new Transformation[sizeOfAutobots];
        decepticons = new Transformation[sizeOfDecepticons];
        autobots[0] =  new Transformation();
        decepticons[0] = new Transformation();
        autobots[1] = new Transformation();
        
        autobots[0].name = "Bluestreak";
        autobots[0].strength = 6;    
        autobots[0].intelligence = 6;  
        autobots[0].speed = 7;  
        autobots[0].endurance = 9;    
        autobots[0].rank = 5;    
        autobots[0].courage = 2;    
        autobots[0].firepower = 9;  
        autobots[0].skill = 7;    

        autobots[1].name = "Hubcap";
        autobots[1].strength = 4;    
        autobots[1].intelligence = 4; 
        autobots[1].speed = 4;
        autobots[1].endurance = 4;    
        autobots[1].rank = 4;    
        autobots[1].courage = 4;    
        autobots[1].firepower = 4;  
        autobots[1].skill = 4;    
                 
        decepticons[0].name = "Soundwave";
        decepticons[0].strength = 8;    
        decepticons[0].intelligence = 9; 
        decepticons[0].speed = 2;
        decepticons[0].endurance = 6;    
        decepticons[0].rank = 7;    
        decepticons[0].courage = 5;    
        decepticons[0].firepower = 6;  
        decepticons[0].skill = 10;    
       
        //The teams should be sorted by rank
        autobots[0].sortTransformations(autobots);
        decepticons[0].sortTransformations(decepticons);
        
        System.out.println("1 battle ");
        
        int sumOfEliminatedDecepticons = 0;;
        int sumofEleiminatedAutobots = 0;
        
        //faced off one on one against each other in order to determine a victor, the loser is eliminated
        //Any Transformers who don’t have a fight are skipped (i.e. if it’s a team of 2 vs. a team of 1, there’s
        //only going to be one battle)
        for(int i = 0; i < min(decepticons.length,autobots.length); i++)
        {
         //Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of
         //any other criteria
            if(autobots[i].name.equals(decepticons[i].name) && (decepticons[i].name.equals("Optimus Prime") || decepticons[i].name.equals("Predaking")))
            {
               autobots[i].isEliminated = true;
               decepticons[i].isEliminated = true;
               sumOfEliminatedDecepticons ++;
               sumofEleiminatedAutobots ++;
            }
            else if((decepticons[i].name.equals("Optimus Prime") || decepticons[i].name.equals("Predaking")))
            {
               autobots[i].isEliminated = true;
               sumofEleiminatedAutobots ++;
            }
            else if((autobots[i].name.equals("Optimus Prime") || autobots[i].name.equals("Predaking")))
            {
               decepticons[i].isEliminated = true;
               sumOfEliminatedDecepticons ++;              
            }       
            //If any fighter is down 4 or more points of courage and 3 or more points of strength
            //compared to their opponent, the opponent automatically wins the face-off regardless of
            //overall rating (opponent has ran away)
            else if ((autobots[i].courage - decepticons[i].courage >= 4) && (autobots[i].strength - decepticons[i].strength >= 3)) 
            {
               decepticons[i].isEliminated = true;
               sumOfEliminatedDecepticons ++;             
            }
                    
            else if ((decepticons[i].courage - autobots[i].courage  >= 4) && (decepticons[i].strength - autobots[i].strength  >= 3)) 
            {
               autobots[i].isEliminated = true;
               sumofEleiminatedAutobots ++;               
            }  
            //Otherwise, if one of the fighters is 3 or more points of skill above their opponent, they win
            //the fight regardless of overall rating
            if (autobots[i].skill - decepticons[i].skill >= 3)
            {
               decepticons[i].isEliminated = true;
               sumOfEliminatedDecepticons ++;              
            }
                    
            else if (decepticons[i].skill - autobots[i].skill  >= 3) 
            {
               autobots[i].isEliminated = true;
               sumofEleiminatedAutobots ++;
            } 
            
            //The winner is the Transformer with the highest overall rating
            else if (autobots[i].getTotalRate() > decepticons[i].getTotalRate())
            {
               decepticons[i].isEliminated = true;
               sumOfEliminatedDecepticons ++;
            }
            else if (decepticons[i].getTotalRate() > autobots[i].getTotalRate())
            {
               autobots[i].isEliminated = true;
               sumofEleiminatedAutobots ++;
            } 
            //In the event of a tie, both Transformers are considered destroyed
            else if (decepticons[i].getTotalRate() == autobots[i].getTotalRate())
            {
               autobots[i].isEliminated = true;
               decepticons[i].isEliminated = true;
               sumOfEliminatedDecepticons ++;
               sumofEleiminatedAutobots ++;               
            }                      
        }
        //The team who eliminated the largest number of the opposing team is the winner
        if (sumOfEliminatedDecepticons < sumofEleiminatedAutobots)
        {
            decepticons[0].printOutput(decepticons, "Decepticons", true);
            autobots[0].printOutput(autobots, "Autobots", false);
        }
        else if (sumOfEliminatedDecepticons > sumofEleiminatedAutobots)
        {
            decepticons[0].printOutput(decepticons, "Decepticons", false);
            autobots[0].printOutput(autobots, "Autobots", true);
        }     
        else
        {
            decepticons[0].printOutput(decepticons, "Decepticons", false);
            autobots[0].printOutput(autobots, "Autobots", false);
        }                 
        
    }   

}


