package jp.co.wqf;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.AssociateRouteTableRequest;
import com.amazonaws.services.ec2.model.CreateRouteRequest;
import com.amazonaws.services.ec2.model.CreateRouteTableRequest;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Tag;

public class RouteTable {

	public static void main(String[] args) {
		String vpcId = "vpc-10daaa77";
		String gatewayId = "igw-adc089c9";
		String subnetId = "subnet-ab3733e2";
		AmazonEC2 ec2 = Ec2Utils.getEc2();

		String routeTableId = ec2.createRouteTable(new CreateRouteTableRequest().withVpcId(vpcId)).getRouteTable()
				.getRouteTableId();
		ec2.createTags(new CreateTagsRequest().withResources(routeTableId).withTags(new Tag("Name", "NATRT")));

		ec2.createRoute(new CreateRouteRequest().withRouteTableId(routeTableId).withDestinationCidrBlock("0.0.0.0/0")
				.withGatewayId(gatewayId));

		ec2.associateRouteTable(new AssociateRouteTableRequest().withRouteTableId(routeTableId).withSubnetId(subnetId));
	}

}
