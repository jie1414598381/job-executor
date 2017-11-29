package com.yst.executor.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.yst.util.Configure;

public class JobInit implements ServletContextListener {
	private static Logger logger = Logger.getLogger(JobInit.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		JobService.end();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("job初始化... ... ...");
		try {
			new Configure();
			new JobService();
			logger.info("job初始化完成");
		} catch (Throwable t) {
			logger.error("job初始化异常", t);
		}

	}

}
