package org.com.cay.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cay on 2017/11/14.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleMailTest {

	@Autowired
	private JavaMailSender javaMailSender;

	@Test
	public void sendMail(){
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("1243505830@qq.com");
		mailMessage.setTo("412425870@qq.com");
		mailMessage.setSubject("测试Springboot发送邮件");
		mailMessage.setText("发送邮件...");

		javaMailSender.send(mailMessage);
	}
}
