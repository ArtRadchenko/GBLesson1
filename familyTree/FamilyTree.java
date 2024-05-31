package familyTree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FamilyTree {
    private List<Person> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    // Добавить персону
    public void addPerson(Person person) {
        this.people.add(person);
    }
    
   // Редактировать персону
    public void editPerson(String name, LocalDate birthDate, boolean isAlive, LocalDate deathDate, String gender) {
        Person person = findPersonByName(name);
        if (person != null) {
            person.setBirthDate(birthDate);
            person.setAlive(isAlive);
            person.setDeathDate(deathDate);
            person.setGender(gender);
        }
    }

    // Поиск персоны по имени
    public Person findPersonByName(String name) {
        for (Person person : people) {
            if (person.getName().equalsIgnoreCase(name)) {
                return person;
            }
        }
        return null;
    }
}