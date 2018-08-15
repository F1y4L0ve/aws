package jp.co.wqf.operate;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;

import jp.co.wqf.DynamoDBUtils;

public class Delete {

	public static void main(String[] args) {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");

		DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
				.withPrimaryKey(new PrimaryKey("year", 2013, "title", "Prisoners"))
				.withConditionExpression("info.rating <= :val").withValueMap(new ValueMap().withNumber(":val", 5.0));
		table.deleteItem(deleteItemSpec);
	}

}
