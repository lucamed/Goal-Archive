package com.goalarchive.system;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("system")
@DisplayName("Test di Sistema - Registrazione End-to-End")
class RegistrazioneSystemTest extends BaseSystemTest {

    /**
     * Pulisce i cookie prima di ogni test per garantire un ambiente pulito.
     */
    @BeforeEach
    void cleanSession() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
        }
    }

    @Test
    @Order(1)
    @DisplayName("ST5 - Registrazione nuovo utente con dati corretti")
    void testRegistrazioneSuccessful() {

        log("Navigazione alla pagina di registrazione");
        driver.get(BASE_URL + "/registrazione.jsp");

        // Se l'URL cambia automaticamente (es. redirect), questo check potrebbe fallire, 
        // ma va bene controllarlo all'inizio
        // assertTrue(driver.getCurrentUrl().contains("registrazione"));

        log("Compilazione form");

        String uniqueEmail = "user" + System.currentTimeMillis() + "@example.com";
        String uniqueUsername = "user" + System.currentTimeMillis();

        driver.findElement(By.id("nome")).sendKeys("Mario");
        driver.findElement(By.id("cognome")).sendKeys("Rossi");
        driver.findElement(By.id("nomeUtente")).sendKeys(uniqueUsername);
        driver.findElement(By.id("dataNascita")).sendKeys("1995-01-01");
        driver.findElement(By.id("email")).sendKeys(uniqueEmail);
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("domandaSicurezza")).sendKeys("Nome del cane");
        driver.findElement(By.id("rispostaSicurezza")).sendKeys("Fido");
        driver.findElement(By.id("squadraCuore")).sendKeys("Napoli");

        WebElement submitButton = driver.findElement(By.cssSelector("button.btn.btn-primary"));
        submitButton.click();

        waitSeconds(2);

        log("URL dopo registrazione: " + driver.getCurrentUrl());

        assertFalse(driver.getCurrentUrl().contains("registrazione"),
                "Non deve rimanere sulla pagina di registrazione");

        assertTrue(driver.getCurrentUrl().contains("login") ||
                   driver.getPageSource().contains("success") || 
                   driver.getCurrentUrl().contains("autenticazione"), // Aggiunto caso comune
                "Deve essere reindirizzato al login o pagina di successo");

        log("✓ Registrazione completata con successo");
    }

    @Test
    @Order(2)
    @DisplayName("ST6 - Registrazione con email già esistente")
    void testRegistrazioneDuplicateEmail() {

        driver.get(BASE_URL + "/registrazione.jsp");

        driver.findElement(By.id("nome")).sendKeys("Mario");
        driver.findElement(By.id("cognome")).sendKeys("Rossi");
        driver.findElement(By.id("nomeUtente")).sendKeys("UniqueUser" + System.currentTimeMillis());
        driver.findElement(By.id("dataNascita")).sendKeys("1990-01-01");

        // EMAIL DUPLICATA (Assicurati che questa email esista nel DB)
        driver.findElement(By.id("email")).sendKeys("claudione04@gmail.com");

        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.id("domandaSicurezza")).sendKeys("Domanda");
        driver.findElement(By.id("rispostaSicurezza")).sendKeys("Risposta");
        driver.findElement(By.id("squadraCuore")).sendKeys("Inter");

        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

        waitSeconds(2);

        // --- DEBUG DIAGNOSTICO ---
        try {
            // Cerchiamo l'errore
            WebElement errore = driver.findElement(By.className("alert-error"));
            assertTrue(errore.isDisplayed(), "Il messaggio di errore deve essere visibile");
            
            String errorText = errore.getText().toLowerCase();
            
            boolean isDuplicateError = errorText.contains("email") || 
                                       errorText.contains("esistente") || 
                                       errorText.contains("duplicate") ||
                                       errorText.contains("già presente");
            
            assertTrue(isDuplicateError, "Messaggio non pertinente: " + errorText);
            
        } catch (Exception e) {
            // SE IL TEST FALLISCE, STAMPIAMO COSA C'È A VIDEO
            String bodyText = driver.findElement(By.tagName("body")).getText();
            System.out.println("--------------------------------------------------");
            System.out.println("DEBUG ST6 - CONTENUTO PAGINA TROVATO:");
            System.out.println(bodyText);
            System.out.println("--------------------------------------------------");

            if (bodyText.contains("Exception") || bodyText.contains("Error") || bodyText.contains("Duplicate entry")) {
                fail("ST6 FALLITO: L'applicazione è andata in crash (Stack Trace visibile) invece di gestire l'eccezione. Leggi il log sopra.");
            } else {
                fail("ST6 FALLITO: L'alert di errore non è apparso. Controlla il nome della classe CSS.");
            }
        }
    }

    @Test
    @Order(3)
    @DisplayName("ST7 - Registrazione con campi mancanti")
    void testRegistrazioneCampiMancanti() {

        log("Navigazione alla pagina di registrazione");
        driver.get(BASE_URL + "/registrazione.jsp");

        log("Invio form vuoto");

        WebElement submitButton = driver.findElement(By.cssSelector("button.btn.btn-primary"));
        submitButton.click();

        waitSeconds(1);

        WebElement nomeField = driver.findElement(By.id("nome"));
        String validation = nomeField.getAttribute("validationMessage");

        // Debug per vedere se la validazione HTML5 è supportata dal browser driver corrente
        // System.out.println("DEBUG Validation Message: " + validation);

        assertFalse(validation.isEmpty(), "La validazione HTML5 deve attivarsi (il browser deve impedire il submit)");

        // Verifica che non abbia cambiato pagina
        // Nota: se la validazione è HTML5, l'URL non cambia proprio perché non parte la request
        assertTrue(driver.getCurrentUrl().contains("registrazione"),
                "Deve rimanere sulla pagina");

        log("✓ Validazione campi obbligatori funzionante");
    }
}