package familyTree;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FamilyTree familyTree = new FamilyTree();

        // Создадим для примера несколько персон
        Person john = new Person("John", LocalDate.of(1950, 1, 1), true, "Male");
        Person jane = new Person("Jane", LocalDate.of(1955, 2, 2), true, "Female");
        Person child1 = new Person("Child1", LocalDate.of(1980, 3, 3), true, "Male");
        Person child2 = new Person("Child2", LocalDate.of(1982, 4, 4), true, "Female");

        // Добавим людей в генеалогическое дерево
        familyTree.addPerson(john);
        familyTree.addPerson(jane);
        familyTree.addPerson(child1);
        familyTree.addPerson(child2);

        // Добавим детей к родителям
        john.addChild(child1);
        john.addChild(child2);
        jane.addChild(child1);
        jane.addChild(child2);

        // Найдем и отредактируем персону затем покажем
        Person foundPerson = familyTree.findPersonByName("John");
        if (foundPerson != null) {
            familyTree.editPerson("John", LocalDate.of(1950, 1, 1), false, LocalDate.of(2020, 1, 1), "Male");
            // Выведем обновленный статус Джона
            System.out.println("Updated status of John:");
            System.out.println(foundPerson);
        }

        // Найдем детей персоны
        System.out.println("Children of John:");
        for (Person child : familyTree.findChildren("John")) {
            System.out.println(child.getName());
        }
    }
}