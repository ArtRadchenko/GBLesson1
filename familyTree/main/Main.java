package familyTree.main;

import familyTree.models.FamilyTree;
import familyTree.models.Person;
import familyTree.presenters.FamilyTreePresenter;
import familyTree.services.FileHandler;
import familyTree.views.FamilyTreeView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FamilyTree<Person> model = new FamilyTree<>();
        FamilyTreeView view = new FamilyTreeView();
        FamilyTreePresenter presenter = new FamilyTreePresenter(model, view);

        view.setPresenter(presenter);

        // Запуск основного цикла обработки команд пользователя
        view.run();
    }
}