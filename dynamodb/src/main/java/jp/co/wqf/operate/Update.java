package jp.co.wqf.operate;

import java.util.Arrays;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import jp.co.wqf.DynamoDBUtils;

public class Update {

	public static void main(String[] args) {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");
		UpdateItemOutcome outcome = update3(table);
		System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
	}

	private static UpdateItemOutcome update3(Table table) {
		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey(new PrimaryKey("year", 2013, "title", "Prisoners"))
				.withUpdateExpression("remove info.actors[0]").withConditionExpression("size(info.actors) >= :num")
				.withValueMap(new ValueMap().withNumber(":num", 2)).withReturnValues(ReturnValue.UPDATED_NEW);
		UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
		return outcome;
	}

	private static UpdateItemOutcome update2(Table table) {
		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("year", 2013, "title", "Prisoners")
				.withUpdateExpression("set info.rating = info.rating + :val")
				.withValueMap(new ValueMap().withNumber(":val", 1)).withReturnValues(ReturnValue.UPDATED_NEW);
		UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
		return outcome;
	}

	private static UpdateItemOutcome update1(Table table) {
		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("year", 2013, "title", "Prisoners")
				.withUpdateExpression("set info.rating = :r, info.plot=:p, info.actors=:a")
				.withValueMap(new ValueMap().withNumber(":r", 5.5).withString(":p", "Everything happens all at once.")
						.withList(":a", Arrays.asList("Larry", "Moe", "Curly")))
				.withReturnValues(ReturnValue.UPDATED_NEW);
		UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
		return outcome;
	}

}
