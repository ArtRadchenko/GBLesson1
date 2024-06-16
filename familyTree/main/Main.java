package familyTree.main;

import familyTree.models.FamilyTree;
import familyTree.models.Person;
import familyTree.presenters.FamilyTreePresenter;
import familyTree.services.FileHandler;
import familyTree.services.FamilyTreeService;
import familyTree.views.FamilyTreeView;

public class Main {
    public static void main(String[] args) {
        FamilyTree<Person> familyTree = new FamilyTree<>();
        FileHandler<FamilyTree<Person>> fileHandler = new FileHandler<>();
        FamilyTreeService<Person> familyTreeService = new FamilyTreeService<>(familyTree, fileHandler);
        FamilyTreeView view = new FamilyTreeView();
        FamilyTreePresenter presenter = new FamilyTreePresenter(familyTreeService, view);

        presenter.start();
    }
}