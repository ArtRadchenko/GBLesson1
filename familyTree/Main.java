package familyTree;

import java.time.LocalDate;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        // Создание персонажей
        Person иван = new Person("Иван", LocalDate.of(1950, 1, 1), true, "Мужской");
        Person мария = new Person("Мария", LocalDate.of(1955, 2, 2), true, "Женский");
        Person наталья = new Person("Наталья", LocalDate.of(1960, 3, 3), true, "Женский");
        Person алексей = new Person("Алексей", LocalDate.of(1980, 4, 4), true, "Мужской");
        Person екатерина = new Person("Екатерина", LocalDate.of(1985, 5, 5), true, "Женский");
        Person дмитрий = new Person("Дмитрий", LocalDate.of(1982, 6, 6), true, "Мужской");

        // Добавление персонажей в семейное дерево
        familyTree.addPerson(иван);
        familyTree.addPerson(мария);
        familyTree.addPerson(наталья);
        familyTree.addPerson(алексей);
        familyTree.addPerson(екатерина);
        familyTree.addPerson(дмитрий);

        // Добавление партнеров
        familyTree.addPartner("Иван", мария);
        familyTree.addPartner("Иван", наталья);

        // Добавление детей от разных браков
        familyTree.addChild("Иван", алексей);  // Ребенок от Марии
        familyTree.addChild("Иван", екатерина); // Ребенок от Натальи

        // Вывод обновленного статуса Ивана
        Person найденныйИван = familyTree.findPersonByName("Иван");
        if (найденныйИван != null) {
            familyTree.editPerson("Иван", LocalDate.of(1950, 1, 1), false, LocalDate.of(2020, 1, 1), "Мужской");
            System.out.println("Обновленный статус Ивана:");
            System.out.println(найденныйИван);
        }

        // Поиск детей Ивана
        System.out.println("Дети Ивана:");
        for (Person child : familyTree.findChildren("Иван")) {
            System.out.println(child.getName());
        }

        // Сохранение в файл
        FileHandler fileHandler = new FileHandler();
        try {
            fileHandler.saveToFile("familyTree.dat", familyTree);
            System.out.println("Семейное дерево сохранено в файл.");

            // Загрузка из файла
            FamilyTree загруженноеДерево = fileHandler.loadFromFile("familyTree.dat");
            System.out.println("Загруженное семейное дерево из файла:");
            System.out.println(загруженноеДерево);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
