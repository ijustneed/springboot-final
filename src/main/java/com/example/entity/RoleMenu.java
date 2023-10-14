package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @version 1.0
 * @author： xiaoxu
 * @date： 2022-09-26 08:58
 */

@Data
@TableName("sys_role_menu")
public class RoleMenu {
    private Integer roleId;
    private Integer menuId;
}
