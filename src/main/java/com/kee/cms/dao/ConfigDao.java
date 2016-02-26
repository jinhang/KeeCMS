package com.kee.cms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kee.cms.entity.Config;

/**
 * 网站配置
 * 
 * @author keehang
 * 
 */

@Repository
public interface ConfigDao {

	
	/**
	 * 增加配置
	 * 
	 * @return Integer
	 */
	public int addConfig(Config config);


	/**
	 * 删除配置
	 * 
	 * return Integer
	 */
	public int deleteConfig(@Param("key") String key);

	

	/**
	 * 更新配置
	 * 
	 * @return Integer
	 */
	public int updateConfig(Config config);

	

	/**
	 * 查看配置
	 * 
	 * @return Config
	 */
	public Config getConfigByKey(@Param("key") String key);
}
