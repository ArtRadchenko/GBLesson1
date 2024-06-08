package familyTree.services;

import familyTree.models.FamilyTree;

import java.io.*;

public class FileHandler implements FileOperations {

    @Override
    public void saveToFile(String filename, FamilyTree familyTree) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(familyTree);
        }
    }

    @Override
    public FamilyTree loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (FamilyTree) ois.readObject();
        }
    }
}