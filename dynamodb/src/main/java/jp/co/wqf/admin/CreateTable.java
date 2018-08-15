package jp.co.wqf.admin;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import jp.co.wqf.DynamoDBUtils;

public class CreateTable {
	public static void main(String[] args) throws InterruptedException {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB dynamoDB = new DynamoDB(client);
		Table table = dynamoDB.createTable(new CreateTableRequest().withTableName("Movies")
				.withKeySchema(new KeySchemaElement("year", KeyType.HASH), new KeySchemaElement("title", KeyType.RANGE))
				.withAttributeDefinitions(new AttributeDefinition("year", ScalarAttributeType.N),
						new AttributeDefinition("title", ScalarAttributeType.S))
				.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L)));
		table.waitForActive();
		System.out.print("Success.");
	}
}
