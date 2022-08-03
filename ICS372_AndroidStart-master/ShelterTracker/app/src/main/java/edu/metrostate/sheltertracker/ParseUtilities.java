package edu.metrostate.sheltertracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

public class ParseUtilities {
    /**
     * Reads in specified JSON file to fill out the ShelterList
     */
    public static void addIncomingJSON(String filename, ShelterList shelters) {
        JSONArray j = FileUtilities.readJSON(filename);
//      https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
        assert j != null;
        for (int i = 0; i < j.length(); i++) {
            try {
                parseAnimalObject(j.getJSONObject(i),shelters);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Takes a JSONObject and converts it into an ShelterModules.Animal, then adds it
     * mapOfShelters.
     * @param animal - (JSONObject) converted into an ShelterModules.Animal Object.
     */
    private static void parseAnimalObject(JSONObject animal, ShelterList shelters) {
        try {
            String shelter_id = (String) animal.get("shelter_id");

            String animal_type = (String) animal.get("animal_type");
            String animal_name = (String) animal.get("animal_name");
            String animal_id = (String) animal.get("animal_id");
            Object temp = animal.get("weight");
            double animal_weight;

            // checks incoming weight value and converts value to double
            if (temp instanceof Double) {
                animal_weight = (Double) temp;
            } else {
                animal_weight = ((Long) temp).doubleValue();
            }
            long receipt_date = (long) animal.get("receipt_date");

            // if type is valid, creates ShelterModules.Animal and add to correct ShelterModules.Shelter. Creates shelter if it doesn't exist
            if (shelters.validAnimal(animal_type)) {
                Animal tempAnimal = new Animal(animal_type, animal_name, animal_id, animal_weight, "", receipt_date);
                if (!shelters.containsShelter(shelter_id)) {
                    Shelter tempShelter = new Shelter(shelter_id);
                    shelters.addShelter(shelter_id, tempShelter);
                }
                shelters.addAnimalToShelter(shelter_id, tempAnimal);
            }
        } catch (Exception e){

        }
    }

    /**
     * Loads saved file data into a HashMap
     * @param filename - (String)
     * @return (HashMap) - creates a new map to be merged into existing roster
     */
    public static HashMap<String, Shelter> loadJSON(String filename) {
        JSONArray shelters = FileUtilities.readJSON(filename);
        HashMap<String, Shelter> shelterRoster = new HashMap<>();

        if (shelters == null) {
            return new HashMap<>();
        }
        try {
            for (int i = 0; i <shelters.length(); i++) {
                JSONObject shelter = shelters.getJSONObject(i);
                String id = (String) shelter.get("shelter_id");
                String name = (String) shelter.get("shelter_name");
                boolean receiving = (Boolean) shelter.get("shelter_receiving");

                Shelter currShelter = new Shelter(id, name);

                JSONArray animalList = (JSONArray) shelter.get("animals");
                for (int j = 0; j < animalList.length(); j++) {
                    JSONObject animal = animalList.getJSONObject(j);
                    String aniType = (String) animal.get("animal_type");
                    String aniName = (String) animal.get("animal_name");
                    String aniID = (String) animal.get("animal_id");
                    Object temp = animal.get("weight");
                    String aniUnit = (String) animal.get("weight_unit");
                    long aniReceipt = animal.getLong("receipt_date");
                    double aniWeight;

                    if (temp instanceof Double) {
                        aniWeight = (Double) temp;
                    } else {
                        aniWeight = ((Long) temp).doubleValue();
                    }

                    Animal ani = new Animal(aniType, aniName, aniID, aniWeight, aniUnit, aniReceipt);
                    currShelter.addAnimal(ani);
                    currShelter.setReceiving(receiving);
                }
                shelterRoster.put(id, currShelter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shelterRoster;
    }

    /**
     * Parses file retrieved via readXML and adds the data to roster
     * @param filename - name of file to import in readXML
     * @param roster - ShelterList XML data is added to
     */
    public static void parseIncomingXML(String filename, ShelterList roster){
        Document doc = FileUtilities.readXML(filename);
        NodeList nodeList = doc.getElementsByTagName("Shelter");

        for (int i = 0; i < nodeList.getLength(); i++) {
            //https://stackoverflow.com/questions/4138754/getting-an-attribute-value-in-xml-element
            Node currNode = nodeList.item(i);

            if(currNode.getNodeType() == Node.ELEMENT_NODE) {
                Element shelterEle = (Element) currNode;

                String id = getAttribute(currNode, "id");
                String name = elementString(shelterEle, "Name");

                Shelter shelter = new Shelter(id, name);
                roster.addShelter(id, shelter);

                NodeList animalList = ((Element) currNode).getElementsByTagName("Animal");
                for (int j = 0; j < animalList.getLength(); j++) {
                    Node aniNode = animalList.item(j);

                    if(aniNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element aniEle = (Element) aniNode;
                        String aniType = "unlisted";
                        String aniID = "unlisted";
                        String aniName = "unlisted";
                        String aniWeightUnit = "";
                        double aniWeight = 0.0;
                        long aniReceipt = 1111111111;

                        if (validAttribute(aniNode, "type")) {
                            aniType = getAttribute(aniNode, "type");
                        }
                        if(validAttribute(aniNode, "id")){
                            aniID = getAttribute(aniNode, "id");
                        }
                        if (validElement(aniEle, "Name")) {
                            aniName = elementString(aniEle, "Name");
                        }
                        if (validElement(aniEle, "Weight")) {
                            aniWeightUnit = getAttribute(getElementNode(aniEle, "Weight"), "unit");
                            aniWeight = Double.parseDouble(elementString(aniEle, "Weight"));
                        }
                        if (validElement(aniEle, "ReceiptDate")) {
                            aniReceipt = Long.parseLong(elementString(aniEle, "ReceiptDate"));
                        }

                        Animal tempAnimal = new Animal(aniType, aniName, aniID, aniWeight, aniWeightUnit, aniReceipt);
                        roster.addAnimalToShelter(id, tempAnimal);

                    }
                }
            }
        }
    }

    public static boolean validElement(Element element, String tag){
        return element.getElementsByTagName(tag).item(0) != null;
    }

    public static boolean validAttribute(Node node, String tag){
        return node.getAttributes().getNamedItem(tag) != null;
    }

    public static String getAttribute(Node node, String tag){
        return node.getAttributes().getNamedItem(tag).getNodeValue();
    }

    public static Node getElementNode(Element element, String tag){
        return element.getElementsByTagName(tag).item(0);
    }

    public static String elementString(Element element, String tag){
        return getElementNode(element, tag).getTextContent();
    }
}
