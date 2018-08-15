package jp.co.wqf.operate;

import java.util.HashMap;
import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;

import jp.co.wqf.DynamoDBUtils;

public class Search {

	public static void main(String[] args) {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");
		// search1(table);
		search2(table);
	}

	private static void search2(Table table) {
		HashMap<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("#yr", "year");

		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put(":yyyy", 2013);
		valueMap.put(":letter1", "A");
		valueMap.put(":letter2", "L");

		QuerySpec querySpec = new QuerySpec().withProjectionExpression("#yr, title, info.genres, info.actors[0]")
				.withKeyConditionExpression("#yr = :yyyy and title between :letter1 and :letter2").withNameMap(nameMap)
				.withValueMap(valueMap);
		ItemCollection<QueryOutcome> items = table.query(querySpec);
		Iterator<Item> iterator = items.iterator();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			System.out.println(item.getNumber("year") + ": " + item.getString("title"));
		}
	}

	private static void search1(Table table) {
		HashMap<String, String> nameMap = new HashMap<String, String>();
		nameMap.put("#yr", "year");

		HashMap<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put(":yyyy", 2013);

		QuerySpec querySpec = new QuerySpec().withKeyConditionExpression("#yr = :yyyy").withNameMap(nameMap)
				.withValueMap(valueMap);
		ItemCollection<QueryOutcome> items = table.query(querySpec);
		Iterator<Item> iterator = items.iterator();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			System.out.println(item.getNumber("year") + ": " + item.getString("title"));
		}
	}

}
