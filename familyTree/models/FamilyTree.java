package familyTree.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FamilyTree implements Serializable, Iterable<Person> {
    private static final long serialVersionUID = 1L;

    private List<Person> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    // Добавление человека в дерево
    public void addPerson(Person person) {
        this.people.add(person);
    }

    // Редактирование данных человека
    public void editPerson(String name, LocalDate birthDate, boolean isAlive, LocalDate deathDate, String gender) {
        Person person = findPersonByName(name);
        if (person != null) {
            person.setBirthDate(birthDate);
            person.setAlive(isAlive);
            person.setDeathDate(deathDate);
            person.setGender(gender);
        }
    }

    // Поиск человека по имени
    public Person findPersonByName(String name) {
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }

    // Поиск всех детей человека
    public List<Person> findChildren(String parentName) {
        Person parent = findPersonByName(parentName);
        if (parent != null) {
            return parent.getChildren();
        }
        return new ArrayList<>();
    }

    // Добавление партнера к человеку
    public void addPartner(String personName, Person partner) {
        Person person = findPersonByName(personName);
        if (person != null) {
            person.addPartner(partner);
            partner.addPartner(person); // Также добавляем обратную связь
        }
    }

    // Добавление ребенка к человеку
    public void addChild(String parentName, Person child) {
        Person parent = findPersonByName(parentName);
        if (parent != null) {
            parent.addChild(child);
        }
    }

    // Сортировка по имени
    public void sortByName() {
        Collections.sort(this.people);
    }

    // Сортировка по дате рождения
    public void sortByBirthDate() {
        this.people.sort((p1, p2) -> p1.getBirthDate().compareTo(p2.getBirthDate()));
    }

    @Override
    public Iterator<Person> iterator() {
        return people.iterator();
    }

    @Override
    public String toString() {
        return "FamilyTree{" +
                "people=" + people +
                '}';
    }
}