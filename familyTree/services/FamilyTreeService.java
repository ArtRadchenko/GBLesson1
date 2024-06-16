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

    public void addElement(T element) {
        familyTree.addElement(element);
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

    public void addChild(String parentName, T child) {
        familyTree.addChild(parentName, child);
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
        this.familyTree = fileHandler.loadFromFile(fileName);
    }

    public FamilyTree<T> getFamilyTree() {
        return familyTree;
    }
}