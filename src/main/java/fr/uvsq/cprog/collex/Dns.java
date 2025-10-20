package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dns {
    private final Map<AdresseIP, DnsItem> ipMap = new HashMap<>();
    private final Map<NomMachine, DnsItem> nomMap = new HashMap<>();
    private final Path fichier;

    public Dns(Path fichier) {
        this.fichier = fichier;
        charger();
    }

    private void charger() {
        try (Stream<String> lignes = Files.lines(fichier)) {
            lignes.forEach(this::traiterLigne);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void traiterLigne(String ligne) {
        String[] parties = ligne.split(" ");
        if (parties.length == 3) {
            AdresseIP ip = new AdresseIP(parties[0]);
            NomMachine nom = new NomMachine(parties[1]);
            DnsItem item = new DnsItem(ip, nom);
            ipMap.put(ip, item);
            nomMap.put(nom, item);
        }
    }
    
    public DnsItem getItem(AdresseIP ip) {
        return ipMap.get(ip);
    }

    public DnsItem getItem(NomMachine nom) {
        return nomMap.get(nom);
    }


    public List<DnsItem> getAllItems() {
        List<DnsItem> liste = new ArrayList<>();

        for (DnsItem item : nomMap.values()) {
            if (item.getNom().getDomaine().equals(domaine)) {
                liste.add(item);
            }
        }

        if (sortByIp) {
            liste.sort(Comparator.comparing(i -> i.getAdresse().getIp()));
        } 
        else {
            liste.sort(Comparator.comparing(i -> i.getNom().getNomMachine()));
        }

        return liste;
    }
    
    public void addItem(AdresseIP ip, NomMachine nom) {
        if (ipMap.containsKey(ip)) {
            throw new IllegalArgumentException("L’adresse IP existe déjà");
        }
        if (nomMap.containsKey(nom)) {
            throw new IllegalArgumentException("Le nom de machine existe déjà");
        }

        DnsItem item = new DnsItem(ip, nom);
        ipMap.put(ip, item);
        nomMap.put(nom, item);
    }

}

