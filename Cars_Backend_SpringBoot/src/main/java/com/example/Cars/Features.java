package com.example.Cars;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Features implements Serializable {
    private ArrayList<String> features;

    //Constructor
    public Features() {
        features = new ArrayList<>();
    }

    public Features(List<String> features) {
        this.features = (ArrayList<String>) features;
    }

    //Getter and Setter
    public List<String> getFeatures() {
        LinkedList<String> copy = new LinkedList<>();
        //make copy of features list - protect original
        for (String feature : features) {
            copy.add(feature);
        }
        return copy;
    }

    public void setFeatures(List<String> features) {
        this.features = (ArrayList<String>) features;
    }

    public boolean addFeature(String feature) {
        if(features == null) {
            features = new ArrayList<>();
        }
        if(features.size() == 0) {
            features.add(feature);
            return true;
        }
        //feature already exists - didnt add new one
        if(hasFeature(feature)) return false;
        features.add(feature);
        return true; //feature added
    }

    public boolean hasFeature(String feature) {
        for (String current : features) {
            if(feature.equalsIgnoreCase(current)) return true;
        }
        return false;
    }

    public boolean equals(Object object) {
        if(object == null) return false;
        else if (object.getClass() != getClass()) return false;
        else {
            //sort features into alphabetical order to compare if the list
                //of features is the exact same in both
            ArrayList<String> casted = ((Features) object).features;
            features.sort(Comparator.naturalOrder());
            casted.sort(Comparator.naturalOrder());
            //check if length is the same first
            if(casted.size() != features.size()) return false;
            else {
                for(int i = 0; i < features.size(); i++) {
                    if(!(features.get(i).equalsIgnoreCase(casted.get(i)))) return false;
                }
            }
            return true;
        }
    }

    public String toString() {
        String display = "";
        for (String feature : features) {
            display += "\t" + feature + "\n";
        }
        return display;
    }
}
