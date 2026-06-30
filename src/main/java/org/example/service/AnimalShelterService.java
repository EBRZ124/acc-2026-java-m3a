package org.example.service;
import org.example.model.AnimalId;
import org.example.model.*;
import org.example.shelter.*;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.List;

public class AnimalShelterService {
    private final Scanner scanner = new Scanner(System.in);
    private Shelter<Animal> shelter;

    public AnimalShelterService(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void addAnimal() {
        System.out.println("Enter species (dog, cat, or bird): ");
        String species = scanner.nextLine().trim();

        System.out.println("Enter the animals name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter " + name + "s age: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age, aborting.");
            return;
        }

        Animal animal = switch (species.toLowerCase()){
            case "dog" -> new Dog(new AnimalId(), name, age);
            case "cat" -> new Cat(new AnimalId(), name, age);
            case "bird" -> new Bird(new AnimalId(), name, age);
            default -> null;
        };

        if (animal == null){
            System.out.println("Unknown species, contact a biologist.");
            return;
        }

        shelter.addAnimal(animal);
        System.out.println("Added: " + animal + "\n");

    }

    public void getAllAnimals(){
        List<Animal> animals = shelter.getAllAnimals();
        if (animals.isEmpty()){
            System.out.println("There are no animals in the shelter...");
            return;
        }
        animals.forEach(System.out::println);
        System.out.println();
    }

    public void getBySpecies(){
        System.out.println("Enter specific species: ");
        String species = scanner.nextLine().trim();
        List<Animal> results = shelter.findBySpecies(species);
        if (results.isEmpty()){
            System.out.println("No animals for species: " + species);
            return;
        }
        results.forEach(System.out::println);
        System.out.println();
    }

    public void getAvailableAnimals(){
        List<Animal> availableAnimals = shelter.findAvailableAnimals();
        if (availableAnimals.isEmpty()){
            System.out.println("No animals are currently available...");
            return;
        }
        availableAnimals.forEach(System.out::println);
        System.out.println();
    }

    public void markAnimalAdopted(){
        System.out.print("Enter animal's id: ");
        String animalId = scanner.nextLine().trim();

        shelter.markAsAdopted(animalId);

    }

}
