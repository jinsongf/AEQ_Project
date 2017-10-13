/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package castle;

import java.util.Scanner;

/**
 *
 * @author Jinsong
 */
public class Castle {
       
    int getLandSize()
    {
        int size = 0;
         try {
            Scanner in = new Scanner(System.in); 

                    System.out.println("Enter a Land size:" );
                    size = in.nextInt();           
         }
         catch (Exception e) {
                e.printStackTrace();
         }
         return size;
    } 
    
    int[] collectLandArray(int size)
    {
        int[] land = new int[size];
         try {
            Scanner in = new Scanner(System.in); 
            for (int j = 0; j < land.length ; j++) {
                    System.out.println("Enter a Land array[" + j + "]:");
                    int k = in.nextInt();
                    land[j] = k;
            }
         }
         catch (Exception e) {
                e.printStackTrace();
         }
         return land;
    }
    
    int getNumOfBuildedCastle(int[] land)
    {
        int sumOfCastle = 0;
        float firstChanged = 0;             
        float secondChanged = 0;
        int baseItem = land[0];
        int sumOfLevelGround = 0;
        for (int i = 0; i < land.length - 2; i++)
        {
            if(secondChanged != 0)
               baseItem = land[i];
            firstChanged = land[i+1] - baseItem;
            secondChanged = land[i+2] - land[i+1];
            
            //calculate level ground
            if(secondChanged == 0)
            {
                sumOfLevelGround ++;                
            }

            else if(firstChanged * secondChanged < 0)
            {               
                sumOfCastle ++;
                if(sumOfLevelGround > 0)
                {
                    sumOfCastle = sumOfCastle + sumOfLevelGround;
                    sumOfLevelGround = 0;
                }                
            }
            else if(firstChanged * secondChanged > 0)
            {
                if(sumOfLevelGround > 0)
                    sumOfLevelGround = 0;
            }            
        }    
        return sumOfCastle;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Castle castle = new Castle();
        //collect land array
        int size = castle.getLandSize();
        int[] land = castle.collectLandArray(size);
        
        if (land.length < 3)
            return;
        
        int numOfCastle = castle.getNumOfBuildedCastle(land);
        System.out.print("number of Castle " + numOfCastle);
    }
    
}
