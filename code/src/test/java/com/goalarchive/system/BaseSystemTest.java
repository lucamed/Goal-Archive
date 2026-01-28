package com.goalarchive.system;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Tag("system")
public abstract class BaseSystemTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;

    // URL base della tua applicazione
    protected static final String BASE_URL = "http://localhost:8080/GoalArchive";
    protected static final int TIMEOUT_SECONDS = 10;

    @BeforeAll
    static void setupDriver() {
        System.out.println("=== Inizializzazione WebDriver (Firefox) ===");

        // Setup automatico FirefoxDriver
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();

        // 🔥 Modalità grafica (browser visibile)
        // NON usare headless
        // options.addArguments("--headless");

        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        // Avvia Firefox
        driver = new FirefoxDriver(options);

        // Timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_SECONDS));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS));

        System.out.println("✓ Firefox WebDriver inizializzato");
    }

    @AfterAll
    static void tearDownDriver() {
        System.out.println("=== Chiusura WebDriver ===");
        if (driver != null) {
            driver.quit();
            System.out.println("✓ WebDriver chiuso");
        }
    }

    @BeforeEach
    void setupTest() {
        System.out.println("\n--- Inizio Test ---");
    }

    @AfterEach
    void cleanupTest() {
        System.out.println("--- Fine Test ---\n");

        // Torna alla home tra un test e l'altro
        if (driver != null) {
            driver.get(BASE_URL);
        }
    }

    // Attesa manuale (debug)
    protected void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Log leggibile
    protected void log(String message) {
        System.out.println("[TEST] " + message);
    }
}
