package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.Result;
import com.example.entity.Dict;
import com.example.mapper.DictMapper;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import com.example.service.IMenuService;
import com.example.entity.Menu;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2022-09-23
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Resource
    private DictMapper dictMapper;
@PostMapping
public Result save(@RequestBody Menu menu) {
    menuService.saveOrUpdate(menu);
        return Result.success();
        }


@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
    menuService.removeById(id);
        return Result.success();
        }
@GetMapping("/ids")
public Result findAllIds() {
    return Result.success(menuService.list().stream().map(Menu::getId));
}
@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
    menuService.removeByIds(ids);
        return Result.success();
        }

@GetMapping
public Result findAll(@RequestParam(defaultValue = "") String name) {


        return Result.success(menuService.findMenus(name));
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam String name,
        @RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }


        @GetMapping("/icons")
    public Result getIcons() {
            QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
            return Result.success(dictMapper.selectList(null));
        }



}

