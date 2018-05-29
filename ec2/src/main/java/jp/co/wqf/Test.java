package jp.co.wqf;

import java.util.List;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Vpc;

public class Test {

	public static void main(String[] args) {
		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withCredentials(new ProfileCredentialsProvider())
				.withRegion(Regions.AP_NORTHEAST_1).build();
		List<Vpc> vpcs = ec2.describeVpcs().getVpcs();
		vpcs.forEach(v -> {
			String vpcId = v.getVpcId();
			System.out.println(vpcId);

			DescribeInstancesRequest request = new DescribeInstancesRequest();
			request.withFilters(new Filter("vpc-id").withValues(vpcId));
			List<Reservation> reservations = ec2.describeInstances(request).getReservations();
			reservations.forEach(r -> {
				List<Instance> ins = r.getInstances();
				ins.forEach(i -> {
					System.out.println(i);
				});
			});
		});

	}

}
