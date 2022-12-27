package hw6_java;

import java.util.HashMap;
import java.util.Objects;

public class Laptop {
    String serialNumber;
    int diagonal;
    int ram;
    String ramType;
    String processor;
    int hddCapacity;
    String operationSystem;
    String color;
    float price;

    public Laptop(String serialNumber, int diagonal, int ram, String ramType, String processor, int hddCapacity, String operationSystem, String color, float price) {
        this.serialNumber = serialNumber;
        this.diagonal = diagonal;
        this.ram = ram;
        this.ramType = ramType;
        this.processor = processor;
        this.hddCapacity = hddCapacity;
        this.operationSystem = operationSystem;
        this.color = color;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return diagonal == laptop.diagonal && ram == laptop.ram && Float.compare(laptop.price, price) == 0 && serialNumber.equals(laptop.serialNumber) && ramType.equals(laptop.ramType) && processor.equals(laptop.processor) && hddCapacity == laptop.hddCapacity && operationSystem.equals(laptop.operationSystem) && color.equals(laptop.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber, diagonal, ram, ramType, processor, hddCapacity, operationSystem, color, price);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "serialNumber='" + serialNumber + '\'' +
                ", diagonal=" + diagonal +
                ", ram=" + ram +
                ", ramType='" + ramType + '\'' +
                ", processor='" + processor + '\'' +
                ", hddCapacity=" + hddCapacity +
                ", operationSystem='" + operationSystem + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                "}";
    }

    public HashMap<String, Object> getMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("serialNumber", serialNumber);
        map.put("diagonal", diagonal);
        map.put("ram", ram);
        map.put("ramType", ramType);
        map.put("processor", processor);
        map.put("hddCapacity", hddCapacity);
        map.put("operationSystem", operationSystem);
        map.put("color", color);
        map.put("price", price);
        return map;
    }

}