package com.backend.controller;

import com.backend.model.dto.UserAlterDTO;
import com.backend.model.dto.UserAlterPasswordDTO;
import com.backend.model.dto.UserImportDTO;
import com.backend.model.dto.UserLoginDTO;
import com.backend.model.entity.User;
import com.backend.model.vo.UserLoginVO;
import com.backend.service.service.UserService;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    // 导入用户
    @PostMapping("/import")
    public ResponseData<Object> imPort(@RequestBody @Validated UserImportDTO userImportDTO) {
        User user = new User();
        BeanUtils.copyProperties(userImportDTO,user);
        userService.add(user);
        return ResponseData.success(null,null);
    }

    // 移除用户
    @PostMapping("/remove")
    public ResponseData<Object> remove(@RequestParam @NotEmpty(message = "用户ID不能为空") Long id){
        userService.delete(id);
        return ResponseData.success(null,null);
    }


    // 根据ID获取用户
    @GetMapping("/getById")
    public ResponseData<Object> getById(@RequestParam @NotEmpty(message = "用户ID不能为空") Long id){
        User user = userService.get(id);
        return ResponseData.success(user,null);
    }

    // 获取所有用户
    @GetMapping("/getAll")
    public ResponseData<Object> getAll(){
        List<User> users = userService.getAll(null);
        return ResponseData.success(users,null);
    }

    // 获取对应角色的所有用户
    @GetMapping("/getRoleAll")
    public ResponseData<Object> getRoleAll(@RequestParam @NotNull @Min(value = 0, message = "用户角色必须在{0,1,2,3}内")
                                               @Max(value = 3, message = "用户角色必须在{0,1,2,3}内") Integer role){
        List<User> users = userService.getAll(role);
        return ResponseData.success(users,null);
    }

    // 根据姓名前缀获取用户
    @GetMapping("/getByNamePrefix")
    public ResponseData<Object> getByNamePrefix(@RequestParam @NotEmpty(message = "姓名前缀不能为空") String namePrefix){
        List<User> users = userService.getByNamePrefix(namePrefix);
        return ResponseData.success(users,null);
    }

    // 获取当前登录用户
    @GetMapping("/getLogin")
    public ResponseData<Object> getLogin(){
        User user = userService.getLoginUser();
        return ResponseData.success(user,null);
    }

    // 登录，获取JWT-token
    @PostMapping("/login")
    public ResponseData<UserLoginVO> login(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        UserLoginVO userLoginVO = new UserLoginVO();
        String jwtToken = userService.getJwtToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        userLoginVO.setJwtToken(jwtToken);
        return ResponseData.success(userLoginVO,null);
    }

    // 修改用户密码
    @PostMapping("/alterPassword")
    public ResponseData<Object> alterPassword(@RequestBody @Validated UserAlterPasswordDTO userAlterPasswordDTO){
        User user = new User();
        BeanUtils.copyProperties(userAlterPasswordDTO,user);
        userService.alter(user);
        return ResponseData.success(null,null);
    }

    // 修改用户信息
    @PostMapping("/alter")
    public ResponseData<Object> alter(@RequestBody @Validated UserAlterDTO userAlterDTO){
        User user = new User();
        BeanUtils.copyProperties(userAlterDTO,user);
        userService.alter(user);
        return ResponseData.success(null,null);
    }
}
