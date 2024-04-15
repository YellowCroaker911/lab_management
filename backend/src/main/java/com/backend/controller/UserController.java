package com.backend.controller;

import com.backend.model.dto.UserImportDTO;
import com.backend.model.dto.UserLoginDTO;
import com.backend.model.entity.User;
import com.backend.model.vo.UserLoginVO;
import com.backend.service.service.UserService;
import com.backend.utils.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @PostMapping("/import")
    public ResponseData<Object> imPort(@RequestBody @Validated UserImportDTO userImportDTO) {
        userService.imPort(userImportDTO.getUsername(),userImportDTO.getPassword(),parseInt(userImportDTO.getRole()));
        return ResponseData.success(null,null);
    }

    @PostMapping("/login")
    public ResponseData<UserLoginVO> login(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        UserLoginVO userLoginVO = new UserLoginVO();
        String jwtToken = userService.getJwtToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        userLoginVO.setJwtToken(jwtToken);
        return ResponseData.success(userLoginVO,null);
    }

    @GetMapping("/loginUser")
    public ResponseData<User> loginUser(){
        return ResponseData.success(userService.getLoginUser(),null);
    }
}
