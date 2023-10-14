package com.example.controller.dto;

import com.example.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @author： xiaoxu
 * @date： 2022-05-27 15:40
 */

@Data
public class UserDto {

//接收前端登录请求
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
    private String role;
    private List<Menu> menus;

}
