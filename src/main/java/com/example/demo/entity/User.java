package com.example.demo.entity;

import com.example.demo.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity implements UserDetails {

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private Integer sex;

    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        collection.add(new SimpleGrantedAuthority("ROLE_" + role));
        return collection;
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
