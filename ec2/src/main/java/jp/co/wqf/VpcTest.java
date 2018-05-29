package jp.co.wqf;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.AttachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateVpcRequest;

public class VpcTest {

	public static void main(String[] args) {
		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withCredentials(new ProfileCredentialsProvider())
				.withRegion(Regions.AP_NORTHEAST_1).build();
		String vpcId = createVpc(ec2, "10.1.0.0/16");
		String internetGatewayId = creteInternetGateway(ec2);
		ec2.attachInternetGateway(
				new AttachInternetGatewayRequest().withVpcId(vpcId).withInternetGatewayId(internetGatewayId));
	}

	private static String creteInternetGateway(AmazonEC2 ec2) {
		return ec2.createInternetGateway().getInternetGateway().getInternetGatewayId();
	}

	private static String createVpc(AmazonEC2 ec2, String cidr) {
		return ec2.createVpc(new CreateVpcRequest(cidr)).getVpc().getVpcId();
	}

}
