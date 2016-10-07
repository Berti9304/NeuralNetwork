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

    public ArrayList getLayers() {

        return this.layers;
    }

    public void writeWeight() {

        for (int i = 1; i < this.layers.size(); i++) {
            for (int j = 0; j < this.layers.get(i).size(); j++) {
                this.layers.get(i).get(j).writeOutWeightList(this.layers.get(i).get(j).weightList);
            }
        }
    }

    public void writeOutput(ArrayList<Float> inputs) {
        calculateAllOutputs(inputs, true);

        for (int i = 0; i < this.layers.get(this.layers.size() - 1).size(); i++) {
            if (this.layers.get(this.layers.size() - 1).size() == 1) {
                System.out.println(this.layers.get(this.layers.size() - 1).get(i).output);
            } else if (i != this.layers.get(this.layers.size() - 1).size() - 1) {
                System.out.print(this.layers.get(this.layers.size() - 1).get(i).output + ",");
            } else {
                System.out.print(this.layers.get(this.layers.size() - 1).get(i).output);
                String newLine = System.getProperty("line.separator");
                System.out.print(newLine);
            }
        }

    }

    public void nullifyLayerWeight(int layer) {
        for (int i = 1; i < this.layers.size(); i++) {
          
            if(i<layer)
            {
                for(int j=0; j<this.layers.get(i).size();j++)
                {
                    this.layers.get(i).get(j).tempWeightList = this.layers.get(i).get(j).addedWeightList;
                }
            }
            else if(i == layer) {
                for (int j = 0; j < this.layers.get(i).size(); j++) {
                    ArrayList<Float> tempWeightList = new ArrayList();
                    for (int k = 0; k < this.layers.get(i).get(j).addedWeightList.size(); k++) {
                        tempWeightList.add((float) 0);
                    }
                    this.layers.get(i).get(j).tempWeightList = tempWeightList;

                }
                
            } else {
                 for (int j = 0; j < this.layers.get(i).size(); j++) {
                    ArrayList<Float> tempWeightList = new ArrayList();
                    for (int k = 0; k < this.layers.get(i).get(j).addedWeightList.size()-1; k++) {
                        tempWeightList.add(this.layers.get(i).get(j).addedWeightList.get(k));
                    }
                    tempWeightList.add((float) 0.0);
                    this.layers.get(i).get(j).tempWeightList = tempWeightList;

                }

            }
        }
    }

    public void calculateAllOutputs(ArrayList<Float> inputs, boolean legacy) {

        for (int j = 0; j < this.layers.get(0).size(); j++) {

            layers.get(0).get(j).output = inputs.get(j); //BEMENETI RÉTEG
        }

        for (int i = 1; i < this.layers.size(); i++) {
            for (int j = 0; j < this.layers.get(i).size(); j++) {
                if (i != this.layers.size() - 1) {
                    if (legacy) {
                        this.layers.get(i).get(j).calculateOutput(false, this.layers.get(i).get(j).addedWeightList);  //KÖZTES RÉTEG
                    } else {
                        this.layers.get(i).get(j).calculateOutput(false, this.layers.get(i).get(j).tempWeightList);
                    }
                } else if (legacy) {
                    this.layers.get(i).get(j).calculateOutput(true, this.layers.get(i).get(j).addedWeightList); //KIMENETI RÉTEG
                } else {
                    this.layers.get(i).get(j).calculateOutput(true, this.layers.get(i).get(j).tempWeightList);
                }
            }
        }
    }

    public void changeWeightList(ArrayList<Float> inputs) {

        for (int i = 1; i < this.layers.size(); i++) {

            for (int j = 0; j < this.layers.get(i).size(); j++) {

                calculateAllOutputs(inputs, true);

                for (int k = 0; k < this.layers.get(i).get(j).addedWeightList.size(); k++) {
                    nullifyLayerWeight(i);
                    if (k == this.layers.get(i).get(j).addedWeightList.size() - 1) {
                      
                            this.layers.get(i).get(j).tempWeightList.set(k, (float) 1);
                        
                    } else {
                        this.layers.get(i).get(j).tempWeightList.set(k, (float) 1);
                    }

                    calculateAllOutputs(inputs, false);
                    this.layers.get(i).get(j).changedWeightList.add(this.layers.get(this.layers.size() - 1).get(this.layers.get(this.layers.size() - 1).size() - 1).output);
                }

            }
        }

    }

}
