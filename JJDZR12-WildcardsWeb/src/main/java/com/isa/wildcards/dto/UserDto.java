package com.isa.wildcards.dto;

import com.isa.wildcards.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {

    private String userName;

    private String password;

    private UUID uuid;

    private UserRole userRole;

}
