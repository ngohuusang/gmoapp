package vn.gmostore.account.oauth;

import org.springframework.security.oauth2.provider.BaseClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Override
	public ClientDetails loadClientByClientId(String clientId)
			throws ClientRegistrationException {
		BaseClientDetails details = new BaseClientDetails("my-trusted-client",
				null, "read,write,trust",
				"password,authorization_code,refresh_token,implicit",
				"ROLE_USER");
		details.setAccessTokenValiditySeconds(43200);
		return details;
	}

}
