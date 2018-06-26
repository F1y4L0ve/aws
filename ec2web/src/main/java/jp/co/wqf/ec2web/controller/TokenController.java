package jp.co.wqf.ec2web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenRequest;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;

@RestController
@RequestMapping("/token")
public class TokenController {

	@GetMapping
	public Credentials getToken() {
		// AWSCredentialsProvider cp = new InstanceProfileCredentialsProvider(false);
		AWSCredentialsProvider cp = new ProfileCredentialsProvider();
		AWSSecurityTokenService sts = AWSSecurityTokenServiceClientBuilder.standard().withCredentials(cp).build();
		GetSessionTokenResult sessionToken = sts
				.getSessionToken(new GetSessionTokenRequest().withDurationSeconds(1800));
		Credentials credentials = sessionToken.getCredentials();

		return credentials;
	}
}
