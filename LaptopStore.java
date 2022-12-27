package hw6_java;

import java.util.HashSet;

public class LaptopStore {
    HashSet<Laptop> laptops = new HashSet<>();

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (Laptop laptop : laptops) {
            s.append(laptop).append("\n");
        }
        return s.toString();
    }

    public HashSet<Laptop> getLaptops() {
        return laptops;
    }

    public void add(Laptop laptop) {
        this.laptops.add(laptop);
    }

}
