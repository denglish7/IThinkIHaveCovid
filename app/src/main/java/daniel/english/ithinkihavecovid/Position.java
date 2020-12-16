package daniel.english.ithinkihavecovid;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Position implements Serializable {
    private double latitude;
    private double longitude;

    protected Position(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

//    public static final Creator<Position> CREATOR = new Creator<Position>() {
//        @Override
//        public Position createFromParcel(Parcel in) {
//            return new Position(in);
//        }
//
//        @Override
//        public Position[] newArray(int size) {
//            return new Position[size];
//        }
//    };

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

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeDouble(latitude);
//        parcel.writeDouble(longitude);
//    }
}
