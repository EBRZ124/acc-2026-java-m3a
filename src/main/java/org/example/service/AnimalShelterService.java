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

    private String validateInputSpecies() {
        List<String> allowedSpecies = List.of("dog", "cat", "bird", "tiger");
        String species;
        while (true){
            System.out.print("Enter species (dog, cat, bird, tiger): ");
            species = scanner.nextLine().trim().toLowerCase();
            if (allowedSpecies.contains(species)) {
                return species;
            }
            System.out.print("Invalid species. Only the given ones are allowed. ");
        }
    }
    private String validateInputName() {
        String name;
        while (true){
            System.out.print("Enter the animal's name: ");
            name = scanner.nextLine().trim();
            if(!name.isEmpty()){
                return name;
            }
            System.out.print("The name can not be empty! ");
        }
    }

    private int validateInputAge(String animalsName) {
        while (true){
            System.out.print("Enter " +animalsName+"s age: ");
            String input = scanner.nextLine().trim();
            try{
                int age = Integer.parseInt(input);
                if (age < 0){
                    System.out.println("Age can not be negative.");
                    continue;
                }
                return age;
            } catch (NumberFormatException e){
                System.out.print("Not a valid age number! ");
            }
        }
    }

    public void addAnimal() {
        String species = validateInputSpecies();
        String name = validateInputName();
        int age = validateInputAge(name);

        Animal animal = switch (species.toLowerCase()){
            case "dog" -> new Dog(new AnimalId(), name, age);
            case "cat" -> new Cat(new AnimalId(), name, age);
            case "bird" -> new Bird(new AnimalId(), name, age);
            case "tiger" -> new Tiger(new AnimalId(), name, age);

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
