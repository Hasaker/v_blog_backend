package com.hasaker.oauth.authentication.provider;

import cn.hutool.core.util.ObjectUtil;
import com.hasaker.oauth.entity.OAuthUserDetails;
import com.hasaker.oauth.service.OAuthUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @package com.hasaker.oauth.authentication.provider
 * @author 余天堂
 * @create 2020/2/22 12:26
 * @description WebLoginAuthenticationProvider
 */
@Configuration
public class WebLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private OAuthUserDetailService OAuthUserDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object principal = authentication.getPrincipal();
        Object password = authentication.getCredentials();

        if (ObjectUtil.isEmpty(principal)) {
            throw new BadCredentialsException("The username cannot be empty");
        }
        if (ObjectUtil.isEmpty(password)) {
            throw new BadCredentialsException("The password cannot be empty");
        }

        OAuthUserDetails oAuthUserDetails = OAuthUserDetailService.loadUserByUsername(principal.toString());
        if (ObjectUtil.isNull(oAuthUserDetails)) {
            throw new BadCredentialsException("User not exists!");
        }
        if (!passwordEncoder.matches(password.toString(), oAuthUserDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password!");
        }

        return new UsernamePasswordAuthenticationToken(oAuthUserDetails, password, oAuthUserDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
