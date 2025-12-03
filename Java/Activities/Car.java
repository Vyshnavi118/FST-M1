package activities;

public class Car {

    String name;
    String color;
    String transmission;
    int make;
    int tyres;
    int doors;

    // Constructor
    public Car() {
        tyres = 4;
        doors = 4;
    }

    void displayCharacteristics() {
        System.out.println("Car Name: " + name);
        System.out.println("Car Color: " + color);
        System.out.println("Car Transmission: " + transmission);
        System.out.println("Car Make: " + make);
        System.out.println("Number of Tyres: " + tyres);
        System.out.println("Number of Doors: " + doors);
    }

    void accelerate() {
        System.out.println("Car is moving forward.");
    }

    void brake() {
        System.out.println("Car has stopped.");
    }
}
