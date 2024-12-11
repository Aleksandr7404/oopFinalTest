package model;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Notebook implements NoteModel {
    private final Map<String, List<String>> notes;

    public Notebook() {
        this.notes = new HashMap<>();
    }

    @Override
    public void addNote(String date, String time, String note) {
        notes.computeIfAbsent(date, _ -> new ArrayList<>()).add(time + " - " + note);
    }

    @Override
    public List<String> getNotes(String date) {
        return notes.getOrDefault(date, Collections.emptyList());
    }

    @Override
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                String dataTime = parts[0];
                String note = parts[1];
                String date = dataTime.split(" ")[0];
                String time = dataTime.substring(date.length() + 1);
                addNote(date, time, note);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, List<String>> entry : notes.entrySet()) {
                String date = entry.getKey();
                for (String note : entry.getValue()) {
                    writer.write(date + " " + note);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> searchNotesInWeek(String startDate) {

        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = start.plusDays(6);
        List<String> weeklyNotes = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : notes.entrySet()) {
            LocalDate noteDate = LocalDate.parse(entry.getKey(), DateTimeFormatter.ISO_LOCAL_DATE);
            if (!noteDate.isBefore(start) && !noteDate.isAfter(end)) {
                for (String note : entry.getValue()) {
                    weeklyNotes.add("%s %s".formatted(noteDate, note));
                }
            }
        }
        // Сортировка по дате и времени
        weeklyNotes.sort((note1, note2) -> {
            String[] parts1 = note1.split(" ");       // Получаем дату и время из записи
            String[] parts2 = note2.split(" ");
            if (parts1.length < 2 || parts2.length < 2) {
                return 0; // Защита от ошибок
            }
            LocalDateTime dateTime1 = LocalDateTime.parse("%s %s".formatted(parts1[0], parts1[1]), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            LocalDateTime dateTime2 = LocalDateTime.parse("%s %s".formatted(parts2[0], parts2[1]), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return dateTime1.compareTo(dateTime2);
        });
        return weeklyNotes;
    }
}