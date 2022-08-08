package edu.metrostate.sheltertracker;
import java.util.ArrayList;
import java.util.List;

public class Shelter {
    private List<Animal> animalList = new ArrayList<>();
    private final String shelterID;
    private String shelterName;
    private boolean receiving;

    Shelter(String shelterID){
        this.shelterID = shelterID;
        shelterName = "unlisted";
        receiving = true;
    }
    Shelter(String shelterId, String name){
        this.shelterID = shelterId;
        shelterName = name;
        receiving = true;
    }

    public boolean isReceiving(){
        return receiving;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public String toString(){
        return shelterName + ": " + shelterID;
    }

    public String getShelterID(){
        return shelterID;
    }

    public String getShelterName(){
        return shelterName;
    }

    public int size(){
        return animalList.size();

    }
    public void setReceiving(boolean status){
        receiving = status;
    }

    public void addAnimal(Animal animal){
        if(receiving){
            animalList.add(animal);
        }
    }
}
