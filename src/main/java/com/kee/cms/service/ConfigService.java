package com.kee.cms.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kee.cms.dao.ConfigDao;
import com.kee.cms.entity.Config;

/**
 * 网站配置
 * 
 * @author keehang
 * 
 */
@Service
public class ConfigService {

	@Autowired
	private ConfigDao configDao;

	/**
	 * 增加配置
	 * 
	 * @param key
	 * @param value
	 * @return Config
	 */
	public Config addConfig(String key, String value) {
		Config config = new Config();
		config.setKey(key);
		config.setValue(value);
		config.setCreateTime(new Date());
		configDao.addConfig(config);
		return config;
	}

	/**
	 * 删除配置
	 * 
	 * @param key
	 * @return Integer
	 */
	@CacheEvict(value = "config", key = "#key")
	public int deleteConfigByKey(String key) {
		return configDao.deleteConfig(key);
	}
	/**
	 * 更新配置
	 * 
	 * @param key
	 * @param value
	 */
	@CacheEvict(value = "config", key = "#key")
	public Config updagteConfigByKey(String key, String value) {
		Config config = configDao.getConfigByKey(key);
		config.setValue(value);
		configDao.updateConfig(config);
		this.getConfigByKey(key);
		return config;
	}
	/**
	 * @param key
	 * @return
	 */
	@Cacheable(value = "config", key = "#key")
	public String getConfigByKey(String key) {
		Config config = configDao.getConfigByKey(key);
		if (config == null) {
			return "";
		} else {
			return config.getValue();
		}
	}
}
