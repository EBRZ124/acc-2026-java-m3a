package org.example.menu;

import org.example.shelter.Shelter;
import org.example.model.*;
import org.example.service.*;

import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    private final AnimalShelterService animalShelterService;

    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
        this.animalShelterService = new AnimalShelterService(shelter);
    }

    public void start(){
        boolean systemUp = true;
        while (systemUp){
            printMenu();
            System.out.print("Choose a service: ");
            String input = scanner.nextLine().trim();

            int optionChosen;
            try {
                optionChosen = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number. Re-enter: ");
                continue;
            }
            switch (optionChosen){
                case 1 -> animalShelterService.addAnimal();
                case 2 -> animalShelterService.getAllAnimals();
                case 3 -> animalShelterService.getBySpecies();
                case 4 -> animalShelterService.getAvailableAnimals();
                case 5 -> animalShelterService.markAnimalAdopted();
                case 0 ->{
                    System.out.println("Goodbye.");
                    systemUp = false;
                }
                default -> System.out.println("This option doesn't exist. Try another: ");
            }
        }
    }

    private void printMenu(){
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                0. Exit
                """);
    }
}
