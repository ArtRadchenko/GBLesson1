package familyTree.presenters;

import familyTree.models.FamilyTree;
import familyTree.models.Person;
import familyTree.services.FileHandler;
import familyTree.views.FamilyTreeView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class FamilyTreePresenter {
    private FamilyTree<Person> model;
    private FamilyTreeView view;

    public FamilyTreePresenter(FamilyTree<Person> model, FamilyTreeView view) {
        this.model = model;
        this.view = view;
    }

    public FamilyTree<Person> getModel() {
        return model;
    }

    public void addPerson(String name, LocalDate birthDate, boolean isAlive, String gender) {
        Person person = new Person(name, birthDate, isAlive, gender);
        model.addElement(person);
    }

    public void editPerson(String name, LocalDate birthDate, boolean isAlive, LocalDate deathDate) {
        model.editElement(name, birthDate, isAlive, deathDate);
    }

    public Person findPersonByName(String name) {
        return model.findElementByName(name);
    }

    public List<Person> findChildren(String parentName) {
        return model.findChildren(parentName);
    }

    public void addPartner(String personName, Person partner) {
        model.addPartner(personName, partner);
    }

    public void addChild(String parentName, Person child) {
        model.addChild(parentName, child);
    }

    public void sortByName() {
        model.sortByName();
    }

    public void sortByBirthDate() {
        model.sortByBirthDate();
    }

    public void saveFamilyTreeToFile(String filename) {
        FileHandler<Person> fileHandler = new FileHandler<>();
        try {
            fileHandler.saveToFile(filename, model);
            view.displayMessage("Семейное дерево сохранено в файл: " + filename);
        } catch (IOException e) {
            view.displayError("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public void loadFamilyTreeFromFile(String filename) {
        FileHandler<Person> fileHandler = new FileHandler<>();
        try {
            FamilyTree<Person> loadedTree = fileHandler.loadFromFile(filename);
            model = loadedTree;
            view.displayMessage("Семейное дерево загружено из файла: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            view.displayError("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}