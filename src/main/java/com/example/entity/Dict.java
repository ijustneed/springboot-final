package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @version 1.0
 * @author： xiaoxu
 * @date： 2022-09-26 08:09
 */

@TableName("sys_dict")
@Data
public class Dict {
    private String name;
    private  String value;
    private String type;
}
