package org.com.cay.springboot.test;

import org.com.cay.springboot.mail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cay on 2017/11/14.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MimeMailTest {

	@Autowired
	private MailService mailService;

	@Test
	public void testMail() throws MessagingException {

		Map<String, String> attachmentMap = new HashMap<>();
		attachmentMap.put("附件", "F:\\Owner\\spring-boot-study\\spring-boot-mail\\src\\main\\resources\\public\\file.txt");

		mailService.sendHtmlMail("测试Springboot发送带附件的邮件", "欢迎进入<a href=\"http://www.baidu.com\">百度首页</a>", attachmentMap);

	}
}
