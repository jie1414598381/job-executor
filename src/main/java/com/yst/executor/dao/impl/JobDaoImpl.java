package com.yst.executor.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.yst.executor.dao.IJobDao;
import com.yst.executor.domain.Job;
import com.yst.util.Configure;

@Component("jobDAO")
public class JobDaoImpl implements IJobDao {

	@Override
	public Job get(String jobid) {
		return htOracle.get(Job.class, jobid);
	}

	@Override
	public void finish(String jobid) {
		Job temp = htOracle.get(Job.class, Long.parseLong(jobid));
		if (temp != null) {
			// 00-就绪；10-正在运行；11-成功 ;99－失败
			if (temp.getJobtype().equals("000") && temp.getTimes() >= 3) {
				temp.setStatus("11");
				temp.setTimes(temp.getTimes() + 1);
				return;
			}
			if (temp.getJobtype().equals("003") && temp.getTimes() >= 3) {
				temp.setStatus("99");
				temp.setTimes(temp.getTimes() + 1);
				return;
			}
			if (temp.getTimes() >= 10) {
				temp.setStatus("99");
				temp.setTimes(temp.getTimes() + 1);
				return;
			}
			temp.setStatus("00");
			temp.setTimes(temp.getTimes() + 1);
		}
	}

	@Override
	public void success(String jobid) {
		Job temp = htOracle.get(Job.class, Long.parseLong(jobid));
	}

	@Override
	public void end() {
		String position = Configure.getProperty("executor.position");
		List<Job> jobs = (List<Job>) htOracle
				.find("select job from Job job where job.status = '10' and job.position = ?",
						position);
		if (jobs.size() == 0) {
			return;
		}

		for (int i = 0; i < jobs.size(); i++) {
			Job job = jobs.get(i);
			job.setStatus("00");
			htOracle.update(job);
		}
	}

	@Autowired
	private HibernateTemplate htOracle;

	public void setHibernateTemplate(HibernateTemplate ht) {
		this.htOracle = ht;
	}

}
