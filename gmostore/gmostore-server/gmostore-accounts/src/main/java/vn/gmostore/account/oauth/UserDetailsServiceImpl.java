package vn.gmostore.account.oauth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService{

	
	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		
		Set<GrantedAuthority> grantedAuthority = new HashSet<GrantedAuthority>();
		GrantedAuthority granted = new SimpleGrantedAuthority("ROLE_USER");
		grantedAuthority.add(granted);
		
		UserDetails userDetails = new User(userName, "password", true, true,  true, true, grantedAuthority);
		return userDetails;
	}

}
