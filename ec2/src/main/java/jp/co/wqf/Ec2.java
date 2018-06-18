package jp.co.wqf;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.ResourceType;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TagSpecification;
import com.amazonaws.services.ec2.model.VolumeType;

public class Ec2 {

	public static void main(String[] args) {
		AmazonEC2 ec2 = Ec2Utils.getEc2();

		String imageId = "ami-8bbab2f7";
		String subnetId = "subnet-ab3733e2";
		String securityGroupId = "sg-7794000f";
		String keyName = "common";

		RunInstancesRequest request = new RunInstancesRequest().withImageId(imageId)
				.withInstanceType(InstanceType.T2Micro).withMaxCount(1).withMinCount(1).withSubnetId(subnetId)
				.withKeyName(keyName).withSecurityGroupIds(securityGroupId)
				.withBlockDeviceMappings(new BlockDeviceMapping().withDeviceName("/dev/xvda")
						.withEbs(new EbsBlockDevice().withVolumeType(VolumeType.Gp2).withVolumeSize(3)))
				.withTagSpecifications(new TagSpecification().withResourceType(ResourceType.Instance)
						.withTags(new Tag("Name", "NATEC")));
		ec2.runInstances(request);
	}

}
