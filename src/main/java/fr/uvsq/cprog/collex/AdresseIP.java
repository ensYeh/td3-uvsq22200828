package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP 
{
    private String ip;
    public AdresseIP(String ip) {
        this.ip = ip;
    }
    public String getIp() {
        return ip;
    }
    @Override
    public String toString() {
        return ip;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdresseIP)) return false;
        AdresseIP other = (AdresseIP) obj;
        return Objects.equals(ip, other.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }
}
