package org.com.cay.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.com.cay.springboot.entity.Product;

/**
 * Created by Cay on 2017/12/12.
 */
//@Mapper//必须
public interface IProductMapper {

	Product findByName(@Param("name") String name);

	int insert(@Param("name") String name, @Param("price") Double price);

	int deleteAll();
}
