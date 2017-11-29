package com.yst.executor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yst.client.job.JobEvent;
import com.yst.executor.dao.IJobDao;
import com.yst.executor.factory.ITask;
import com.yst.executor.factory.TaskFactory;
import com.yst.executor.factory.TaskResult;
import com.yst.util.Configure;
import com.yst.util.JobUtil;

public class JobService {

	private static ExecutorService exec = Executors.newFixedThreadPool(1000);
	private static ExecutorService consumer = Executors
			.newSingleThreadExecutor();
	private static Logger logger = Logger.getLogger(JobService.class);
	private static ApplicationContext context;
	private static IJobDao jobDao;
	private static ITask task = new TaskFactory();
	private static ArrayList<Future<TaskResult>> results = new ArrayList<Future<TaskResult>>();
	private static List<JobEvent> jobs = new ArrayList<JobEvent>();

	static {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		jobDao = (IJobDao) context.getBean("jobDAO");
		consumer.execute(new Runnable() {
			public void run() {
				logger.info("启动工作提交线程");
				try {
					while (true) {
						if (jobs.size() == 0) {
							jobs = JobUtil.get();
							if (jobs.size() == 0) {
								logger.info("没有获取到job,休眠1妙");
								Thread.sleep(1000);
							} else {
								logger.info("获取到job");
							}
							continue;
						} else {
							logger.info("开始执行");
							for (int i = 0; i < jobs.size(); i++) {
								logger.info(String.format("提交%s", jobs.get(i)));
								logger.info("|——————————jobs.get(i)="+jobs.get(i));
								logger.info("|——————————task="+task);
								logger.info("|——————————jobs="+jobs);
								logger.info("|——————————exec="+exec);
								results.add(exec.submit(task.initTask(jobs
										.get(i))));
							}
							jobs.clear();
						}

						for (Future<TaskResult> fs : results) {
							TaskResult tr = null;
							try {
								tr = fs.get();
								if (tr != null) {
									logger.info(tr);
									if (tr.isSuccess()) {
										jobDao.success(String.valueOf(tr
												.getJob().getJobid()));
										logger.info(String.format("执行成功%s", tr));
									} else {
										jobDao.finish(String.valueOf(tr
												.getJob().getJobid()));
										logger.info(String.format("执行失败%s", tr));
									}
								} else {
								}
							} catch (Throwable t) {
								if (tr != null) {
									jobDao.finish(String.valueOf(tr.getJob()
											.getJobid()));
									logger.error("执行异常", t);
								} else {
									logger.error("执行异常,tr为null", t);
								}
							}
						}
						results.clear();
						logger.info("一轮执行结束,休眠3妙");
						Thread.sleep(Configure.getLong("executor.interval"));
						logger.info("开始下一轮");
					}
				} catch (Throwable t) {
					jobDao.end();
					logger.error("工作提交线程异常", t);
				}
			}
		});
	}

	public static void end() {
		logger.info("停止应用,所有job恢复为就绪状态!");
		jobDao.end();
	}

	public static void main(String[] Args) {

	}
}
