package jp.co.wqf;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.DeleteTopicRequest;

public class DeleteTopic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AmazonSNS sns = SnsUtils.getSns();
		String topicArn = "";
		sns.deleteTopic(new DeleteTopicRequest(topicArn));
	}

}
