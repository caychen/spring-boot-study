package org.com.cay.spring.boot;

import io.searchbox.client.JestClient;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import org.com.cay.spring.boot.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElasticsearchApplicationTests {

	@Autowired
	private JestClient jestClient;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void createIndex() throws IOException {
		Book book = new Book();
		book.setId(1);
		book.setAuthor("caychen");
		book.setContent("Hello World");
		book.setTitle("First Elasticsearch");
		Index index = new Index.Builder(book).index("caychen").type("book").build();

		DocumentResult result = jestClient.execute(index);
		System.out.println(result.isSucceeded());
	}

	@Test
	public void getIndex(){
		Get get = new Get.Builder("caychen", "1").type("book").build();

		System.out.println(get.toString());
	}

}
