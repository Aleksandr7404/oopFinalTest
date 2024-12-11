import model.NoteModel;
import model.Notebook;
import presenter.NotePresenter;
import presenter.NotePresenterImpl;
import view.NoteView;
import view.NoteViewImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NoteModel model = new Notebook();
        NoteView view = new NoteViewImpl();
        NotePresenter presenter = new NotePresenterImpl(model, view);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n Меню:");
            System.out.println("1. Добавить запись");
            System.out.println("2. Поиск записи по дате");
            System.out.println("3. Показать записи за неделю");
            System.out.println("4. Сохранить в файл");
            System.out.println("5. Загрузить из файла");
            System.out.println("6. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    presenter.addNote();
                    break;
                case 2:
                    presenter.showNotes();
                    break;
                case 3:
                    presenter.showWeeklyNotes();
                    break;
                case 4:
                    System.out.print("Введите имя файла для сохранения: ");
                    String saveFile = scanner.nextLine();
                    model.saveToFile(saveFile);
                    break;
                case 5:
                    System.out.print("Введите имя файла для загрузки: ");
                    String loadFile = scanner.nextLine();
                    model.loadFromFile(loadFile);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
