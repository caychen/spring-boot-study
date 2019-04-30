package org.com.cay.spring.boot.springbootkafka.message;

import lombok.Data;

import java.util.Date;

/**
 * Author:       Caychen
 * Class:        org.com.cay.spring.boot.springbootkafka.message.Message
 * Date:         2019/4/28
 * Desc:
 */

@Data
public class Message {

    private Long id;
    private String msg;
    private Date sendTime;

}
