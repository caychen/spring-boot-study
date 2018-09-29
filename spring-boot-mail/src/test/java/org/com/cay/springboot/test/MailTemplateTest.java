package org.com.cay.springboot.test;

import freemarker.template.TemplateException;
import org.com.cay.springboot.mail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cay on 2017/11/14.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MailTemplateTest {

	@Autowired
	private MailService mailService;

	@Test
	public void testFreemarkerMail() throws MessagingException, IOException, TemplateException {

		Map<String, Object> params = new HashMap<>();
		params.put("username", "Cay");

		mailService.sendTemplateMail("测试Springboot发送模版邮件", params);

	}
}
