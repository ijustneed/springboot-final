package com.example.controller.dto;

import lombok.Data;

/**
 * @version 1.0
 * @author： xiaoxu
 * @date： 2022-12-06 15:37
 */

@Data
public class UserPasswordDTO {
    private String username;
    private String password;
    private String newPassword;
}
