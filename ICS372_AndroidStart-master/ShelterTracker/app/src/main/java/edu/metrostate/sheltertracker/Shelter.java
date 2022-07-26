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

    public void setAnimalList(List<Animal> newList) {
        animalList = newList;
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

    /*
        returns a string of all animals in the list for specific shelter
         */
    public String showAnimals(){
        String str = "Shelter ID: " + shelterID + "\n";
        str += "Shelter Name: " + shelterName + "\n";
        str += "Shelter Receiving: " + receiving + "\n";

        for (int i = 0; i < size(); i++) {
            str += animalList.get(i).toString();
            str += "\n";
        }
        return str;
    }
    public int size(){
        return animalList.size();

    }
    public void setReceiving(boolean status){
        receiving = status;
    }
    /**
     * Method to toggle isReceiving attribute of a Shelter object to receiving or not receiving based on
     * boolean value.
     * @param status - (boolean) true enables receiving, false disables
     */
    public void changeReceiving(boolean status) {
        setReceiving(status);
        if(status){
            receiving = false;
            System.out.println("Receiving disabled for shelter " + shelterID + "\n");
        } else {
            receiving = true;
            System.out.println("Receiving enabled for shelter " + shelterID + "\n");
        }
    }
    /**
     * Method responsible for adding Animal object into previously created Shelter object
     * @param newAnimal - (Animal) animal object to be added to shelter object
     * @param selected - (String) shelter ID
     */
    public static void addUserCreatedAnimal(Animal newAnimal, String selected, ShelterList shelter){
        try{
            Shelter tempShelter = shelter.getShelter(selected);
            List<Animal> tempList = tempShelter.getAnimalList();
            tempList.add(newAnimal);
            tempShelter.setAnimalList(tempList);
            System.out.println("New Animal has been added.\n");
        } catch (Exception e) {
            System.out.println("Animal could not be added.\n");
        }
    }

    public void addAnimal(Animal animal){
        if(receiving){
            animalList.add(animal);
        }
    }
}
