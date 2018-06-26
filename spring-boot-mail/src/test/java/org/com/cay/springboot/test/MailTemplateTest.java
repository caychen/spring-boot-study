package org.com.cay.springboot.test;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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
	private JavaMailSender javaMailSender;

	@Test
	public void testFreemarkerMail() throws MessagingException, IOException, TemplateException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setFrom("1243505830@qq.com");
		helper.setTo("412425870@qq.com");
		helper.setSubject("测试Springboot发送模版邮件");

		Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
		configuration.setClassForTemplateLoading(this.getClass(), "/templates");

		Map<String, Object> model = new HashMap<>();

		model.put("username", "Cay");
		String html = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("mail.ftl"), model);

		helper.setText(html, true);

		javaMailSender.send(mimeMessage);

	}
}
