package com.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.IFill;
import com.example.common.Constants;
import com.example.controller.dto.UserDto;
import com.example.controller.dto.UserPasswordDTO;
import com.example.entity.Menu;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.exception.ServiceException;
import com.example.mapper.RoleMapper;
import com.example.mapper.RoleMenuMapper;
import com.example.mapper.UserMapper;
import com.example.service.IMenuService;
import com.example.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.utils.TokenUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xu
 * @since 2022-05-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log LOG = Log.get();

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;
    @Resource UserMapper userMapper;

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);

    }

    @Override
    public UserDto login(UserDto userDto) {
        User one = getUserInfo(userDto);


        if (one != null) {
            BeanUtil.copyProperties(one,userDto,true);
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword().toString());
            userDto.setToken(token);

            String role = one.getRole();

            List<Menu> roleMenus = getRoleMenus(role);

            userDto.setMenus(roleMenus);



            return userDto;

        }
        else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");

        }

    }

    @Override
    public User register(UserDto userDto) {
        User one = getUserInfo(userDto);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDto,one,true);
            save(one);
        }else  {
            throw new ServiceException(Constants.CODE_600,"用户名已存在");
        }
        return one;

    }

    @Override
    public Page<User> findPage(Page<User> page, String username, String email, String address) {
       return userMapper.findPage(page,username,email,address);
    }

//    @Override
//    public Page<User> findPage(Page<User> page, String username, String email, String address) {
//        return userMapper.findPage(page, username, email, address);
//    }


    private User getUserInfo(UserDto userDto) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",userDto.getUsername());
        userQueryWrapper.eq("password",userDto.getPassword());
        User one;
        try{
            one = getOne(userQueryWrapper);
        }catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;

    }
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }
}
