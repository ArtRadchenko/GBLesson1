package familyTree.services;

import familyTree.models.FamilyTree;
import familyTree.models.FamilyTreeElement;


import java.io.IOException;

public interface FileOperations<T extends FamilyTreeElement<T> & Comparable<T>> {
    void saveToFile(String filename, FamilyTree<T> familyTree) throws IOException;
    FamilyTree<T> loadFromFile(String filename) throws IOException, ClassNotFoundException;
}