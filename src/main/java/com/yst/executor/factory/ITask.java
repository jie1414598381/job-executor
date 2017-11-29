package com.yst.executor.factory;

import java.util.concurrent.Callable;

import com.yst.client.job.JobEvent;

public interface ITask {

	public Callable<TaskResult> initTask(JobEvent event);
}
