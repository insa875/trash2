package model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("cat")
public class Cat extends Pet {

    @Column(name = "color")
    private String color;

    public Cat() {
    }

    public Cat(String name, String color, User owner) {
        super(name, owner);
        this.color = color;
    }

    public Cat(int id, String name, String color, User owner) {
        super(id, name, owner);
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                '}';
    }
}
