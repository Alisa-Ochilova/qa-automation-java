package API;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("countryName")
    protected   String countryName;

    @SerializedName("id")
    protected  int id;

    @SerializedName("location")
    protected  String location;


    public String getCountryName() {
        return countryName;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Country setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public Country setId(int id) {
        this.id = id;
        return this;
    }

    public Country setLocation(String location) {
        this.location = location;
        return this;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "countryName='" + countryName + '\'' +
                ", id=" + id +
                ", location='" + location + '\'' +
                '}';
    }
}
