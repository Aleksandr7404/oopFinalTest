package view;

import java.util.List;

public interface NoteView{
void displayNotes(List<String> notes);
void showMessage(String message);
String getDateInput();
String getTimeInput();
String getNoteInput();
String getStartDateInput();
}
