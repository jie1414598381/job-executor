package com.yst.util;

/**
 * 010-商圈-添加说说 011-商圈-添加评论 012-商圈-添加点赞
 * 
 * 013-商圈-删除说说 014-商圈-删除评论 015-商圈-删除点赞
 * 
 * 000-公众号-推送消息 分配方确认 001-商圈-推送通知 分配方确认 003-公众号－图文（文本）消息
 * 
 * 020-添加好友
 * 
 * @author Coci
 * 
 */
public enum JobTypeEnum {

	/**
	 * 000-公众号-推送消息
	 */
	GZHNOTICE("000"),
	/**
	 * 001-商圈-推送通知
	 */
	SQNOTICE("001"),
	/**
	 * 003-公众号－图文（文本）消息
	 */
	GZHPUSH("003"),

	/**
	 * 004微信公众号推送
	 */
	WECHATMSG("004"),

	/**
	 * 010-商圈-添加说说
	 */
	ADDSTATE("010"),
	/**
	 * 011-商圈-添加评论
	 */
	ADDCOMMENT("011"),
	/**
	 * 012-商圈-添加点赞
	 */
	ADDLIKE("012"),
	/**
	 * 013-商圈-删除说说
	 */
	DELETESTATE("013"),
	/**
	 * 014-商圈-删除评论
	 */
	DELETECOMMENT("014"),
	/**
	 * 015-商圈-删除点赞
	 */
	DELETELIKE("015"),
	/**
	 * 020-添加好友
	 */
	ADDFRIEND("020"),
	/**
	 * 021删除好友
	 */
	DELETEFRIEND("021");

	private String key;

	private JobTypeEnum(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return this.key;
	}
}
