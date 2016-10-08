/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NNSolutionPackage;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Márk
 */
public class Architecture {

    public ArrayList<ArrayList<Neuron>> layers = new ArrayList();

    public Architecture(int[] inputIntArray) {

        for (int j = 0; j < inputIntArray.length; j++) {
            this.layers.add(j, new ArrayList<Neuron>());
            for (int i = 0; i < inputIntArray[j]; i++) {
                this.layers.get(j).add(new Neuron());

            }

        }

        for (int i = 1; i < this.layers.size(); i++) {
            for (int j = 0; j < this.layers.get(i).size(); j++) {
                this.layers.get(i).get(j).setNeuronList(this.layers.get(i - 1));
            }
        }
    }
    
    public ArrayList getLayers(){

    return this.layers;
    }

    public void writeWeight() {

        for (int i = 1; i < this.layers.size(); i++) {
            for (int j = 0; j < this.layers.get(i).size(); j++) {
                this.layers.get(i).get(j).writeOutWeightList();
            }
        }
    }
    
    public void writeOutput(ArrayList<Float> inputs)
    {
         DecimalFormat tv = new DecimalFormat();
           for(int j=0; j<this.layers.get(0).size(); j++)
           {
               
                   layers.get(0).get(j).output = inputs.get(j); //BEMENETI RÉTEG
           }
           
           for(int i=1; i<this.layers.size(); i++)
           {
               for(int j=0; j<this.layers.get(i).size(); j++)
               {
                   if(i != this.layers.size()-1)
                   {
                       this.layers.get(i).get(j).calculateOutput(false);  //KÖZTES RÉTEG
                   }
                   else{
                       this.layers.get(i).get(j).calculateOutput(true); //KIMENETI RÉTEG
                   }
               }
           }
           
           for(int i=0; i<this.layers.get(this.layers.size()-1).size();i++)
           {
               if(this.layers.get(this.layers.size()-1).size() == 1)
               System.out.println(this.layers.get(this.layers.size()-1).get(i).output);
               else{
                   if(i != this.layers.get(this.layers.size()-1).size()-1)
                   {
                       System.out.print(this.layers.get(this.layers.size()-1).get(i).output + ",");
                   }
                   else{
                       System.out.print(this.layers.get(this.layers.size()-1).get(i).output);
                       String newLine = System.getProperty("line.separator");
                       System.out.print(newLine);
                   }
               }
           }
             
       
       
       
        
    }

}
