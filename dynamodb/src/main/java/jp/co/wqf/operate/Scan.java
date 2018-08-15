package jp.co.wqf.operate;

import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

import jp.co.wqf.DynamoDBUtils;

public class Scan {

	public static void main(String[] args) {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");
		ScanSpec scanSpec = new ScanSpec().withProjectionExpression("#yr, title, info.rating")
				.withFilterExpression("#yr between :start_yr and :end_yr")
				.withNameMap(new NameMap().with("#yr", "year"))
				.withValueMap(new ValueMap().withNumber(":start_yr", 2012).withNumber(":end_yr", 2013));
		ItemCollection<ScanOutcome> items = table.scan(scanSpec);
		Iterator<Item> iterator = items.iterator();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			System.out.println(item.toJSONPretty());
		}
	}

}
