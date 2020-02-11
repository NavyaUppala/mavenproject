package com.capitalone;

import java.util.List;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class Application {

	public static void main(String[] args) {
		System.out.println("Application Started");
		
		String queueUrl = "https://sqs.us-east-1.amazonaws.com/196613382328/myQueue";
		
		AmazonSQS sqsClient = AmazonSQSClientBuilder.defaultClient();
		
		SendMessageRequest send_msg_request = new SendMessageRequest()
				.withQueueUrl(queueUrl)
				.withMessageBody("hello world")
				.withDelaySeconds(5);
		
		sqsClient.sendMessage(send_msg_request);
		
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest();
		receiveMessageRequest.setMaxNumberOfMessages(10);
		receiveMessageRequest.setQueueUrl(queueUrl);
        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();

        // delete messages from the queue
        for (Message m : messages) {
        	System.out.println(m);
        	//sqsClient.deleteMessage(queueUrl, m.getReceiptHandle());
        }
	}
}
