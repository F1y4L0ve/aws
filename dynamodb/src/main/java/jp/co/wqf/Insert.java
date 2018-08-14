package jp.co.wqf;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Insert {

	public static void main(String[] args) throws JsonParseException, IOException {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB db = new DynamoDB(client);
		Table table = db.getTable("Movies");
		String jsonfile = Insert.class.getResource("moviedata.json").getPath();
		JsonParser parser = new JsonFactory().createParser(new File(jsonfile));
		JsonNode rootNode = new ObjectMapper().readTree(parser);
		Iterator<JsonNode> iter = rootNode.iterator();
		ObjectNode currentNode;
		while (iter.hasNext()) {
			currentNode = (ObjectNode) iter.next();
			int year = currentNode.path("year").asInt();
			String title = currentNode.path("title").asText();
			try {
				table.putItem(new Item().withPrimaryKey("year", year, "title", title).withJSON("info",
						currentNode.path("info").toString()));
				System.out.println("PutItem succeeded: " + year + " " + title);
			} catch (Exception e) {
				System.err.println("Unable to add movie: " + year + " " + title);
				System.err.println(e.getMessage());
				break;
			}
		}
		parser.close();
	}

}
