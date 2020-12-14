package daniel.english.ithinkihavecovid;

public class Position {
    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Position(double aLatitude, double aLongitude) {
        latitude = aLatitude;
        longitude = aLongitude;
    }
}
