/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NNSolutionPackage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Márk
 */
public class NNSolutionOne {

    public static void main(String[] args) {
        //Bemenet átkonvertálása
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s = br.readLine();
            String[] inputStringArray = s.split(",");
            int[] inputIntArray = new int[inputStringArray.length];

            for (int i = 0; i < inputStringArray.length; i++) {
                inputIntArray[i] = Integer.parseInt(inputStringArray[i]);

            }
            String newLine = System.getProperty("line.separator");
            for(int i=0; i<inputIntArray.length; i++)
            {
                if(i== inputIntArray.length -1)
                {
                    System.out.print(inputIntArray[i]);
                }
                else{
                System.out.print(inputIntArray[i] + ",");
                }
            }
            System.out.print(newLine);
            Architecture arch = new Architecture(inputIntArray);
            arch.writeWeight();
        } catch (IOException ex) {
            Logger.getLogger(NNSolutionOne.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
