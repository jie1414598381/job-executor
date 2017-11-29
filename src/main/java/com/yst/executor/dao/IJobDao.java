package com.yst.executor.dao;

import com.yst.executor.domain.Job;

public interface IJobDao {

	public Job get(String jobid);

	public void finish(String jobid);
	

	public void success(String jobid);

	public void end();
}
