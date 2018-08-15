package jp.co.wqf.admin;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

import jp.co.wqf.DynamoDBUtils;

public class DeleteTable {

	public static void main(String[] args) throws InterruptedException {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");
		table.delete();
		table.waitForDelete();
		System.out.print("Success.");
	}

}
