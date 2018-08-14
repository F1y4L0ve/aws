package jp.co.wqf.ec2web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;

@Controller
@RequestMapping("/token")
public class TokenController {

	@GetMapping
	public String getToken(Model model) {
		// AWSCredentialsProvider cp = new InstanceProfileCredentialsProvider(false);
		AWSCredentialsProvider cp = new ProfileCredentialsProvider();
		AWSSecurityTokenService sts = AWSSecurityTokenServiceClientBuilder.standard().withCredentials(cp).build();
		AssumeRoleResult roleResult = sts
				.assumeRole(new AssumeRoleRequest().withRoleArn("arn:aws:iam::270221123016:role/Role4S3")
						.withRoleSessionName("test").withDurationSeconds(3600));
		Credentials credentials = roleResult.getCredentials();
		model.addAttribute("credentials", credentials);
		// GetSessionTokenResult sessionToken = sts
		// .getSessionToken(new GetSessionTokenRequest().withDurationSeconds(1800));
		// Credentials credentials = sessionToken.getCredentials();
		// GetSessionTokenResult sessionToken = sts.getSessionToken();
		// Credentials credentials = sessionToken.getCredentials();

		AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_1)
				.withCredentials(
						new AWSStaticCredentialsProvider(new BasicSessionCredentials(credentials.getAccessKeyId(),
								credentials.getSecretAccessKey(), credentials.getSessionToken())))
				.build();
		ObjectListing object = s3.listObjects("weiqingfei-img", "bbb/");

		return "token/index";
	}
}
