package model;

public class Klant {

    // attributen

    private int klantNummer;
    private String klantNaam;
    private String locatie;

    // constructors

    public Klant(int klantNummer, String klantNaam, String locatie) {
        this.klantNummer = klantNummer;
        this.klantNaam = klantNaam;
        this.locatie = locatie;
    }

    // methoden

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Klantnummer: %d%n", klantNummer));
        stringBuilder.append(String.format("Achternaam: %s%n", klantNaam));
        stringBuilder.append(String.format("Locatie: %s%n", locatie));
        return stringBuilder.toString();
    }


    // getters en setters


    public int getKlantNummer() {
        return klantNummer;
    }

    public String getKlantNaam() {
        return klantNaam;
    }

    public String getLocatie() {
        return locatie;
    }
}
