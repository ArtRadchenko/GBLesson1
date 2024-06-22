package familyTree.views;

import java.util.Scanner;

public class FamilyTreeView {
    private Scanner scanner;

    public FamilyTreeView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Меню:");
        System.out.println("1. Добавить персонажа");
        System.out.println("2. Редактировать персонажа");
        System.out.println("3. Найти персонажа по имени");
        System.out.println("4. Найти детей персонажа");
        System.out.println("5. Добавить партнера");
        System.out.println("6. Добавить ребенка");
        System.out.println("7. Сортировать по имени");
        System.out.println("8. Сортировать по дате рождения");
        System.out.println("9. Сохранить древо в файл");
        System.out.println("10. Загрузить древо из файла");
        System.out.println("0. Выход");
        System.out.print("Выберите опцию: ");
    }

    public String getInput() {
        return scanner.nextLine();
    }

    public String getInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.err.println(error);
    }
}