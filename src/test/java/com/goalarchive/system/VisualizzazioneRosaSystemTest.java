package com.goalarchive.system;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("system")
@DisplayName("Test di Sistema - Visualizzazione Rose End-to-End")
class VisualizzazioneRosaSystemTest extends BaseSystemTest {

    @Test
    @Order(1)
    @DisplayName("ST9 - Visualizzazione nazioni")
    void testVisualizzazioneNazioni() {

        driver.get(BASE_URL + "/club");

        waitSeconds(1);

        List<WebElement> nazioni = driver.findElements(By.cssSelector(".nazione-card"));
        assertTrue(nazioni.size() > 0, "Deve esserci almeno una nazione");
    }

    @Test
    @Order(2)
    @DisplayName("ST10 - Visualizzazione lista club per nazione")
    void testVisualizzazioneClubs() {

        driver.get(BASE_URL + "/club");
        waitSeconds(1);

        driver.findElement(By.cssSelector(".nazione-card")).click();
        waitSeconds(2);

        // I club NON hanno classe .club-card nella tua JSP
        // quindi cerchiamo i link ai club
        List<WebElement> clubs = driver.findElements(By.cssSelector("a[href*='dettaglioClub']"));
        assertTrue(clubs.size() > 0, "Deve esserci almeno un club");
    }

    @Test
    @Order(3)
    @DisplayName("ST11 - Apertura dettaglio club")
    void testDettaglioClub() {

        driver.get(BASE_URL + "/club");
        waitSeconds(1);

        driver.findElement(By.cssSelector(".nazione-card")).click();
        waitSeconds(2);

        driver.findElement(By.cssSelector("a[href*='dettaglioClub']")).click();
        waitSeconds(2);

        String page = driver.getPageSource();
        assertTrue(page.contains("Info") || page.contains("Archivio Rose"),
                "La pagina deve contenere le sezioni del club");
    }

    @Test
    @Order(4)
    @DisplayName("ST12 - Visualizzazione archivio rose e selezione stagione")
    void testArchivioRose() {

        driver.get(BASE_URL + "/club");
        waitSeconds(1);

        driver.findElement(By.cssSelector(".nazione-card")).click();
        waitSeconds(2);

        driver.findElement(By.cssSelector("a[href*='dettaglioClub']")).click();
        waitSeconds(2);

        driver.findElement(By.linkText("Archivio Rose")).click();
        waitSeconds(2);

        // Se non ci sono stagioni, la pagina mostra un messaggio
        List<WebElement> noData = driver.findElements(By.cssSelector(".no-data"));
        if (!noData.isEmpty()) {
            assertTrue(noData.get(0).isDisplayed());
            return;
        }

        WebElement selectElement = driver.findElement(By.id("stagione"));
        Select stagioneSelect = new Select(selectElement);

        assertTrue(stagioneSelect.getOptions().size() > 0,
                "Deve esserci almeno una stagione disponibile");

        if (stagioneSelect.getOptions().size() > 1) {
            stagioneSelect.selectByIndex(1);
            waitSeconds(2);
        }

        List<WebElement> giocatori = driver.findElements(By.cssSelector(".giocatore-card"));
        assertTrue(giocatori.size() > 0, "Deve esserci almeno un giocatore nella rosa");

        List<WebElement> ruoli = driver.findElements(By.cssSelector(".ruolo-section"));
        assertTrue(ruoli.size() > 0, "Deve esserci almeno una sezione ruolo");
    }
}
