package org.example.model;
import lombok.Getter;

public sealed abstract class Animal permits Dog,Cat,Bird,Tiger {

    @Getter
    private final AnimalId id;
    @Getter
    private String name;
    @Getter
    private int age;
    @Getter
    private AdoptionStatus adoptionStatus;

    protected Animal(AnimalId id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.adoptionStatus = AdoptionStatus.AVAILABLE;
    }

    public void markAsAdopted(){
        this.adoptionStatus = AdoptionStatus.ADOPTED;
    }

    public abstract String getSpecies();

    @Override
    public String toString(){
        return id + " | " + name + " | " + age + " years old | " + getSpecies() + " | " + adoptionStatus;
    }
}
