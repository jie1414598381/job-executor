package com.yst.executor.factory;

import com.yst.client.job.JobEvent;

public class TaskResult {

	private JobEvent job;
	private boolean success;

	public JobEvent getJob() {
		return job;
	}

	public void setJob(JobEvent job) {
		this.job = job;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public TaskResult(JobEvent job, boolean success) {
		super();
		this.job = job;
		this.success = success;
	}

	public TaskResult() {
		super();
	}

	@Override
	public String toString() {
		return String.format("TaskResult [job=%s, success=%s]", job, success);
	}
}
