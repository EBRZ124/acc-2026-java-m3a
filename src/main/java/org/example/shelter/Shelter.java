package org.example.shelter;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return animals;
    }

    public List<T> findBySpecies(String species){
        List<T> animalsOfSpecies = new ArrayList<>();

        for(T animal : animals){
            if (animal.getSpecies().equalsIgnoreCase(species)){
                animalsOfSpecies.add(animal);
            }
        }
        return animalsOfSpecies;
    }

    public List<T> findAvailableAnimals(){
        List<T> availableAnimals = new ArrayList<>();

        for(T animal : animals){
            if (animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE){
                availableAnimals.add(animal);
            }
        }

        return availableAnimals;
    }

    public void markAsAdopted(String id){
        boolean notFound = true;
        for (T animal : animals){
            if (animal.getId().toString().equals(id)){
                animal.markAsAdopted();
                System.out.println("Animal with id: " + id + " is now adopted :) \n");
                notFound = false;
            }
        }
        if (notFound){
            System.out.println("Animal with id: " + id + " not found \n");
        }
    }
}
