package com.crud.crudapi.services;


import com.crud.crudapi.domain.License;
import java.util.List;

public interface LicenseService
{
    List<License> fetchAllLicenses(Integer userId);

//    License fetchLicenseById(Integer usedId, Integer licenseId) throws CrudResourceNotFoundException;
//
//    License addLicense(Integer userId, String title, String description) throws CrudBadRequestException;
//
//    void updateLicense(Integer userId, Integer licenseId, License license) throws CrudBadRequestException;
}
