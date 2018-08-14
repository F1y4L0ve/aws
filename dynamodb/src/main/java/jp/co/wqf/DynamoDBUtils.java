package jp.co.wqf;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBUtils {
	public static AmazonDynamoDB getDynamoDB() {
		AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(new ProfileCredentialsProvider()).withRegion(Regions.AP_NORTHEAST_1).build();
		return dynamoDB;
	}
}
