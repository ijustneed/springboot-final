package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.example.common.Result;
import com.example.config.AuthAccess;
import com.example.entity.User;
import com.example.mapper.FileMapper;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0
 * @author： xiaoxu
 * @date： 2022-09-22 14:30
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Resource
    private FileMapper fileMapper;

    @Autowired
    private IUserService iUserService;
    @GetMapping("/example")
    public Result get(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y",CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return  Result.success(map);
    }
    @AuthAccess
    @GetMapping("file//front/all")
    public Result frontAll() {
        return Result.success(fileMapper.selectList(null));
    }
    @GetMapping("/members")
    public Result members(){
        List<User> list = iUserService.list();
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;

        for (User user: list) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                case Q1: q1 += 1; break;
                case Q2: q2 += 1; break;
                case Q3: q3 += 1; break;
                case Q4: q4 += 1; break;
                default:break;
            }




        }
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));



    }

}
