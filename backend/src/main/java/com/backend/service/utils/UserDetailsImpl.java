package com.backend.service.utils;

import com.backend.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {

    private User user;

    //根据账号身份返回授权
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        if (user.getRole().equals(0)) {
            authoritys.add(new SimpleGrantedAuthority("admin"));
        } else if (user.getRole().equals(1)) {
            authoritys.add(new SimpleGrantedAuthority("student"));
        } else if (user.getRole().equals(2)) {
            authoritys.add(new SimpleGrantedAuthority("teacher"));
        } else if (user.getRole().equals(3)) {
            authoritys.add(new SimpleGrantedAuthority("lab_admins"));
        }
        return authoritys;
    }


    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
