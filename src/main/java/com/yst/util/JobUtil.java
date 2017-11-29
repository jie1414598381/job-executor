package com.yst.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.yst.client.job.IJob;
import com.yst.client.job.JobEvent;

public class JobUtil {
	private static Logger logger = Logger.getLogger(JobUtil.class);
	
	// http://10.210.60.111:6061/dispatch/job
	// http://10.213.24.17:5051/dispatch/job
	private static HessianRemoteCall factory;
	private static IJob jobFactory;

	static {
		new Configure();
		String url = Configure.getProperty("job.url");
		factory = new HessianRemoteCall();
		try {
			jobFactory = (IJob) factory.getRemoteObject(url, IJob.class, null);
		} catch (Throwable t) {
			logger.error("获取接口失败", t);
		}
	}

	public static List<JobEvent> get() {
		List<JobEvent> jobs = new ArrayList<JobEvent>();
		try {
			String position = Configure.getProperty("executor.position");
			int count = Integer.valueOf(Configure
					.getProperty("executor.job.count"));
			jobs = jobFactory.dispatch(position, count);
		} catch (Throwable t) {
			logger.error("获取job失败", t);
			return jobs;
		}
		return jobs;
	}
}
