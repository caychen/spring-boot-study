package org.com.cay.springboot.test;

import org.com.cay.springboot.mail.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Cay on 2017/11/14.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SimpleMailTest {

	@Autowired
	private MailService mailService;

	@Test
	public void sendMail(){

		mailService.sendSimpleMail("测试Springboot发送邮件", "发送邮件...");
	}
}
