package daniel.english.ithinkihavecovid;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Address implements Serializable {
    private String label;
    private String countryCode;
    private String countryName;
    private String stateCode;
    private String state;
    private String county;
    private String city;
    private String street;
    private String postalCode;
    private String houseNumber;

    protected Address(Parcel in) {
        label = in.readString();
        countryCode = in.readString();
        countryName = in.readString();
        stateCode = in.readString();
        state = in.readString();
        county = in.readString();
        city = in.readString();
        street = in.readString();
        postalCode = in.readString();
        houseNumber = in.readString();
    }
    

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Address(String label,
                   String countryCode,
                   String countryName,
                   String stateCode,
                   String state,
                   String county,
                   String city,
                   String street,
                   String postalCode,
                   String houseNumber) {
        this.label = label;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.stateCode = stateCode;
        this.state = state;
        this.county = county;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "label='" + label + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", state='" + state + '\'' +
                ", county='" + county + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }
}
