package jp.co.wqf;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.SubscribeRequest;

public class CreateTopic {

	public static void main(String[] args) {
		AmazonSNS sns = SnsUtils.getSns();
		CreateTopicResult result = sns.createTopic(new CreateTopicRequest("test"));
		String topicArn = result.getTopicArn();
		System.out.println("Topic ARN:" + topicArn);
		sns.subscribe(new SubscribeRequest(topicArn, "email", "gikeihi@yahoo.co.jp"));
	}

}
