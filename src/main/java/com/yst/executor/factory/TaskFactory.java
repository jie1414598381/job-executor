package com.yst.executor.factory;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.yst.client.job.JobEvent;

public class TaskFactory implements ITask {

	private static Logger logger = Logger.getLogger(TaskFactory.class);

	@Override
	public Callable<TaskResult> initTask(JobEvent job) {

		logger.info("|——————————————TaskFactory  job=" + job + "====jobType="
				+ job.getJobtype());
		return null;
//		if (job.getJobtype().equals(JobTypeEnum.GZHNOTICE.toString())) { // 000
//
//			return new GzhMessageTask(job);// 公众号消息推送
//		} else if (job.getJobtype().equals(JobTypeEnum.SQNOTICE.toString())) { // 001
//			return new NoticeTask(job); // 商圈消息推送
//		} else if (job.getJobtype().equals(JobTypeEnum.GZHPUSH.toString())) { // 003
//																				// JobTypeEnum.GZHPUSH
//
//			return new GzhPushTask(job); // 图文或文本推送
//		} else if (job.getJobtype().equals(JobTypeEnum.WECHATMSG.toString())) { // 004
//
//			return new WechatMessageTask(job);// 微信公众号消息推送
//		} else if (job.getJobtype().equals(JobTypeEnum.ADDSTATE.toString())) { // 010
//
//			return new StateAddTask(job); // 添加说说
//		} else if (job.getJobtype().equals(JobTypeEnum.ADDCOMMENT.toString())) { // 011
//
//			return new CommentTask(job); // 添加评论
//		} else if (job.getJobtype().equals(JobTypeEnum.ADDLIKE.toString())) { // 012
//
//			return new LikeTask(job); // 添加点赞
//		} else if (job.getJobtype().equals(JobTypeEnum.DELETESTATE.toString())) { // 013
//
//			return new StateDeleteTask(job);// 删除说说 3.9不做
//		} else if (job.getJobtype().equals(JobTypeEnum.DELETELIKE.toString())) { // 015
//
//			return new LikeCancelTask(job); // 删除点赞
//		} else if (job.getJobtype().equals(JobTypeEnum.ADDFRIEND.toString())) { // 020
//
//			return new UserFriendTask(job); // 添加好友
//		} else if (job.getJobtype()
//				.equals(JobTypeEnum.DELETECOMMENT.toString())) { // 014
//
//			return new CommentDeleteTask(job);// 删除评论
//		} else if (job.getJobtype().equals(JobTypeEnum.DELETEFRIEND.toString())) { // 021
//
//			return new UserFriendDeleteTask(job); // 删除好友
//		} else {
//			return null;
//		}
	};
}
