package edu.metrostate.sheltertracker;

import java.util.*;

public class ShelterList {
    Map<String, Shelter> mapOfShelters = new HashMap<>();

    public void addHashMap(HashMap<String, Shelter> shelters){
        mapOfShelters.putAll(shelters);
    }

    public void addShelter(String id, Shelter shelter){
        mapOfShelters.put(id, shelter);
    }

    /**
     * Prints out the values of mapOfShelters directly to the console
     */
    public void showShelters(){
        System.out.println(mapOfShelters.values());
    }

    /**
     * Returns true if a given shelter id is found in mapOfShelters.
     * @param id - (String) user selected shelter id
     * @return (boolean) - if a given shelter exists
     */
    public boolean containsShelter(String id){
        return mapOfShelters.containsKey(id);
    }

    /**
     * Returns a ShelterModules.Shelter that corresponds to the
     * @param id - (String) user given id
     * @return (Shelter) - Shelter based on corresponding id.
     */
    public Shelter getShelter(String id){
        return mapOfShelters.get(id);
    }

    /**
     * Returns a Collection of the Shelters stored in mapOfShelters.
     * @return (Collection) - values of stored Shelters.
     */
    public List<Shelter> getShelters(){
        return new ArrayList<>(mapOfShelters.values());}

    /**
     * Checks whether a given animal type is supported.
     * @param type - (String) user given animal type.
     * @return (boolean) - validity of given type.
     */
    public boolean validAnimal(String type){
        return type.equalsIgnoreCase("dog") || type.equalsIgnoreCase("cat") ||
                type.equalsIgnoreCase("bird") || type.equalsIgnoreCase("rabbit");
    }

    public static boolean shelterSearch(String selected, ShelterList shelters){
        if (shelters.containsShelter(selected)) {
            return true;
        } else {
            System.out.println("Invalid shelter ID\n");
        }
        return false;
    }

    /**
     * Method loops through all shelters in map and all animal objects in each shelter object
     * and prints all to console.
     */
    public static void showAllAnimals (ShelterList shelterMap) {
        List<Shelter> allShelters = new ArrayList<>(shelterMap.getShelters());
        for (Shelter currentShelter : allShelters) {
            System.out.println(currentShelter.showAnimals());
        }
    }

    public List<Animal> getAllAnimals(){
        List<Animal> animals = new ArrayList<>();
        List<Shelter> shelters = new ArrayList<>(mapOfShelters.values());

        for (int i = 0; i < shelters.size(); i++) {
            animals.addAll(shelters.get(i).getAnimalList());
        }

        return animals;
    }

    public void addAnimalToShelter(String id, Animal animal){
        Shelter tempShelter = mapOfShelters.get(id);
        tempShelter.addAnimal(animal);
    }
}
