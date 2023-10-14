package com.example.service;

import com.example.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xu
 * @since 2022-09-23
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
