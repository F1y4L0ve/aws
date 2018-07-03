package jp.co.wqf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class SendReciveMessage {
	private final static String QUEUE_URL = "https://sqs.ap-northeast-1.amazonaws.com/270221123016/MyQueue";

	public static void main(String[] args) {
		AmazonSQS sqs = SqsUtils.getSqs();
		Runnable sendRunner = () -> {
			for (;;) {
				sqs.sendMessage(new SendMessageRequest().withQueueUrl(QUEUE_URL)
						.withMessageBody(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(sendRunner).start();

		Runnable recieveRunner = () -> {
			for (;;) {
				List<Message> messages = sqs.receiveMessage(new ReceiveMessageRequest().withQueueUrl(QUEUE_URL)
						.withMaxNumberOfMessages(1).withWaitTimeSeconds(5)).getMessages();
				for (Message message : messages) {
					System.out.println(Thread.currentThread().getName() + ":" + message.getBody());
					sqs.deleteMessage(new DeleteMessageRequest().withQueueUrl(QUEUE_URL)
							.withReceiptHandle(message.getReceiptHandle()));
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		for (int i = 0; i < 5; i++) {
			new Thread(recieveRunner).start();
		}
	}

}
