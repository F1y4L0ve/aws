package jp.co.wqf;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.QueueAttributeName;

public class CreateSqs {

	public static void main(String[] args) {
		AmazonSQS sqs = SqsUtils.getSqs();
		CreateQueueResult deadQueue = sqs.createQueue("MyDeadQueue");
		String deadQueueArn = sqs
				.getQueueAttributes(new GetQueueAttributesRequest(deadQueue.getQueueUrl())
						.withAttributeNames(QueueAttributeName.QueueArn.toString()))
				.getAttributes().get(QueueAttributeName.QueueArn.toString());

		CreateQueueResult sourceQueue = sqs.createQueue(new CreateQueueRequest("MyQueue")
				.addAttributesEntry(QueueAttributeName.VisibilityTimeout.toString(), String.valueOf(31))
				.addAttributesEntry(QueueAttributeName.MessageRetentionPeriod.toString(),
						String.valueOf(60 * 60 * 24 * 5))
				.addAttributesEntry(QueueAttributeName.MaximumMessageSize.toString(), String.valueOf(1024 * 128))
				.addAttributesEntry(QueueAttributeName.DelaySeconds.toString(), String.valueOf(0))
				.addAttributesEntry(QueueAttributeName.ReceiveMessageWaitTimeSeconds.toString(), String.valueOf(0))
				.addAttributesEntry(QueueAttributeName.RedrivePolicy.toString(),
						"{\"maxReceiveCount\":\"5\", \"deadLetterTargetArn\":\"" + deadQueueArn + "\"}"));

	}

}
