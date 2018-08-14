package jp.co.wqf;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class CreateDynamoDB {
	public static void main(String[] args) {
		AmazonDynamoDB dynamoDB = DynamoDBUtils.getDynamoDB();
		dynamoDB.createTable(new CreateTableRequest().withTableName("Movies")
				.withKeySchema(new KeySchemaElement("year", KeyType.HASH), new KeySchemaElement("title", KeyType.RANGE))
				.withAttributeDefinitions(new AttributeDefinition("year", ScalarAttributeType.N),
						new AttributeDefinition("title", ScalarAttributeType.S))
				.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L)));
	}
}
