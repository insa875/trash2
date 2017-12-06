package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("dog")
public class Dog extends Pet {
    private int strength;

    public Dog() {

    }

    public Dog(String name, int strength, User owner) {
        super(name, owner);
        this.strength = strength;
    }

    public Dog(int id, String name, int strength, User owner) {
        super(id, name, owner);
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "strength=" + strength +
                '}';
    }
}
