package jp.co.wqf;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

public class SnsUtils {
	public static AmazonSNS getSns() {
		AmazonSNS sns = AmazonSNSClientBuilder.standard().withCredentials(new ProfileCredentialsProvider())
				.withRegion(Regions.AP_NORTHEAST_1).build();
		return sns;
	}
}
