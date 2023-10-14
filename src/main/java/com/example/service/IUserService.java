package com.example.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.controller.dto.UserDto;
import com.example.controller.dto.UserPasswordDTO;
import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2022-05-27
 */
public interface IUserService extends IService<User> {

    UserDto login(UserDto userDto);

    User register(UserDto userDto);

    Page<User> findPage(Page<User> page, String username, String email, String address);

    void updatePassword(UserPasswordDTO userPasswordDTO);
}
