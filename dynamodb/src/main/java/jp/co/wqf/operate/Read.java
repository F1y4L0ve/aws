package jp.co.wqf.operate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

import jp.co.wqf.DynamoDBUtils;

public class Read {

	public static void main(String[] args) {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");
		Item outcome = table.getItem(new GetItemSpec().withPrimaryKey("year", 2015, "title", "The Big New Movie"));
		System.out.println(outcome.toJSONPretty());
	}

}
