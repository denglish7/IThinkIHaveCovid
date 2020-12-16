package daniel.english.ithinkihavecovid;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TestingLocation implements Serializable {

    private String title;
    private String id;
    private String resultType;
    private Address address;
    private Position position;
    private Position access;
    private Integer distance;
    private String phone;
    private String website;

    public TestingLocation(String title,
                    String id,
                    String resultType,
                    Address address,
                    Position position,
                    Position access,
                    Integer distance,
                    String phone,
                    String website) {
        this.title = title;
        this.id = id;
        this.resultType = resultType;
        this.address = address;
        this.position = position;
        this.access = access;
        this.distance = distance;
        this.phone = phone;
        this.website = website;
    }

    protected TestingLocation(Parcel in) {
        title = in.readString();
        id = in.readString();
        resultType = in.readString();
        if (in.readByte() == 0) {
            distance = null;
        } else {
            distance = in.readInt();
        }
        phone = in.readString();
        website = in.readString();
    }

//    public static final Creator<TestingLocation> CREATOR = new Creator<TestingLocation>() {
//        @Override
//        public TestingLocation createFromParcel(Parcel in) {
//            return new TestingLocation(in);
//        }
//
//        @Override
//        public TestingLocation[] newArray(int size) {
//            return new TestingLocation[size];
//        }
//    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getAccess() {
        return access;
    }

    public void setAccess(Position access) {
        this.access = access;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "TestingLocation{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", resultType='" + resultType + '\'' +
                ", address=" + address +
                ", position=" + position +
                ", access=" + access +
                ", distance=" + distance +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeString(title);
//        parcel.writeString(id);
//        parcel.writeString(resultType);
//        if (distance == null) {
//            parcel.writeByte((byte) 0);
//        } else {
//            parcel.writeByte((byte) 1);
//            parcel.writeInt(distance);
//        }
//        parcel.writeString(phone);
//        parcel.writeString(website);
//    }
}
