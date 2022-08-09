package edu.metrostate.sheltertracker;
import java.util.ArrayList;
import java.util.List;

/**
 * Shelter class that keep tracks shelter status and animals.
 */
public class Shelter {
    private List<Animal> animalList = new ArrayList<>();
    private final String shelterID;
    private String shelterName;
    private boolean receiving;

    /**
     * Constructor - responsible for creating shelter with shelterID.
     * @param shelterID
     */
    Shelter(String shelterID){
        this.shelterID = shelterID;
        shelterName = "unlisted";
        receiving = true;
    }

    /**
     * Constructor - responsible for creating shelter with shelterID and shelter name.
     * @param shelterId
     * @param name
     */
    Shelter(String shelterId, String name){
        this.shelterID = shelterId;
        shelterName = name;
        receiving = true;
    }

    /**
     * Method that returns the receiving status
     * @return
     */
    public boolean isReceiving(){
        return receiving;
    }

    /**
     * Method that return the animals in the shelter
     * @return
     */
    public List<Animal> getAnimalList() {
        return animalList;
    }

    /**
     * Method that returns the name and id of shelter
     * @return
     */
    public String toString(){
        return shelterName + ": " + shelterID;
    }

    /**
     * Method that returns the shelterID of shelter
     * @return
     */
    public String getShelterID(){
        return shelterID;
    }

    /**
     * Method that returns the shelter name of shelter
     * @return
     */
    public String getShelterName(){
        return shelterName;
    }

    /**
     * Method that returns the size of animalList in shelter
     * @return
     */
    public int size(){
        return animalList.size();

    }

    /**
     * Method that set the receiving status of a shelter
     * @param status
     */
    public void setReceiving(boolean status){
        receiving = status;
    }

    /**
     * Method that add an animal to shelter if shelter receiving status is true.
     * @param animal
     */
    public void addAnimal(Animal animal){
        if(receiving){
            animalList.add(animal);
        }
    }
}
