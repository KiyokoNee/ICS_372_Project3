package edu.metrostate.sheltertracker;

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
}
