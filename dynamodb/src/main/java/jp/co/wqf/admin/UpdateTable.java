package jp.co.wqf.admin;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

import jp.co.wqf.DynamoDBUtils;

public class UpdateTable {

	public static void main(String[] args) throws InterruptedException {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");
		ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput().withReadCapacityUnits(1L)
				.withWriteCapacityUnits(1L);
		table.updateTable(provisionedThroughput);
		table.waitForActive();
		System.out.print("Success.");
	}

}
