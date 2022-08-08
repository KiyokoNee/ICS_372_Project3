package edu.metrostate.sheltertracker;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {

    @Test
    public void getReceivingStatusTest(){
        Shelter shelter = new Shelter("12345");
        //test that default status is true
        assertTrue(shelter.isReceiving());
        System.out.println("Status: " + shelter.isReceiving());

        //test that changeReceiving toggles to false when status starts as true
        shelter.setReceiving(false);
        assertFalse(shelter.isReceiving());
        System.out.println("Status: " + shelter.isReceiving());

        //test that changeReceiving toggles to true when status starts as false
        shelter.setReceiving(true);
        assertTrue(shelter.isReceiving());
        System.out.println("Status: " + shelter.isReceiving());

    }

    @Test
    public void addAnimalReceivingTest(){
        Shelter shelter = new Shelter("12345");
        Animal testAnimal = new Animal(
                "bird", "test", "12345",
                0.5, "lb", 1732299329);

        //test that animals can not be added to a shelter that is not receiving
        shelter.setReceiving(false);
        shelter.addAnimal(testAnimal);
        assertEquals(shelter.size(), 0);

        //test that animals can be added to a shelter that is receiving
        shelter.setReceiving(true);
        shelter.addAnimal(testAnimal);
        assertEquals(shelter.size(), 1);

    }

    @Test
    public void addShelterMissingFieldsTest(){
        Shelter testShelter = new Shelter("12345");
        Shelter testShelter2 = new Shelter("12345", "Bob's Shelter");

        //test that default value for isReceiving is true
        assertTrue(testShelter.isReceiving());

        //test that added shelter name is listed when object is created
        assertEquals(testShelter2.getShelterName(), "Bob's Shelter");

        //test that if shelterName is not provided, "unlisted is provided"
        assertEquals(testShelter.getShelterName(), "unlisted");
    }

    @Test
    public void addDuplicateShelterTest(){
        ShelterList mapOfSheltersTest = new ShelterList();
        Shelter testShelter = new Shelter("12345", "Original Shelter");
        Shelter duplicateShelter = new Shelter("12345", "Duplicate Shelter");

        Shelter testShelter2 = new Shelter("54321");
        assertEquals(mapOfSheltersTest.getShelterQuantity(), 0);

        //initial shelter added to map
        mapOfSheltersTest.addShelter(testShelter.getShelterID(), testShelter);
        assertEquals(mapOfSheltersTest.getShelterQuantity(), 1);

        //additional unique shelter added to map
        mapOfSheltersTest.addShelter(testShelter2.getShelterID(), testShelter2);
        assertEquals(mapOfSheltersTest.getShelterQuantity(), 2);

        //attempt to add duplicate shelter
        mapOfSheltersTest.addShelter(duplicateShelter.getShelterID(), duplicateShelter);
        assertEquals(mapOfSheltersTest.getShelter("12345").getShelterName(), "Original Shelter");

    }
}