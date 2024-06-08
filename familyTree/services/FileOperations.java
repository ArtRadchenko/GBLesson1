package familyTree.services;

import familyTree.models.FamilyTree;

import java.io.IOException;

public interface FileOperations {
    void saveToFile(String filename, FamilyTree familyTree) throws IOException;
    FamilyTree loadFromFile(String filename) throws IOException, ClassNotFoundException;
}