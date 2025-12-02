package activities;

public class Activity1 {

    public static void main(String[] args) {

        Car verna = new Car();

        verna.name = "Verna";
        verna.make = 2014;
        verna.color = "Black";
        verna.transmission = "Manual";

        verna.displayCharacteristics();
        verna.accelerate();
        verna.brake();
    }
}
