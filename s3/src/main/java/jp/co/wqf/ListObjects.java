package jp.co.wqf;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;

public class ListObjects {

	public static void main(String[] args) {
		AmazonS3 s3 = S3Utils.getS3();
		s3.listBuckets().forEach(b -> {
			System.out.println(b.getName());
			ListObjectsV2Result ret = s3.listObjectsV2(
					new ListObjectsV2Request().withBucketName(b.getName()).withDelimiter("/").withPrefix("bbb/"));
			ret.getObjectSummaries().forEach(o -> {
				System.out.println(o.getKey());
			});
		});
		;
	}

}
