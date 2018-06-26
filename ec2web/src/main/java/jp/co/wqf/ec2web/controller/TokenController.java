package jp.co.wqf.ec2web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;

@RestController
@RequestMapping("/token")
public class TokenController {

	@GetMapping
	public Credentials getToken() {
		AWSCredentialsProvider cp = new InstanceProfileCredentialsProvider(false);
		// AWSCredentialsProvider cp = new ProfileCredentialsProvider();
		AWSSecurityTokenService sts = AWSSecurityTokenServiceClientBuilder.standard().withCredentials(cp).build();
		sts.assumeRole(new AssumeRoleRequest().withRoleArn("arn:aws:iam::270221123016:role/Role4S3")
				.withRoleSessionName("test").withDurationSeconds(3600));
		// GetSessionTokenResult sessionToken = sts
		// .getSessionToken(new GetSessionTokenRequest().withDurationSeconds(1800));
		// Credentials credentials = sessionToken.getCredentials();
		GetSessionTokenResult sessionToken = sts.getSessionToken();
		Credentials credentials = sessionToken.getCredentials();
		return credentials;
	}
}
