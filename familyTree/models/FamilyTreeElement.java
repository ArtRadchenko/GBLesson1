package familyTree.models;

import java.time.LocalDate;
import java.util.List;

public interface FamilyTreeElement<T> {
    String getName();
    LocalDate getBirthDate();
    void setBirthDate(LocalDate birthDate);  // Добавляем этот метод
    void addChild(T child);
    List<T> getChildren();
    void addPartner(T partner);
    List<T> getPartners();
    boolean isAlive();
    void setAlive(boolean isAlive);
    LocalDate getDeathDate();
    void setDeathDate(LocalDate deathDate);
}