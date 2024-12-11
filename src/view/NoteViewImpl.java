package view;

import java.util.List;
import java.util.Scanner;

public class NoteViewImpl implements NoteView {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayNotes(List<String> notes) {
        if (notes.isEmpty()) {
            System.out.println("Записей не найдено.");
        } else {
            System.out.println("Найдены записи:");
            notes.forEach(System.out::println);
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getDateInput() {
        System.out.print("Введите дату (YYYY-MM-DD): ");
        return scanner.nextLine();
    }

    @Override
    public String getTimeInput() {
        System.out.print("Введите время (HH:mm): ");
        return scanner.nextLine();
    }

    @Override
    public String getNoteInput() {
        System.out.print("Введите запись: ");
        return scanner.nextLine();
    }

    @Override
    public String getStartDateInput() {
        System.out.print("Введите дату начала недели (YYYY-MM-DD): ");
        return scanner.nextLine();
    }

}
