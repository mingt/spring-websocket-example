
package com.neoframework.microservices.wsteaching.utils;

import com.neoframework.common.auth.model.CustomUserDetails;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.security.Principal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

public class UserUtils {

    /**
     * 转换 Principal 为具体的 CustomUserDetails . 如果需要 User，使用下面的 getCurrentSysUser .
     *
     * @param user Principal
     * @return 如果是本系统的 CustomUserDetails 类型，则返回，否则为 null
     */
    public static CustomUserDetails getCurrentCustomUserDetails(Principal user) {
        if (user instanceof OAuth2Authentication) {
            user = ((OAuth2Authentication) user).getUserAuthentication();
        }
        if (user instanceof UsernamePasswordAuthenticationToken) {
            Object userDetails = ((UsernamePasswordAuthenticationToken) user).getPrincipal();
            if (userDetails instanceof CustomUserDetails) {
                return (CustomUserDetails) userDetails;
            } else {
                // TODO: 可以更好处理
            }
        }
        return null;
    }

    /**
     * 转换 Principal 为具体的 CustomUserDetails --> User .
     *
     * @param user Principal
     * @return 如果是本系统的 CustomUserDetails 类型，则返回，否则为 null
     */
    public static User getCurrentSysUser(Principal user) {
        CustomUserDetails userDetails = getCurrentCustomUserDetails(user);
        if (userDetails != null) {
            return userDetails.getUser();
        }
        return null;
    }

}
