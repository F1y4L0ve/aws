package jp.co.wqf.admin;

import java.util.Iterator;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.TableCollection;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;

import jp.co.wqf.DynamoDBUtils;

public class ListTable {

	public static void main(String[] args) {
		AmazonDynamoDB client = DynamoDBUtils.getDynamoDB();
		DynamoDB dynamoDB = new DynamoDB(client);
		TableCollection<ListTablesResult> tables = dynamoDB.listTables();
		Iterator<Table> iterator = tables.iterator();

		while (iterator.hasNext()) {
			Table table = iterator.next();
			TableDescription tableDescription = table.describe();
			System.out.printf("status:\t\t\t%s\nName:\t\t\t%s\nReadCapacityUnits:\t%d\nWriteCapacityUnits:\t%d\n",
					tableDescription.getTableStatus(), tableDescription.getTableName(),
					tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
					tableDescription.getProvisionedThroughput().getWriteCapacityUnits());
			System.out.println(table.getTableName());
		}
	}

}
