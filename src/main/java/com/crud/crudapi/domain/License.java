package com.crud.crudapi.domain;

public class License
{
    private Integer licenseId;
    private Integer userId;
    private String title;
    private String description;
    private Integer totalLicense;

    public License(Integer licenseId, Integer userId, String title, String description, Integer totalLicense) {
        this.licenseId = licenseId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.totalLicense = totalLicense;
    }

    public Integer getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Integer licenseId) {
        this.licenseId = licenseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalLicense() {
        return totalLicense;
    }

    public void setTotalLicense(Integer totalLicense) {
        this.totalLicense = totalLicense;
    }
}
