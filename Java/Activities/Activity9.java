package activities;

import java.util.ArrayList;

public class Activity9 {
    public static void main(String[] args) {
        ArrayList<String> myList = new ArrayList<String>();

        myList.add("Apple");
        myList.add("Mango");
        myList.add("Orange");
        myList.add("Grapes");
        myList.add("Papaya");

        System.out.println("All names in the list:");
        for (String name : myList) {
            System.out.println(name);
        }

        System.out.println("3rd name in the list: " + myList.get(2));
        System.out.println("Is 'Chicku' in the list? " + myList.contains("Chicku"));
        System.out.println("Size of the list: " + myList.size());

        myList.remove("Papaya");

        System.out.println("Size after removing 'Papaya': " + myList.size());
    }
}
