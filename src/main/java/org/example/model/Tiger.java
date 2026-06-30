package org.example.model;

public final class Tiger extends Animal{

    public Tiger(AnimalId id, String name, int age){
        super(id,name,age);
    }

    public Tiger(String name, int age){
        this(new AnimalId(), name, age);
    }

    @Override
    public String getSpecies(){
        return "Tiger";
    }
}
