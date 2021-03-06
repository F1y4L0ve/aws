package jp.co.wqf.admin;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

import jp.co.wqf.DynamoDBUtils;

public class ReadTable {

	public static void main(String[] args) {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");
		TableDescription tableDescription = table.describe();
		System.out.printf("status:\t\t\t%s\nName:\t\t\t%s\nReadCapacityUnits:\t%d\nWriteCapacityUnits:\t%d\n",
				tableDescription.getTableStatus(), tableDescription.getTableName(),
				tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
				tableDescription.getProvisionedThroughput().getWriteCapacityUnits());
	}

}
