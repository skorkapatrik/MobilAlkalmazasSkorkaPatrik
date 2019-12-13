package hu.patrik.mobilalkalmazas.feszekterkep;

public class Feszek {

    private Double latitude;
    private Double longitude;
    private String azonosito;
    private String faj;

    public Feszek(Double latitude, Double longitude, String azonosito) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.azonosito = azonosito;
    }

    public Feszek(Double latitude, Double longitude, String azonosito, String faj) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.azonosito = azonosito;
        this.faj = faj;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAzonosito() {
        return azonosito;
    }

    public void setAzonosito(String azonosito) {
        this.azonosito = azonosito;
    }

    public String getFaj() {
        return faj;
    }

    public void setFaj(String faj) {
        this.faj = faj;
    }
}
