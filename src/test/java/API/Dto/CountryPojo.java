package API.Dto;

import com.google.gson.annotations.SerializedName;

//@JsonIgnorePropeties(ignoreUnknown = true)
public class CountryPojo {
    @SerializedName("id")
    public int id;

    @SerializedName("countryName")
    private String countryName;

    @SerializedName("locations")
    private String locations;

    public int getId() {
        return id;
    }

    public CountryPojo setId(int id) {
        this.id = id;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public CountryPojo setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getLocations() {
        return locations;
    }

    public CountryPojo setLocations(String locations) {
        this.locations = locations;
        return this;
    }
}
