package fr.uvsq.dns;
import fr.uvsq.dns.commandes.*;
import org.junit.Before;
import org.junit.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class DnsTest {

    private Dns dns;

    @Before
    public void setUp() throws Exception {
        // Crée un fichier temporaire pour les tests
        Path tempFile = Files.createTempFile("dns_test", ".txt");
        Files.write(tempFile, List.of(
                "www.uvsq.fr 193.51.31.90",
                "poste.uvsq.fr 193.51.31.154",
                "ecampus.uvsq.fr 193.51.25.12"
        ));
        dns = new Dns(tempFile);
    }

    @Test
    public void testGetItemByNom() {
        DnsItem item = dns.getItem(new NomMachine("www.uvsq.fr"));
        assertNotNull(item);
        assertEquals("193.51.31.90", item.getAdresse().getIp());
    }

    @Test
    public void testGetItemByIP() {
        DnsItem item = dns.getItem(new AdresseIP("193.51.31.154"));
        assertNotNull(item);
        assertEquals("poste.uvsq.fr", item.getNom().toString());
    }

    @Test
    public void testGetItemsByDomaine() {
        List<DnsItem> items = dns.getItems("uvsq.fr", false);
        assertEquals(3, items.size());
        assertEquals("ecampus.uvsq.fr", items.get(0).getNom().toString()); // tri par nom
    }

    @Test
    public void testGetItemsByDomaineSortByIP() {
        List<DnsItem> items = dns.getItems("uvsq.fr", true);
        assertEquals(3, items.size());
        assertEquals("193.51.25.12", items.get(0).getAdresse().getIp()); // tri par IP
    }

    @Test
    public void testAddItemSuccess() {
        dns.addItem(new AdresseIP("193.51.25.24"), new NomMachine("pikachu.uvsq.fr"));
        DnsItem item = dns.getItem(new NomMachine("pikachu.uvsq.fr"));
        assertNotNull(item);
        assertEquals("193.51.25.24", item.getAdresse().getIp());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemDuplicateNom() {
        dns.addItem(new AdresseIP("193.51.25.90"), new NomMachine("www.uvsq.fr"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemDuplicateIP() {
        dns.addItem(new AdresseIP("193.51.31.90"), new NomMachine("nouveau.uvsq.fr"));
    }

    @Test
    public void testCommandeIP() {
        Commande cmd = new CommandeIP(dns, new NomMachine("www.uvsq.fr"));
        String res = cmd.execute();
        assertEquals("193.51.31.90", res);
    }

    @Test
    public void testCommandeNom() {
        Commande cmd = new CommandeNom(dns, new AdresseIP("193.51.31.154"));
        String res = cmd.execute();
        assertEquals("poste.uvsq.fr", res);
    }

    @Test
    public void testCommandeListe() {
        Commande cmd = new CommandeListe(dns, "uvsq.fr", false);
        String res = cmd.execute();
        assertTrue(res.contains("www.uvsq.fr"));
        assertTrue(res.contains("poste.uvsq.fr"));
        assertTrue(res.contains("ecampus.uvsq.fr"));
    }

    @Test
    public void testCommandeAdd() {
        Commande cmd = new CommandeAdd(dns, new AdresseIP("193.51.25.24"), new NomMachine("pikachu.uvsq.fr"));
        String res = cmd.execute();
        assertEquals("Ajout réussi.", res);

        // Vérifie que la machine a bien été ajoutée
        DnsItem item = dns.getItem(new NomMachine("pikachu.uvsq.fr"));
        assertNotNull(item);
        assertEquals("193.51.25.24", item.getAdresse().getIp());
    }
}