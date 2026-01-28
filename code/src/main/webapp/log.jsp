<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.goalarchive.util.LogFile" %>
<%@ page import="java.io.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Log Modifiche - GoalArchive</title>
    <style>
        body { font-family: monospace; margin: 20px; background: #f5f5f5; }
        h1 { color: #333; }
        #log-content { background: white; padding: 20px; border-radius: 5px; }
        .log-entry { padding: 5px; border-bottom: 1px solid #eee; }
        .INFO { color: blue; }
        .WARNING { color: orange; }
        .ERROR { color: red; }
        .DEBUG { color: green; }
    </style>
</head>
<body>
<h1>Registro Modifiche</h1>
<div id="log-content">
    <%
        // Leggi il file modifiche.txt
        String logPath = "/modifiche.txt"; // MODIFICA con lo stesso path di LogFile.java
        File logFile = new File(logPath);

        if (logFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
                String line;
                List<String> lines = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }


                int start = Math.max(0, lines.size() - 100);
                for (int i = start; i < lines.size(); i++) {
                     line = lines.get(i);
                    String cssClass = "";
                    if (line.contains("[INFO]")) cssClass = "INFO";
                    else if (line.contains("[WARNING]")) cssClass = "WARNING";
                    else if (line.contains("[ERROR]")) cssClass = "ERROR";
                    else if (line.contains("[DEBUG]")) cssClass = "DEBUG";

                    System.out.println("<div class='log-entry " + cssClass + "'>" +
                            line.replace("<", "&lt;").replace(">", "&gt;") + "</div>");
                }
            } catch (IOException e) {
                System.out.println("<p style='color:red;'>Errore nella lettura del file: " + e.getMessage() + "</p>");
            }
        } else {
            System.out.println("<p>File modifiche.txt non ancora creato. Verrà creato alla prima scrittura.</p>");
        }
    %>
</div>
</body>
</html>
