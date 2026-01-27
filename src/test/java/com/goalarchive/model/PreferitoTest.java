package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Preferito
 */
class PreferitoTest {

    @Test
    void testCostruttoreVuoto() {
        Preferito preferito = new Preferito();
        assertNotNull(preferito);
    }

    @Test
    void testSettersGetters() {
        Preferito preferito = new Preferito();
        Date data = new Date();

        preferito.setIdPreferito(1);
        preferito.setEmail("user@test.com");
        preferito.setTipo("club");
        preferito.setIdRiferimento(10);
        preferito.setDataAggiunta(data);
        preferito.setNome("Juventus");
        preferito.setDescrizione("Club italiano");

        assertEquals(1, preferito.getIdPreferito());
        assertEquals("user@test.com", preferito.getEmail());
        assertEquals("club", preferito.getTipo());
        assertEquals(10, preferito.getIdRiferimento());
        assertEquals(data, preferito.getDataAggiunta());
        assertEquals("Juventus", preferito.getNome());
        assertEquals("Club italiano", preferito.getDescrizione());
    }

    @Test
    void testTipoCalciatore() {
        Preferito preferito = new Preferito();
        preferito.setTipo("calciatore");
        assertEquals("calciatore", preferito.getTipo());
    }

    @Test
    void testDescrizioneNull() {
        Preferito preferito = new Preferito();
        preferito.setDescrizione(null);
        assertNull(preferito.getDescrizione());
    }

    @Test
    void testIdRiferimentoZero() {
        Preferito preferito = new Preferito();
        preferito.setIdRiferimento(0);
        assertEquals(0, preferito.getIdRiferimento());
    }
}
