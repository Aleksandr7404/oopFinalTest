package model;

import java.util.List;

public interface NoteModel {
    void addNote(String date, String time, String note);
    List<String> getNotes(String date);
    void loadFromFile(String filename);
    void saveToFile(String filename);
    List<String> searchNotesInWeek(String startDate);
 }
