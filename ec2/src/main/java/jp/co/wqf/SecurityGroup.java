package jp.co.wqf;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Tag;

public class SecurityGroup {

	public static void main(String[] args) {
		String vpcId = "vpc-10daaa77";
		AmazonEC2 ec2 = Ec2Utils.getEc2();

		String groupId = ec2.createSecurityGroup(new CreateSecurityGroupRequest().withVpcId(vpcId)
				.withGroupName("NATSG").withDescription("Security Group For Nat")).getGroupId();
		ec2.createTags(new CreateTagsRequest().withResources(groupId).withTags(new Tag("Name", "NATSG")));

		ec2.authorizeSecurityGroupIngress(new AuthorizeSecurityGroupIngressRequest().withGroupId(groupId)
				.withIpProtocol("tcp").withFromPort(22).withToPort(22).withCidrIp("126.247.211.163/32"));
	}

}
