package com.lot.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
			Authentication authentication) throws IOException, ServletException {

		boolean hasUserRole = false;
		boolean hasAdminRole = false;

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("USER")) {
				hasUserRole = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("ADMIN")) {
				hasAdminRole = true;
				break;
			}
		}

		if (hasUserRole) {
			redirectStrategy.sendRedirect(arg0, arg1, "/lot/home");
		} else if (hasAdminRole) {
			redirectStrategy.sendRedirect(arg0, arg1, "/admin/lot/home");
		} else {
			throw new IllegalStateException();
		}

	}

}
