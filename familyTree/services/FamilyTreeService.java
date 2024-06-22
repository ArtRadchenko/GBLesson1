package familyTree.services;

import familyTree.models.FamilyTree;
import familyTree.models.FamilyTreeElement;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class FamilyTreeService<T extends FamilyTreeElement<T> & Comparable<T>> {
    private FamilyTree<T> familyTree;
    private FileOperations<FamilyTree<T>> fileHandler;

    public FamilyTreeService(FamilyTree<T> familyTree, FileOperations<FamilyTree<T>> fileHandler) {
        this.familyTree = familyTree;
        this.fileHandler = fileHandler;
    }

    public void addElement(String name, LocalDate birthDate, boolean isAlive, String gender) {
        familyTree.addElement(name, birthDate, isAlive, gender);
    }

    public void editElement(String name, LocalDate birthDate, boolean isAlive, LocalDate deathDate) {
        familyTree.editElement(name, birthDate, isAlive, deathDate);
    }

    public T findElementByName(String name) {
        return familyTree.findElementByName(name);
    }

    public List<T> findChildren(String parentName) {
        return familyTree.findChildren(parentName);
    }

    public void addPartner(String elementName, T partner) {
        familyTree.addPartner(elementName, partner);
    }

    public void addChild(String parentName, String childName, LocalDate birthDate, boolean isAlive, String gender) {
        familyTree.addChild(parentName, childName, birthDate, isAlive, gender);
    }

    public void sortByName() {
        familyTree.sortByName();
    }

    public void sortByBirthDate() {
        familyTree.sortByBirthDate();
    }

    public void saveToFile(String fileName) throws IOException {
        fileHandler.saveToFile(familyTree, fileName);
    }

    public void loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        familyTree = fileHandler.loadFromFile(fileName);
    }
}