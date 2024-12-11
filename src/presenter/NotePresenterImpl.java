package presenter;

import model.NoteModel;
import view.NoteView;

import java.util.List;

public class NotePresenterImpl implements NotePresenter {
    private final NoteModel model;
    private final NoteView view;

    public NotePresenterImpl(NoteModel model, NoteView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void addNote() {
        String date = view.getDateInput();
        String time = view.getTimeInput();
        String note = view.getNoteInput();
        model.addNote(date, time, note);
        view.showMessage("Запись добавлена.");
    }

    @Override
    public void showNotes() {
        String date = view.getDateInput();
        List<String> notes = model.getNotes(date);
        view.displayNotes(notes);

    }

    public void showWeeklyNotes() {
        String startDate = view.getStartDateInput();
        List<String> weeklyNotes = model.searchNotesInWeek(startDate);
        view.displayNotes(weeklyNotes);
    }
}
