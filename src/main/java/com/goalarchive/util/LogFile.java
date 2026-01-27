package com.goalarchive.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFile {


    private static final String LOG_FILE_PATH = "C:\\Users\\Utente\\Desktop\\unisa\\terzo anno\\is\\progetto\\goalarchive\\src\\main\\java\\com\\goalarchive\\logs\\modifiche.txt";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static synchronized void scriviModifica(String username, String tipoModifica,
                                                   String descrizione, String motivo) {

        System.out.println("\n========== INIZIO SCRITTURA LOG ==========");
        System.out.println("Username: " + username);
        System.out.println("Tipo: " + tipoModifica);
        System.out.println("Path: " + LOG_FILE_PATH);

        File logFile = new File(LOG_FILE_PATH);

        File parentDir = logFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            System.out.println("Cartella logs creata: " + created);
        }

        if (!logFile.exists()) {
            try {
                boolean created = logFile.createNewFile();
                System.out.println("File modifiche.txt creato: " + created);
            } catch (IOException e) {
                System.err.println("❌ ERRORE creazione file: " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            String timestamp = dateFormat.format(new Date());
            String logEntry = String.format("[%s] Utente: %s | Azione: %s | %s | Motivo: %s%n",
                    timestamp, username, tipoModifica, descrizione, motivo);
            writer.write(logEntry);
            writer.flush();
            System.out.println("✅ LOG SCRITTO CON SUCCESSO!");
            System.out.println("Contenuto: " + logEntry);
        } catch (IOException e) {
            System.err.println("❌ ERRORE scrittura: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("========== FINE SCRITTURA LOG ==========\n");
    }

    public static String leggiModifiche() {
        File logFile = new File(LOG_FILE_PATH);

        if (!logFile.exists()) {
            return "Nessuna modifica registrata.";
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Errore nella lettura del file di log.";
        }

        return sb.length() == 0 ? "Il file di log è vuoto." : sb.toString();
    }

    public static void error(String message) {
        System.err.println("[ERROR] " + message);
    }
}
