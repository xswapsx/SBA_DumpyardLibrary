package com.appynitty.swachbharatabhiyanlibrary.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalityResultPojo {
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("continent")
    @Expose
    private String continent;
    @SerializedName("lookupSource")
    @Expose
    private String lookupSource;
    @SerializedName("continentCode")
    @Expose
    private String continentCode;
    @SerializedName("localityLanguageRequested")
    @Expose
    private String localityLanguageRequested;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("postcode")
    @Expose
    private String postcode;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("principalSubdivision")
    @Expose
    private String principalSubdivision;
    @SerializedName("principalSubdivisionCode")
    @Expose
    private String principalSubdivisionCode;
    @SerializedName("plusCode")
    @Expose
    private String plusCode;
    @SerializedName("locality")
    @Expose
    private String locality;

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
}
