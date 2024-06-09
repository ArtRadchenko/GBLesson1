package familyTree.services;

import familyTree.models.FamilyTree;
import familyTree.models.FamilyTreeElement;

import java.io.*;

public class FileHandler<T extends FamilyTreeElement<T> & Comparable<T> & Serializable> implements FileOperations<T> {

    @Override
    public void saveToFile(String filename, FamilyTree<T> familyTree) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(familyTree);
        }
    }

    @Override
    public FamilyTree<T> loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (FamilyTree<T>) ois.readObject();
        }
    }
}