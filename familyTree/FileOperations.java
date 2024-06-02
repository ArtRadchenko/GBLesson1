package familyTree;

import java.io.IOException;

public interface FileOperations {
    void saveToFile(String filename, FamilyTree familyTree) throws IOException;
    FamilyTree loadFromFile(String filename) throws IOException, ClassNotFoundException;
}
