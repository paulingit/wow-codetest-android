package au.com.wow.codetestapp.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @FileName FuelStationItem.java
 * @Purpose POJO holding the fuel station details
 * @RevisionHistory Created
 */
public class FuelStationItem implements Parcelable , Serializable{

    @SerializedName("regular")
    private String regular;

    @SerializedName("plus")
    private String plus;

    @SerializedName("premium")
    private String premium;

    @SerializedName("diesel")
    private String diesel;

    @SerializedName("brand")
    private String brand;

    @SerializedName("img")
    private String img;

    @SerializedName("address")
    private String address;

    @SerializedName("pupdate")
    private String pupdate;

    @SerializedName("distance")
    private String distance;


    public FuelStationItem(){
        //do not remove this constructor since it is required for serialization
    }

    public FuelStationItem(Parcel parcel){
        this.regular = parcel.readString();
        this.plus = parcel.readString();
        this.premium = parcel.readString();
        this.diesel = parcel.readString();
        this.brand = parcel.readString();
        this.img = parcel.readString();
        this.address = parcel.readString();
        this.pupdate = parcel.readString();
        this.distance = parcel.readString();
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public String getPlus() {
        return plus;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getDiesel() {
        return diesel;
    }

    public void setDiesel(String diesel) {
        this.diesel = diesel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPupdate() {
        return pupdate;
    }

    public void setPupdate(String pupdate) {
        this.pupdate = pupdate;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.regular);
        parcel.writeString(this.plus);
        parcel.writeString(this.premium);
        parcel.writeString(this.diesel);
        parcel.writeString(this.brand);
        parcel.writeString(this.img);
        parcel.writeString(this.address);
        parcel.writeString(this.pupdate);
        parcel.writeString(this.distance);
    }

    public static final Creator<FuelStationItem> CREATOR = new Creator<FuelStationItem>() {

        public FuelStationItem createFromParcel(Parcel source) {
            return new FuelStationItem(source);
        }

        @Override
        public FuelStationItem[] newArray(int i) {
            return new FuelStationItem[0];
        }
    };
}
