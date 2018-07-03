package jp.co.wqf;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class SqsUtils {
	public static AmazonSQS getSqs() {
		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(new ProfileCredentialsProvider())
				.withRegion(Regions.AP_NORTHEAST_1).build();
		return sqs;
	}
}
