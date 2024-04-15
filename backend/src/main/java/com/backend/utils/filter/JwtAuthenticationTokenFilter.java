package com.backend.utils.filter;

import com.backend.service.mapper.UserMapper;
import com.backend.model.entity.User;
import com.backend.service.utils.UserDetailsImpl;
import com.backend.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;

// https://blog.csdn.net/m0_37731470/article/details/116754395

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
            // 这里会403，doFilter会根据security config过滤一次，这里会过滤掉不是访问公共URL的
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7);

        String userid;
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            resolver.resolveException(request, response, null, new SignatureException());   //todo：测试修改是否正确
            return;
        }
        userid = claims.getSubject();

        User user = userMapper.selectById(Integer.parseInt(userid));

        if (user == null) {
            resolver.resolveException(request, response, null, new ArithmeticException());  //todo：测试修改是否正确
            return;
        }

        UserDetailsImpl loginUser = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // todo:再试试在security config里面设置权限。
        filterChain.doFilter(request, response);
    }
}