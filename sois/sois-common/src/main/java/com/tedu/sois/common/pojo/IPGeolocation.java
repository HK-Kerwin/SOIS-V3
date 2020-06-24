package com.tedu.sois.common.pojo;

import java.io.Serializable;

public class IPGeolocation implements Serializable {

    /**查询IP*/
    private String query;
    /**状态 success fail*/
    private String status;
    /**失败消息*/
    private String message;
    /**国家*/
    private String country;
    /**国家代号*/
    private String countryCode;
    /**省代号*/
    private String region;
    /**省*/
    private String regionName;
    /**城市*/
    private String city;
    /** 不知道啥*/
    private String zip ;
    /**纬度*/
    private String lat;
    /** 经度*/
    private String lon;
    /**时间格式*/
    private String timezone;
    /**运营商*/
    private String isp;
    private String org;
    private String as;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IPGeolocation that = (IPGeolocation) o;

        if (query != null ? !query.equals(that.query) : that.query != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (countryCode != null ? !countryCode.equals(that.countryCode) : that.countryCode != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (regionName != null ? !regionName.equals(that.regionName) : that.regionName != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (zip != null ? !zip.equals(that.zip) : that.zip != null) return false;
        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        if (lon != null ? !lon.equals(that.lon) : that.lon != null) return false;
        if (timezone != null ? !timezone.equals(that.timezone) : that.timezone != null) return false;
        if (isp != null ? !isp.equals(that.isp) : that.isp != null) return false;
        if (org != null ? !org.equals(that.org) : that.org != null) return false;
        return as != null ? as.equals(that.as) : that.as == null;
    }

    @Override
    public int hashCode() {
        int result = query != null ? query.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (lat != null ? lat.hashCode() : 0);
        result = 31 * result + (lon != null ? lon.hashCode() : 0);
        result = 31 * result + (timezone != null ? timezone.hashCode() : 0);
        result = 31 * result + (isp != null ? isp.hashCode() : 0);
        result = 31 * result + (org != null ? org.hashCode() : 0);
        result = 31 * result + (as != null ? as.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IPGeolocation{" +
                "query='" + query + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", region='" + region + '\'' +
                ", regionName='" + regionName + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", timezone='" + timezone + '\'' +
                ", isp='" + isp + '\'' +
                ", org='" + org + '\'' +
                ", as='" + as + '\'' +
                '}';
    }
}
