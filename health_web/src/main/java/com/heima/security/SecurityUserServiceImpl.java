package com.heima.security;
import com.alibaba.dubbo.config.annotation.Reference;
import com.heima.pojo.Permission;
import com.heima.pojo.Role;
import com.heima.pojo.User;
import com.heima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SecurityUserServiceImpl implements UserDetailsService {
    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查询用户信息
        User user= userService.findByUsername(username);
        if (null==user){
            return null ;

        }
        Set<Role> roles = user.getRoles();
        //授权

        List<GrantedAuthority> authorityList=new ArrayList<GrantedAuthority>();
        if (null!=roles){
            //用户所拥有的权限集合

            SimpleGrantedAuthority sga=null;
            for (Role role : roles) {
                sga=new SimpleGrantedAuthority(role.getKeyword());
                authorityList.add(sga);
                Set<Permission> permissions = role.getPermissions();
                if (null!=permissions){
                    for (Permission permission : permissions) {
                        sga=new SimpleGrantedAuthority(permission.getKeyword());
                        authorityList.add(sga);
                    }

                }

            }


        }
        UserDetails userDetails=new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);

        return userDetails;
    }
}
