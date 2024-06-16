package familyTree.presenters;

import familyTree.models.Person;
import familyTree.services.FamilyTreeService;
import familyTree.views.FamilyTreeView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FamilyTreePresenter {
    private FamilyTreeService<Person> familyTreeService;
    private FamilyTreeView view;
    private Scanner scanner;

    public FamilyTreePresenter(FamilyTreeService<Person> familyTreeService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.displayMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addElement();
                    break;
                case "2":
                    editElement();
                    break;
                case "3":
                    findElementByName();
                    break;
                case "4":
                    findChildren();
                    break;
                case "5":
                    addPartner();
                    break;
                case "6":
                    addChild();
                    break;
                case "7":
                    sortByName();
                    break;
                case "8":
                    sortByBirthDate();
                    break;
                case "9":
                    saveToFile();
                    break;
                case "10":
                    loadFromFile();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    view.displayError("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private void addElement() {
        view.displayMessage("Введите имя:");
        String name = scanner.nextLine();
        view.displayMessage("Введите дату рождения (YYYY-MM-DD):");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        view.displayMessage("Жив ли персонаж (true/false):");
        boolean isAlive = Boolean.parseBoolean(scanner.nextLine());
        view.displayMessage("Введите пол:");
        String gender = scanner.nextLine();

        Person person = new Person(name, birthDate, isAlive, gender);
        familyTreeService.addElement(person);
        view.displayMessage("Персонаж добавлен.");
    }

    private void editElement() {
        view.displayMessage("Введите имя персонажа для редактирования:");
        String name = scanner.nextLine();
        view.displayMessage("Введите новую дату рождения (YYYY-MM-DD):");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        view.displayMessage("Жив ли персонаж (true/false):");
        boolean isAlive = Boolean.parseBoolean(scanner.nextLine());
        view.displayMessage("Введите дату смерти (YYYY-MM-DD), если применимо:");
        LocalDate deathDate = null;
        String deathDateString = scanner.nextLine();
        if (!deathDateString.isEmpty()) {
            deathDate = LocalDate.parse(deathDateString);
        }

        familyTreeService.editElement(name, birthDate, isAlive, deathDate);
        view.displayMessage("Персонаж отредактирован.");
    }

    private void findElementByName() {
        view.displayMessage("Введите имя персонажа:");
        String name = scanner.nextLine();
        Person person = familyTreeService.findElementByName(name);
        if (person != null) {
            view.displayMessage("Найденный персонаж: " + person);
        } else {
            view.displayError("Персонаж не найден.");
        }
    }

    private void findChildren() {
        view.displayMessage("Введите имя родителя:");
        String name = scanner.nextLine();
        List<Person> children = familyTreeService.findChildren(name);
        if (!children.isEmpty()) {
            view.displayMessage("Дети: " + children);
        } else {
            view.displayError("Дети не найдены.");
        }
    }

    private void addPartner() {
        view.displayMessage("Введите имя персонажа:");
        String name = scanner.nextLine();
        view.displayMessage("Введите имя партнера:");
        String partnerName = scanner.nextLine();

        Person partner = familyTreeService.findElementByName(partnerName);
        if (partner != null) {
            familyTreeService.addPartner(name, partner);
            view.displayMessage("Партнер добавлен.");
        } else {
            view.displayError("Партнер не найден.");
        }
    }

    private void addChild() {
        view.displayMessage("Введите имя родителя:");
        String parentName = scanner.nextLine();
        view.displayMessage("Введите имя ребенка:");
        String childName = scanner.nextLine();
        view.displayMessage("Введите дату рождения ребенка (YYYY-MM-DD):");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());
        view.displayMessage("Жив ли ребенок (true/false):");
        boolean isAlive = Boolean.parseBoolean(scanner.nextLine());
        view.displayMessage("Введите пол ребенка:");
        String gender = scanner.nextLine();

        Person child = new Person(childName, birthDate, isAlive, gender);
        familyTreeService.addChild(parentName, child);
        view.displayMessage("Ребенок добавлен.");
    }

    private void sortByName() {
        familyTreeService.sortByName();
        view.displayMessage("Древо отсортировано по имени.");
    }

    private void sortByBirthDate() {
        familyTreeService.sortByBirthDate();
        view.displayMessage("Древо отсортировано по дате рождения.");
    }

    private void saveToFile() {
        view.displayMessage("Введите имя файла для сохранения:");
        String fileName = scanner.nextLine();
        try {
            familyTreeService.saveToFile(fileName);
            view.displayMessage("Древо сохранено в файл.");
        } catch (IOException e) {
            view.displayError("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        view.displayMessage("Введите имя файла для загрузки:");
        String fileName = scanner.nextLine();
        try {
            familyTreeService.loadFromFile(fileName);
            view.displayMessage("Древо загружено из файла.");
        } catch (IOException | ClassNotFoundException e) {
            view.displayError("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}