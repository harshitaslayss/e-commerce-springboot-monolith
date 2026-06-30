package com.app.ecom_app.DTO;

import com.app.ecom_app.Entity.UserRole;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private AddressDTO address;
}
