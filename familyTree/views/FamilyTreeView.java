package familyTree.views;

import familyTree.models.FamilyTree;
import familyTree.models.Person;
import familyTree.presenters.FamilyTreePresenter;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FamilyTreeView {
    private FamilyTreePresenter presenter;
    private Scanner scanner;

    public FamilyTreeView() {
        this.scanner = new Scanner(System.in);
    }

    public void setPresenter(FamilyTreePresenter presenter) {
        this.presenter = presenter;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    editPerson();
                    break;
                case 3:
                    findPerson();
                    break;
                case 4:
                    findChildren();
                    break;
                case 5:
                    addPartner();
                    break;
                case 6:
                    addChild();
                    break;
                case 7:
                    sortByName();
                    break;
                case 8:
                    sortByBirthDate();
                    break;
                case 9:
                    saveFamilyTree();
                    break;
                case 10:
                    loadFamilyTree();
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Добавить элемент в дерево");
        System.out.println("2. Редактировать данные элемента");
        System.out.println("3. Найти элемент по имени");
        System.out.println("4. Найти всех детей элемента");
        System.out.println("5. Добавить партнера к элементу");
        System.out.println("6. Добавить ребенка к элементу");
        System.out.println("7. Сортировать по имени");
        System.out.println("8. Сортировать по дате рождения");
        System.out.println("9. Сохранить древо в файл");
        System.out.println("10. Загрузить древо из файла");
        System.out.println("11. Выйти");
        System.out.print("Выберите действие: ");
    }

    private void addPerson() {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите дату рождения (гггг-мм-дд): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Введите пол (Мужской/Женский): ");
        String gender = scanner.nextLine();

        presenter.addPerson(name, birthDate, true, gender);
        System.out.println("Элемент добавлен в дерево.");
    }

    private void editPerson() {
        System.out.print("Введите имя элемента для редактирования: ");
        String name = scanner.nextLine();

        System.out.print("Введите новую дату рождения (гггг-мм-дд): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Введите статус жизни (true/false): ");
        boolean isAlive = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Введите дату смерти (гггг-мм-дд) или оставьте пустым, если жив: ");
        String deathDateStr = scanner.nextLine();
        LocalDate deathDate = (deathDateStr.isEmpty()) ? null : LocalDate.parse(deathDateStr);

        presenter.editPerson(name, birthDate, isAlive, deathDate);
        System.out.println("Данные элемента отредактированы.");
    }

    private void findPerson() {
        System.out.print("Введите имя элемента для поиска: ");
        String name = scanner.nextLine();

        Person foundPerson = presenter.findPersonByName(name);
        if (foundPerson != null) {
            System.out.println("Найденный элемент:");
            System.out.println(foundPerson);
        } else {
            System.out.println("Элемент не найден.");
        }
    }

    private void findChildren() {
        System.out.print("Введите имя родителя для поиска детей: ");
        String parentName = scanner.nextLine();

        List<Person> children = presenter.findChildren(parentName);
        if (!children.isEmpty()) {
            System.out.println("Дети " + parentName + ":");
            for (Person child : children) {
                System.out.println(child.getName());
            }
        } else {
            System.out.println(parentName + " не имеет детей.");
        }
    }

    private void addPartner() {
        System.out.print("Введите имя первого партнера: ");
        String personName1 = scanner.nextLine();

        System.out.print("Введите имя второго партнера: ");
        String personName2 = scanner.nextLine();

        Person person1 = presenter.findPersonByName(personName1);
        Person person2 = presenter.findPersonByName(personName2);

        if (person1 != null && person2 != null) {
            presenter.addPartner(personName1, person2);
            System.out.println(person2.getName() + " добавлен как партнер " + personName1);
        } else {
            System.out.println("Один из элементов не найден.");
        }
    }

    private void addChild() {
        System.out.print("Введите имя родителя: ");
        String parentName = scanner.nextLine();

        System.out.print("Введите имя ребенка: ");
        String childName = scanner.nextLine();

        Person parent = presenter.findPersonByName(parentName);
        if (parent != null) {
            Person child = presenter.findPersonByName(childName);
            if (child != null) {
                presenter.addChild(parentName, child);
                System.out.println(childName + " добавлен как ребенок " + parentName);
            } else {
                System.out.println("Ребенок не найден.");
            }
        } else {
            System.out.println("Родитель не найден.");
        }
    }

    private void sortByName() {
        presenter.sortByName();
        System.out.println("Дерево отсортировано по имени:");
        printFamilyTree(presenter.getModel());
    }

    private void sortByBirthDate() {
        presenter.sortByBirthDate();
        System.out.println("Дерево отсортировано по дате рождения:");
        printFamilyTree(presenter.getModel());
    }

    private void printFamilyTree(FamilyTree<Person> familyTree) {
        for (Person person : familyTree) {
            System.out.println(person);
        }
    }

    private void saveFamilyTree() {
        System.out.print("Введите имя файла для сохранения: ");
        String filename = scanner.nextLine();
        presenter.saveFamilyTreeToFile(filename);
    }

    private void loadFamilyTree() {
        System.out.print("Введите имя файла для загрузки: ");
        String filename = scanner.nextLine();
        presenter.loadFamilyTreeFromFile(filename);
    }
    
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.err.println(error);
    }
}