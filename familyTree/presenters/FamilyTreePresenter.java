package familyTree.presenters;

import familyTree.models.Person;
import familyTree.services.FamilyTreeService;
import familyTree.views.FamilyTreeView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class FamilyTreePresenter {
    private FamilyTreeService<Person> familyTreeService;
    private FamilyTreeView view;

    public FamilyTreePresenter(FamilyTreeService<Person> familyTreeService, FamilyTreeView view) {
        this.familyTreeService = familyTreeService;
        this.view = view;
    }

    public void start() {
        boolean running = true;
        while (running) {
            view.displayMenu();
            String choice = view.getInput();

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
        String name = view.getInput("Введите имя:");
        LocalDate birthDate = LocalDate.parse(view.getInput("Введите дату рождения (YYYY-MM-DD):"));
        boolean isAlive = Boolean.parseBoolean(view.getInput("Жив ли персонаж (true/false):"));
        String gender = view.getInput("Введите пол:");

        familyTreeService.addElement(name, birthDate, isAlive, gender);
        view.displayMessage("Персонаж добавлен.");
    }

    private void editElement() {
        String name = view.getInput("Введите имя персонажа для редактирования:");
        LocalDate birthDate = LocalDate.parse(view.getInput("Введите новую дату рождения (YYYY-MM-DD):"));
        boolean isAlive = Boolean.parseBoolean(view.getInput("Жив ли персонаж (true/false):"));
        LocalDate deathDate = null;
        String deathDateString = view.getInput("Введите дату смерти (YYYY-MM-DD), если применимо:");
        if (!deathDateString.isEmpty()) {
            deathDate = LocalDate.parse(deathDateString);
        }

        familyTreeService.editElement(name, birthDate, isAlive, deathDate);
        view.displayMessage("Персонаж отредактирован.");
    }

    private void findElementByName() {
        String name = view.getInput("Введите имя персонажа:");
        Person person = familyTreeService.findElementByName(name);
        if (person != null) {
            view.displayMessage("Найденный персонаж: " + person);
        } else {
            view.displayError("Персонаж не найден.");
        }
    }

    private void findChildren() {
        String name = view.getInput("Введите имя родителя:");
        List<Person> children = familyTreeService.findChildren(name);
        if (!children.isEmpty()) {
            view.displayMessage("Дети: " + children);
        } else {
            view.displayError("Дети не найдены.");
        }
    }

    private void addPartner() {
        String name = view.getInput("Введите имя персонажа:");
        String partnerName = view.getInput("Введите имя партнера:");

        Person partner = familyTreeService.findElementByName(partnerName);
        if (partner != null) {
            familyTreeService.addPartner(name, partner);
            view.displayMessage("Партнер добавлен.");
        } else {
            view.displayError("Партнер не найден.");
        }
    }

    private void addChild() {
        String parentName = view.getInput("Введите имя родителя:");
        String childName = view.getInput("Введите имя ребенка:");
        LocalDate birthDate = LocalDate.parse(view.getInput("Введите дату рождения ребенка (YYYY-MM-DD):"));
        boolean isAlive = Boolean.parseBoolean(view.getInput("Жив ли ребенок (true/false):"));
        String gender = view.getInput("Введите пол ребенка:");

        familyTreeService.addChild(parentName, childName, birthDate, isAlive, gender);
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
        String fileName = view.getInput("Введите имя файла для сохранения:");
        try {
            familyTreeService.saveToFile(fileName);
            view.displayMessage("Древо сохранено в файл.");
        } catch (IOException e) {
            view.displayError("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        String fileName = view.getInput("Введите имя файла для загрузки:");
        try {
            familyTreeService.loadFromFile(fileName);
            view.displayMessage("Древо загружено из файла.");
        } catch (IOException | ClassNotFoundException e) {
            view.displayError("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}