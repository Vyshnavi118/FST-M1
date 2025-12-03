package activities;

import java.util.HashMap;

public class Activity11 {
    public static void main(String[] args) {
        HashMap<Integer, String> colours = new HashMap<Integer, String>();

        colours.put(1, "Red");
        colours.put(2, "Green");
        colours.put(3, "Blue");
        colours.put(4, "White");
        colours.put(5, "Black");

        System.out.println("Original Map: " + colours);

        colours.remove(4);
        System.out.println("After removing White: " + colours);

        if (colours.containsValue("Green")) {
            System.out.println("Green exists in the Map");
        } else {
            System.out.println("Green does not exist in the Map");
        }

        System.out.println("Size of the Map: " + colours.size());
    }
}
