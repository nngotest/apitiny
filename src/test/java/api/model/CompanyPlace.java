package api.model;

public class CompanyPlace {
    private String id;

    private String address;

    private String name;

    private long longitude;

    private long latitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "ComplanyPlace [id = " + id + ", address = " + address + ", name = " + name + ", longitude = " + longitude + ", latitude = " + latitude + "]";
    }
}