package jp.co.wqf;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;

public class Ec2 {

	public static void main(String[] args) {
		AmazonEC2 ec2 = Ec2Utils.getEc2();

		String imageId = "ami-8bbab2f7";
		String subnetId = "subnet-ab3733e2";
		String securityGroupId = "sg-7794000f";
		String keyName = "common";

		RunInstancesRequest request = new RunInstancesRequest().withImageId(imageId)
				.withInstanceType(InstanceType.T2Micro).withMaxCount(1).withMinCount(1).withSubnetId(subnetId)
				.withKeyName(keyName).withSecurityGroupIds(securityGroupId);
		ec2.runInstances(request);
	}

}
