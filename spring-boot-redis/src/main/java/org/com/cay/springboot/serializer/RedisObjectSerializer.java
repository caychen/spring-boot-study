package org.com.cay.springboot.serializer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by Cay on 2017/9/26.
 */
//用于解析对象的类
public class RedisObjectSerializer implements RedisSerializer<Object> {

	private Converter<Object, byte[]> serializer = new SerializingConverter();
	private Converter<byte[], Object> deserializer = new DeserializingConverter();

	private final byte[] EMPTY_BYTE = new byte[0];

	@Override
	public byte[] serialize(Object t) throws SerializationException {
		if(t == null){
			return EMPTY_BYTE;
		}

		try{
			return serializer.convert(t);
		}catch (Exception e) {
			return EMPTY_BYTE;
		}
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		if(isEmpty(bytes))
			return null;

		try{
			return deserializer.convert(bytes);
		}catch (Exception e) {
			return null;
		}
	}

	private boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}
}
