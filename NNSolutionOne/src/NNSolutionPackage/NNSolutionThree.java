/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NNSolutionPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Márk
 */
public class NNSolutionThree {
    
    
    public static void main(String[] args) throws IOException
    {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String[] inputStringArray = s.split(",");
        int[] inputIntArray = new int[inputStringArray.length];

        for (int i = 0; i < inputStringArray.length; i++) {
            inputIntArray[i] = Integer.parseInt(inputStringArray[i]);

        }
        Architecture arch = new Architecture(inputIntArray);
        ///////////////////////WEIGHTS
        String newLine = System.getProperty("line.separator");
        for (int l = 1; l < inputIntArray.length; l++) {
            for (int i = 0; i < inputIntArray[l]; i++) {
              
                String st = br.readLine();
                String[] inputStringArray2 = st.split(",");
                float[] inputFloatArray = new float[inputStringArray2.length];

                for (int j = 0; j < inputStringArray2.length; j++) {
                    inputFloatArray[j] = Float.parseFloat(inputStringArray2[j]);

                }
                arch.layers.get(l).get(i).editAddedWeightList(inputFloatArray);

            }
        }
        /////////////////INPUT
       
        String st = br.readLine();
        int inputNumber = Integer.parseInt(st);
        ArrayList<ArrayList<Float>> inputs = new ArrayList();
        for (int i = 0; i < inputNumber; i++) {
            inputs.add(new ArrayList());
        }
        for (int i = 0; i < inputNumber; i++) {

            String sa = br.readLine();
            String[] inputStringArray3 = sa.split(",");

            for (int j = 0; j < arch.layers.get(0).size(); j++) {

                inputs.get(i).add(Float.parseFloat(inputStringArray3[j]));

            }
        }
        
       System.out.println(s);

        for (int i = 0; i < inputs.size(); i++) {
            arch.changeWeightList(inputs.get(i));
        }
        for (int i=1; i<arch.layers.size(); i++)
        {
            for(int j=0; j<arch.layers.get(i).size(); j++)
            {
                arch.layers.get(i).get(j).writeOutWeightList(arch.layers.get(i).get(j).changedWeightList);
            }
        }
        
    }
}
