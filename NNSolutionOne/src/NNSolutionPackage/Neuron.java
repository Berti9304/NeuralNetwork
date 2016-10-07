/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NNSolutionPackage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Márk
 */
public class Neuron {

    private int index = 0;
    public ArrayList<Neuron> inputList = new ArrayList();
    public ArrayList<Float> weightList = new ArrayList();
    public ArrayList<Float> addedWeightList = new ArrayList();
    public ArrayList<Float> changedWeightList = new ArrayList();
    public ArrayList<Float> tempWeightList = new ArrayList();
    public float ifInput;
    public float output;
    private float bias =  (float) 0.0;
    Random r = new Random();

    public void setNeuronList(ArrayList<Neuron> list) {
        for (int i = 0; i < list.size(); i++) {
            this.weightList.add(weightCalculator());
        }
        this.weightList.add(bias);
        this.inputList = list;
    }

    public float weightCalculator() {

        return  (float)((float)r.nextGaussian() * 0.1);
    }

    public ArrayList getWeightList() {
        return weightList;
    }

    public void writeOutWeightList(ArrayList<Float> weightList) {
        String newLine = System.getProperty("line.separator");
        for (int i = 0; i < weightList.size(); i++) {
            if (i == weightList.size() - 1) {
                System.out.print(weightList.get(i));
            } else {
                System.out.print(weightList.get(i) + ",");
            }
        }
        System.out.print(newLine);
    }

    public void editAddedWeightList(float[] ar) {
        for (int i = 0; i < ar.length; i++) {
            this.addedWeightList.add(ar[i]);
        }
    }

    public void calculateOutput(boolean isItFinal, ArrayList<Float> weight) {
        
       

        if (isItFinal) {
            float result = weight.get(weight.size() - 1); //utolsó elem a bias
            float additive = 0;
            for (int i = 0; i < this.inputList.size(); i++) {
                additive = additive + (this.inputList.get(i).output * weight.get(i));
            }
            result = result + additive;
          
          
            this.output = result;
            

        } else {

            float result = weight.get(weight.size() - 1); //utolsó elem a bias
            float additive = 0;
            for (int i = 0; i < this.inputList.size(); i++) {
                additive = additive + (this.inputList.get(i).output * weight.get(i));
            }
            result = result + additive;
           
            float finalresult = 0;
            if (result >= 0) {
                finalresult =  result;
            } else {
                finalresult =  (float) 0.0;
            }
            this.output = finalresult;
           

        }

    }
    
    public void setWeightList(ArrayList<Float> temp)
    {
        this.weightList = temp;
    }

}
