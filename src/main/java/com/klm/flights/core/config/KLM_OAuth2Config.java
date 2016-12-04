package com.klm.flights.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * 
 * OAuth2 settings for using simple-travel-mock-api endpoint
 * 
 * @author Sasa Radovanovic
 *
 */
@Configuration
public class KLM_OAuth2Config {
	
	
	@Value("${oauth.clientid}")
	private String OAuthClientId;
	
	@Value("${oauth.secret}")
	private String OAuthClientSecret;
	
	@Value("${oauth.tokenAccessUrl}")
	private String OAuthTokenAccessUrl;
	
	@Bean
	public OAuth2RestOperations createRestTemplate () {
		AccessTokenRequest atr = new DefaultAccessTokenRequest();
	    return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
	}
	

	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
		resource.setAccessTokenUri(OAuthTokenAccessUrl);
		resource.setClientId(OAuthClientId);
		resource.setClientSecret(OAuthClientSecret);
		return resource;
	}

}
