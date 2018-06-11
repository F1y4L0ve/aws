package jp.co.wqf;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Tag;

public class Subnet {

	public static void main(String[] args) {
		String vpcId = "vpc-10daaa77";
		AmazonEC2 ec2 = Ec2Utils.getEc2();

		String natSnId = ec2.createSubnet(new CreateSubnetRequest(vpcId, "10.1.1.0/24")).getSubnet().getSubnetId();
		ec2.createTags(new CreateTagsRequest().withResources(natSnId).withTags(new Tag("Name", "NATSN")));
	}

}
