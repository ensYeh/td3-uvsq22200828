package fr.uvsq.cprog.collex;

/**
 * Hello world!
 *
 */
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
}
