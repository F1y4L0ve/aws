package jp.co.wqf;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.DryRunResult;

public class DryRunTest {

	public static void main(String[] args) {
		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withCredentials(new ProfileCredentialsProvider())
				.withRegion(Regions.AP_NORTHEAST_1).build();
		DryRunResult<CreateVpcRequest> dryResponse = ec2.dryRun(new CreateVpcRequest().withCidrBlock("10.1.0.0/16"));
		System.out.println(dryResponse.getMessage());
	}

}
