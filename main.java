// Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
// Создать множество ноутбуков.
// Написать метод, который будет запрашивать у пользователя критерий (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map. Например:
// “Введите цифру, соответствующую необходимому критерию:
// 1 - ОЗУ
// 2 - Объем ЖД
// 3 - Операционная система
// 4 - Цвет …
// Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
// Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

package hw6_java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        var store = new LaptopStore();
        fillStore(store);
        showStore(store);

        HashMap<String, Object> filterParams = new HashMap<>();
        HashSet<Laptop> filteredLaptops = new HashSet<>();
        getFilters(filterParams, store, filteredLaptops);
    }

    private static void showStore(LaptopStore store) {
        System.out.println();
        System.out.println("Каталог ноутбуков:");
        System.out.println(store);
    }

    private static void fillStore(LaptopStore store) {
        var laptop1 = new Laptop("124", 15, 32, "DDR3", "Intel Core 2 Duo", 1, "Windows 10", "Silver", 150000);
        var laptop2 = new Laptop("125", 16, 16, "DDR2", "Celeron N4020", 1, "Windows 7", "Silver", 30000);
        var laptop3 = new Laptop("126", 17, 8, "DDR5", "Intel Core i7", 2, "Windows 8", "Black", 120000);
        var laptop4 = new Laptop("127", 14, 16, "DDR3", "Intel Core i9", 2, "Windows 11", "White", 40000);
        var laptop5 = new Laptop("128", 23, 4, "DDR4", "AMD Ryzen 5", 1, "Windows 7", "Silver", 80000);
        var laptop6 = new Laptop("129", 15, 32, "DDR4", "Celeron J4125", 2, "Windows 8", "Black", 100000);


        store.add(laptop1);
        store.add(laptop2);
        store.add(laptop3);
        store.add(laptop4);
        store.add(laptop5);
        store.add(laptop6);
    }

    public static void getFilters(HashMap<String, Object> filterParams, LaptopStore store, HashSet<Laptop> filteredLaptops) throws NoSuchFieldException {
        showMenu();

        Scanner scanner = new Scanner(System.in);
        String s;
        while (scanner.hasNextLine() && !((s = scanner.nextLine()).equalsIgnoreCase("q"))) {
            String param = getParam(s);

            System.out.println();
            System.out.print("Введите минимальное значение: ");
            String valueInput = scanner.nextLine();

            filterParams.put(param, valueInput);
            System.out.println("Выбранные фильтры: " + filterParams);


            filteredLaptops = multiFilter(filterParams, store.getLaptops());
            printLaptops(filteredLaptops);

            System.out.print("""
                    1 – Добавить ещё фильтр
                    2 – Очистить фильтры
                    """);
            s = scanner.nextLine();
            if (s.equals("2")) {
                filterParams = new HashMap<>();
                filteredLaptops = store.getLaptops();
                showStore(store);
                getFilters(filterParams, store, filteredLaptops);
            } else if (s.equals("1")) {
                getFilters(filterParams, store, filteredLaptops);
            } else {
                scanner.close();
                break;
            }

        }
    }

    public static HashSet<Laptop> multiFilter(HashMap<String, Object> filterParams, HashSet<Laptop> laptops) throws NoSuchFieldException {
        for (Map.Entry<String, Object> entry : filterParams.entrySet()) {
            String param = entry.getKey();
            String value = entry.getValue().toString();
            String valueType = Laptop.class.getDeclaredField(param).getType().getTypeName();

            if (valueType.equalsIgnoreCase("java.lang.String")) {
                laptops = filter(param, String.valueOf(value), laptops);
            } else if (valueType.equals("int")) {
                laptops = filter(param, Integer.parseInt(value), laptops);
            } else if (valueType.equals("float")) {
                laptops = filter(param, Float.parseFloat(value), laptops);
            }
        }
        return laptops;
    }

    public static HashSet<Laptop> filter(String param, Object value, HashSet<Laptop> laptops) {
        HashSet<Laptop> filtered = new HashSet<>();

        for (Laptop laptop : laptops) {
            for (Map.Entry<String, Object> entry : laptop.getMap().entrySet()) {
                if (entry.getKey().equals(param)) {
                    if (String.valueOf(entry.getValue()).equalsIgnoreCase(String.valueOf(value))) {
                        filtered.add(laptop);
                    } else {
                        if ((value instanceof Float || value instanceof Integer) && (entry.getValue() instanceof Float || entry.getValue() instanceof Integer)) {
                            if (Float.parseFloat(String.valueOf(entry.getValue())) >= Float.parseFloat(String.valueOf(value))) {
                                filtered.add(laptop);
                            }
                        }
                    }
                }
            }
        }
        return filtered;
    }

    public static void printLaptops(HashSet<Laptop> laptops) {
        if (laptops.size() > 0) {
            System.out.println("Найденные варианты:");
            for (Laptop laptop : laptops) {
                System.out.println(laptop);
            }
        } else {
            System.out.println("По заданным фильтрам ничего не найдено.");
        }
    }

    public static String getParam(String s) {
        String param = "";
        switch (s) {
            case "1" -> param = "diagonal";
            case "2" -> param = "ram";
            case "3" -> param = "ramType";
            case "4" -> param = "processor";
            case "5" -> param = "hddCapacity";
            case "6" -> param = "operationSystem";
            case "7" -> param = "color";
            case "8" -> param = "price";
        }
        return param;
    }

    public static void showMenu() {
        System.out.println("""
                Выберите параметр для фильтра:
                1 – диагональ, дюймы
                2 – объём оперативной памяти, Гб
                3 – тип оперативной памяти, Гб
                4 – процессор
                5 – объём диска, Тб
                6 – операционная система
                7 – цвет
                8 – цена
                q - выход
                """);
        System.out.print("Ваш выбор: ");
    }
}
