package au.com.wow.codetestapp.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @FileName FuelStationResponse.java
 * @Purpose Response POJO
 * @RevisionHistory Created
 */
public class FuelStationResponse implements Serializable {

    @SerializedName("zip")
    private String zip;

    @SerializedName("item")
    private List<FuelStationItem> item;

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<FuelStationItem> getItem() {
        return item;
    }

    public void setItem(List<FuelStationItem> item) {
        this.item = item;
    }
}
