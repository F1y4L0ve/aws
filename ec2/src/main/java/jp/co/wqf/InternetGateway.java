package jp.co.wqf;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.AttachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Tag;

public class InternetGateway {

	public static void main(String[] args) {
		String vpcId = "vpc-10daaa77";
		AmazonEC2 ec2 = Ec2Utils.getEc2();

		String internetGatewayId = ec2.createInternetGateway().getInternetGateway().getInternetGatewayId();
		ec2.createTags(new CreateTagsRequest().withResources(internetGatewayId).withTags(new Tag("Name", "WPGW")));

		ec2.attachInternetGateway(
				new AttachInternetGatewayRequest().withVpcId(vpcId).withInternetGatewayId(internetGatewayId));
	}

}
