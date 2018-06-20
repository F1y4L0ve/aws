package jp.co.wqf;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;

public class SendMessage {

	public static void main(String[] args) {
		AmazonSNS sns = SnsUtils.getSns();
		String topicArn = "";
		String subject = "Test";
		String message = "This is a test message";
		sns.publish(new PublishRequest(topicArn, message, subject));
	}

}
