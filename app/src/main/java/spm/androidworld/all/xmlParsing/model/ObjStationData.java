package spm.androidworld.all.xmlParsing.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class ObjStationData {

    @Element(name = "StationDesc", required = true)
    private String stationDesc;
    @Element(name = "StationAlias", required = false)
    private String stationAlias;
    @Element(name = "StationLatitude", required = true)
    private Float stationLatitude;
    @Element(name = "StationLongitude", required = true)
    private Float stationLongitude;
    @Element(name = "StationCode", required = true)
    private String stationCode;
    @Element(name = "StationId", required = true)
    private String stationId;


    public ObjStationData() {

    }

    public ObjStationData(@Element(name = "StationDesc", required = true) String stationDesc, @Element(name = "StationAlias", required = false) String stationAlias,
                          @Element(name = "StationLatitude", required = true) Float stationLatitude, @Element(name = "StationLongitude", required = true) Float stationLongitude,
                          @Element(name = "StationCode", required = true) String stationCode, @Element(name = "StationId", required = true) String stationId) {
        this.stationDesc = stationDesc;
        this.stationAlias = stationAlias;
        this.stationLatitude = stationLatitude;
        this.stationLongitude = stationLongitude;
        this.stationCode = stationCode;
        this.stationId = stationId;
    }

    public String getStationDesc() {
        return stationDesc;
    }

    public void setStationDesc(String stationDesc) {
        this.stationDesc = stationDesc;
    }

    public String getStationAlias() {
        return stationAlias;
    }

    public void setStationAlias(String stationAlias) {
        this.stationAlias = stationAlias;
    }

    public Float getStationLatitude() {
        return stationLatitude;
    }

    public void setStationLatitude(Float stationLatitude) {
        this.stationLatitude = stationLatitude;
    }

    public Float getStationLongitude() {
        return stationLongitude;
    }

    public void setStationLongitude(Float stationLongitude) {
        this.stationLongitude = stationLongitude;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }
}