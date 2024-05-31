package familyTree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private LocalDate birthDate;
    private boolean isAlive;
    private LocalDate deathDate;
    private String gender;
    private List<Person> children;

    public Person(String name, LocalDate birthDate, boolean isAlive, String gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.isAlive = isAlive;
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Person> getChildren() {
        return children;
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", isAlive=" + isAlive +
                ", deathDate=" + (deathDate != null ? deathDate : "N/A") +
                ", gender='" + gender + '\'' +
                ", children=" + getChildrenNames() +
                '}';
    }

    private String getChildrenNames() {
        List<String> names = new ArrayList<>();
        for (Person child : children) {
            names.add(child.getName());
        }
        return String.join(", ", names);
    }
}