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
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }








    /**
     * Test that shelters will only allow new animals to be added when shelter receiving status is true
     */
    @org.junit.Test
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

    /**
     *Test that shelter objects can be created with shelter ID alone and with BOTH shelter ID and name
     */
    @org.junit.Test
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

    /**
     * Test that new shelters can only be created when shelter ID is not already in collection
     */
    @org.junit.Test
    public void addDuplicateShelterTest(){
        Map<String, Shelter> mapOfSheltersTest = new HashMap<>();
        Shelter testShelter = new Shelter("12345", "Original Shelter");
        Shelter testShelterDuplicate = new Shelter("12345", "duplicate Shelter");

        Shelter testShelter2 = new Shelter("54321");
        assertEquals(mapOfSheltersTest.size(), 0);

        //initial shelter added to map
        mapOfSheltersTest.put(testShelter.getShelterID(), testShelter);
        assertEquals(mapOfSheltersTest.size(), 1);

        //additional unique shelter added to map
        mapOfSheltersTest.put(testShelter2.getShelterID(), testShelter2);
        assertEquals(mapOfSheltersTest.size(), 2);

    }
}

