package vuonghieu.project.config.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import vuonghieu.project.entity.Login;
import vuonghieu.project.service.impl.LoginServiceImpl;
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    LoginServiceImpl loginService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Login login = loginService.findByEmail(s);
        if(login==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(login);
    }
}
