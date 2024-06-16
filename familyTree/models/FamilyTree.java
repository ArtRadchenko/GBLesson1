package familyTree.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FamilyTree<T extends FamilyTreeElement<T> & Comparable<T>> implements Serializable, Iterable<T> {
    private static final long serialVersionUID = 1L;
    private List<T> elements;

    public FamilyTree() {
        this.elements = new ArrayList<>();
    }

    public void addElement(T element) {
        this.elements.add(element);
    }

    public void editElement(String name, LocalDate birthDate, boolean isAlive, LocalDate deathDate) {
        T element = findElementByName(name);
        if (element != null) {
            element.setBirthDate(birthDate);
            element.setAlive(isAlive);
            element.setDeathDate(deathDate);
        }
    }

    public T findElementByName(String name) {
        for (T element : elements) {
            if (element.getName().equalsIgnoreCase(name)) {
                return element;
            }
        }
        return null;
    }

    public List<T> findChildren(String parentName) {
        T parent = findElementByName(parentName);
        if (parent != null) {
            return parent.getChildren();
        }
        return new ArrayList<>();
    }

    public void addPartner(String elementName, T partner) {
        T element = findElementByName(elementName);
        if (element != null) {
            element.addPartner(partner);
            partner.addPartner(element);
        }
    }

    public void addChild(String parentName, T child) {
        T parent = findElementByName(parentName);
        if (parent != null) {
            parent.addChild(child);
        }
    }

    public void sortByName() {
        Collections.sort(this.elements);
    }

    public void sortByBirthDate() {
        this.elements.sort((e1, e2) -> e1.getBirthDate().compareTo(e2.getBirthDate()));
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public String toString() {
        return "FamilyTree{" +
                "elements=" + elements +
                '}';
    }
}