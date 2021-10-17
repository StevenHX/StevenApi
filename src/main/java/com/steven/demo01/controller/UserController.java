package com.steven.demo01.controller;

import com.steven.demo01.bean.User;
import com.steven.demo01.service.UserService;
import com.steven.demo01.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
    @Autowired
    private UserService userService;

    /**
     * 处理 /user/ GET请求
     * @return
     */
    @GetMapping("/")
    public List<User> getUserList() {
//        String sql = "SELECT * FROM employees.employees where gender = 'M' and birth_date > '1965-01-31'";
//        List<User> data = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
//        for (User user: data) {
//            System.out.println(user.toString());
//        }
//        return  data;
        System.out.println(userService.getAllUsers());
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public String postUser(@RequestBody  User user) {
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success delete";
    }
}
