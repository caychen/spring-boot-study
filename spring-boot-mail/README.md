## 1、添加mail依赖
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>
```

## 2、添加配置信息(以QQ邮箱为例)
```properties
# 设置邮箱主机
spring.mail.host=smtp.qq.com

# 设置用户名
spring.mail.username=发送邮件的邮箱(xxxx@qq.com)

# 设置密码，该处的密码是QQ邮箱开启SMTP的授权码而非QQ密码
spring.mail.password=xxxxxx

# 设置是否需要认证，如果为true,那么用户名和密码就必须的，
# 如果设置false，可以不设置用户名和密码，当然也得看你的对接的平台是否支持无密码进行访问的。
spring.mail.properties.mail.smtp.auth=true

# STARTTLS[1]  是对纯文本通信协议的扩展。它提供一种方式将纯文本连接升级为加密连接（TLS或SSL），而不是另外使用一个端口作加密通信。
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true

spring.freemarker.cache=false
```
一旦添加了mail依赖之后，SpringBoot会根据配置文件内容来创建 **JavaMailSender** 实例，只需要在使用的地方通过依赖注入的方式注入 **JavaMailSender** 对象。

## 3、测试
### 3.1、简单邮件的发送
创建 **SimpleMailMessage** 对象即可
```java
@Autowired
private JavaMailSender javaMailSender;

SimpleMailMessage mailMessage = new SimpleMailMessage();
mailMessage.setFrom("发送邮件的邮箱");
mailMessage.setTo("接收邮件的邮箱");
mailMessage.setSubject("邮件主题");
mailMessage.setText("邮件的文本内容");
//发送邮箱
javaMailSender.send(mailMessage);
```

### 3.2、发送带有附件的邮件
创建 **MimeMessage** 对象
```java
@Autowired
private JavaMailSender javaMailSender;

MimeMessage mimeMessage = javaMailSender.createMimeMessage();

//是否发送的邮件是富文本（附件，图片，html等）
MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

messageHelper.setFrom("发送邮件的邮箱");
messageHelper.setTo("接收邮件的邮箱");
messageHelper.setSubject("测试Springboot发送带附件的邮件");

messageHelper.setText("欢迎进入<a href=\"http://www.baidu.com\">百度首页</a>");

messageHelper.addAttachment("附件", new FileSystemResource("附件的本地路径（绝对路径或者相对路径皆可）"));

javaMailSender.send(mimeMessage);
```

### 3.3、模版文件(以Freemakrer为例)
#### 3.3.1、添加Freemakrer依赖
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
```

#### 3.3.2、在resources/templates/目录下创建模版文件
```html
<html>
<body>
    <h3>你好， ${username}, 这是一封模板邮件!</h3>
</body>
</html>
```

```java
@Autowired
private JavaMailSender javaMailSender;

MimeMessage mimeMessage = javaMailSender.createMimeMessage();

MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

helper.setFrom("发送邮件的邮箱");
helper.setTo("接收邮件的邮箱");
helper.setSubject("测试Springboot发送模版邮件");

Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
//设置模版所在的目录
configuration.setClassForTemplateLoading(this.getClass(), "/templates");

//填充的数据
Map<String, Object> model = new HashMap<>();
model.put("username", "Cay");
//将model数据填充到模版文件中，然后生成html的文本，model中的key对应模版文件的${key}
String html = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("mail.ftl"), model);
helper.setText(html, true);

javaMailSender.send(mimeMessage);
```

**关键代码：**
```java
Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
configuration.setClassForTemplateLoading(this.getClass(), "/templates");

FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("模版文件"), "模型数据");
```