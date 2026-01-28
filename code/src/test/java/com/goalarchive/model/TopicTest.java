package com.goalarchive.model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Topic
 */
class TopicTest {

    @Test
    void testCostruttoreVuoto() {
        Topic topic = new Topic();
        assertNotNull(topic);
    }

    @Test
    void testSettersGetters() {
        Topic topic = new Topic();
        Date data = new Date();

        topic.setIdTopic(1);
        topic.setTitolo("Discussione Juventus");
        topic.setDescrizione("Parliamo della partita");
        topic.setDataCreazione(data);
        topic.setNumeroCommenti(42);

        assertEquals(1, topic.getIdTopic());
        assertEquals("Discussione Juventus", topic.getTitolo());
        assertEquals("Parliamo della partita", topic.getDescrizione());
        assertEquals(data, topic.getDataCreazione());
        assertEquals(42, topic.getNumeroCommenti());
    }

    @Test
    void testNumeroCommentiZero() {
        Topic topic = new Topic();
        topic.setNumeroCommenti(0);
        assertEquals(0, topic.getNumeroCommenti());
    }

    @Test
    void testDescrizioneVuota() {
        Topic topic = new Topic();
        topic.setDescrizione("");
        assertEquals("", topic.getDescrizione());
    }

    @Test
    void testDataNull() {
        Topic topic = new Topic();
        topic.setDataCreazione(null);
        assertNull(topic.getDataCreazione());
    }
}
