package jp.co.wqf.operate;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import jp.co.wqf.DynamoDBUtils;

public class Insert {

	public static void main(String[] args) {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");
		int year = 2015;
		String title = "The Big New Movie";

		final Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("plot", "Nothing happens at all.");
		infoMap.put("rating", 0);

		PutItemOutcome outcome = table
				.putItem(new Item().withPrimaryKey("year", year, "title", title).withMap("info", infoMap));
		System.out.println("PutItem succeeded:\n" + outcome.getPutItemResult());
	}

}
