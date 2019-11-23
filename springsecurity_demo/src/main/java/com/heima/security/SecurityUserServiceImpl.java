package com.heima.security;

import com.heima.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

public class SecurityUserServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByUsername(username);
        List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();

        SimpleGrantedAuthority sga=new SimpleGrantedAuthority("ROLE_ADMIN");
        authorities.add(sga);


        UserDetails userDetails=new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
        return userDetails;
    }

    private User findByUsername(String username){
        if("admin".equals(username)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword("$2a$10$51LKsQVAWBKfkJRdBAtmi.v.4RZH5TxdAQWKz2bULtSUCzMn8XOkC");
            return user;
        }
        return null;
    }
}
