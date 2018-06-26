package org.com.cay.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by Cay on 2017/11/14.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MimeMailTest {

	@Autowired
	private JavaMailSender javaMailSender;

	@Test
	public void testMail() throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		//是否发送的邮件是富文本（附件，图片，html等）
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

		messageHelper.setFrom("1243505830@qq.com");
		messageHelper.setTo("412425870@qq.com");
		messageHelper.setSubject("测试Springboot发送带附件的邮件");

		messageHelper.setText("欢迎进入<a href=\"http://www.baidu.com\">百度首页</a>");

		messageHelper.addAttachment("附件", new FileSystemResource("F:\\Owner\\spring-boot\\spring-boot-mail\\src\\main\\resources\\public\\file.txt"));

		javaMailSender.send(mimeMessage);
	}
}
