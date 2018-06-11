package jp.co.wqf;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.Tag;

public class Vpc {

	public static void main(String[] args) {
		AmazonEC2 ec2 = Ec2Utils.getEc2();
		String vpcId = ec2.createVpc(new CreateVpcRequest("10.1.0.0/16")).getVpc().getVpcId();
		ec2.createTags(new CreateTagsRequest().withResources(vpcId).withTags(new Tag("Name", "WPVPC")));
	}

}
