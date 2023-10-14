package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.Result;
import com.example.entity.Files;
import com.example.entity.User;
import com.example.mapper.CourseMapper;
import com.example.service.IUserService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.example.service.ICourseService;
import com.example.entity.Course;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xu
 * @since 2022-12-05
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Resource
    private ICourseService courseService;

    @Resource
    private IUserService userService;
    @Resource
    private CourseMapper  courseMapper;
@PostMapping
public Result save(@RequestBody Course course) {
    courseService.saveOrUpdate(course);
        return Result.success();
        }

    @PostMapping("/studentCourse/{courseId}/{studentId}")
    public Result studentCourse(@PathVariable Integer courseId, @PathVariable Integer studentId) {
        courseService.setStudentCourse(courseId, studentId);
        return Result.success();
    }

@DeleteMapping("/{id}")
public Result delete(@PathVariable Integer id) {
    courseService.removeById(id);
        return Result.success();
        }

@PostMapping("/del/batch")
public Result deleteBatch(@RequestBody List<Integer> ids) {
    courseService.removeByIds(ids);
        return Result.success();
        }
    @PostMapping("/update")
    public Result update(@RequestBody Course course) {
        return Result.success(courseMapper.updateById(course));
    }

@GetMapping
public Result findAll() {
        return Result.success(courseService.list());
        }

@GetMapping("/{id}")
public Result findOne(@PathVariable Integer id) {
        return Result.success(courseService.getById(id));
        }

@GetMapping("/page")
public Result findPage(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam String name) {
//        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//       Page<Course> page =  courseService.page(new Page<>(pageNum,pageSize),queryWrapper);
//       List<Course> records = page.getRecords();
//       for (Course record : records) {
//           User user = userService.getById(record.getTeacherId());
//           if (user != null) {
//               record.setTeacher(user.getNickname());
//           }
//       }
        Page<Course> page = courseService.findPage(new Page<>(pageNum,pageSize),name);
        return Result.success(page);
        }



}

