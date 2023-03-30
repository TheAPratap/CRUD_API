package com.crud.crudapi.resources;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/licenses")
public class LicenseResource
{
    @GetMapping("")
    public String getAllLicenses(HttpServletRequest request)
    {
        int userId = (Integer) request.getAttribute("userId");
        return "Authenticated! User ID: " + userId;
    }
}
