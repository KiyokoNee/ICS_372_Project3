package edu.metrostate.sheltertracker;

import java.util.Scanner;

public class Animal {
    private final String animal_ID;
    private final String animal_Type;
    private final String animal_Name;
    private final String weight_unit;
    private double animal_weight;
    private final long receipt_date;

    /**
     * Animal Constructor - responsible for creating/setting animal variables with the passed on parameters
     *
     * @param animal_type - (String) the general species of animal
     * @param animal_name - (String) the name of the animal
     * @param animal_id - (String) ID of the animal
     * @param animal_weight - (double) numeric weight of the animal
     * @param weight_unit - (String) unit of the animal's weight
     * @param receipt_date - (long) date the animal was received
     */
    public Animal(String animal_type, String animal_name, String animal_id, double animal_weight, String weight_unit, long receipt_date){
        this.animal_Type = animal_type;
        this.animal_Name = animal_name;
        this.animal_ID = animal_id;
        this.animal_weight = animal_weight;
        this.weight_unit = weight_unit;
        this.receipt_date = receipt_date;

    }

    public String getAnimal_ID() {
        return animal_ID;
    }

    public String getAnimal_Type() {
        return animal_Type;
    }

    public String getAnimal_Name() {
        return animal_Name;
    }

    public void setAnimal_weight(double animal_weight) {
        this.animal_weight = animal_weight;
    }

    public double getAnimal_weight() {
        return animal_weight;
    }

    public String getWeight_unit(){
        return weight_unit;
    }

    public long getReceipt_date() {
        return receipt_date;
    }

    @Override
    public String toString() {
        return "Animal ID='" + animal_ID + '\'' +
                ", Animal Type='" + animal_Type + '\'' +
                ", Animal Name='" + animal_Name + '\'' +
                ", Animal Weight=" + animal_weight + " " + weight_unit +
                ", Receipt Date=" + receipt_date;
    }

    /**
     * Method responsible for collecting user input to create new Animal object
     * @return (Animal) - user defined animal object
     */
    public static Animal createNewAnimal(ShelterList shelterMap) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the animal type: ");
        String type = scan.nextLine();
        if (shelterMap.validAnimal(type)) {
            try {
                System.out.println("Please enter the animal name: ");
                String name = scan.nextLine();
                System.out.println("Please enter the animal ID: ");
                String id = scan.nextLine();
                System.out.println("Please enter the animal weight: ");
                double weight = scan.nextDouble();
                scan.nextLine();
                System.out.println("Please enter the weight unit: ");
                String unit = scan.nextLine();
                System.out.println("Please enter the receipt date: ");
                long receipt = scan.nextLong();
                scan.nextLine();
                return new Animal(type, name, id, weight, unit, receipt);
            }catch (Exception e){
                System.out.println("Animal could not be created\n");
                return null;
            }
        } else {
            System.out.println("Not a valid animal type.\n");
            return null;
        }
    }
}
