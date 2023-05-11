package in.maxwellchristian.androiddemos.recycler_demo;

import androidx.annotation.NonNull;

public class Person {

    private String name;
    private String address;

    private String colour;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
        this.colour = "Blue";
    }

    public Person(String name, String address, String colour) {
        this.name = name;
        this.address = address;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " lives at " + address;
    }
}
