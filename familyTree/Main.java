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
    }
}