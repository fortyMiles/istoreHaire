/**
 * @Project istoreHaier
 * @Package com.istore.common.core.bean
 * @Title XChannel.java
 * @Description TODO
 * @CopyRight CopyRight (c) 2014
 * @Company 江苏太湖云计算信息技术股份有限公司
 *
 * @author mojilin
 * @date 2014-7-9
 * @email mojilin@wuxicloud.com
 * @version V1.0
 */
package com.istore.common.core.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @ClassName: XChannel.java
 * @Description: TODO
 * @author mojilin
 * @time 2014-7-9上午10:11:11
 */
@SuppressWarnings("serial")
public class XChannel implements Serializable {
	private long xchannel_id;
	private String xchannel_name;
	private long store_id;
	private Timestamp createtime;
	private long author;
	private Timestamp lastupdate;
	private long markfordelete;
	private long seq;
	private String type;

	public long getXchannel_id() {
		return xchannel_id;
	}

	public void setXchannel_id(long xchannel_id) {
		this.xchannel_id = xchannel_id;
	}

	public String getXchannel_name() {
		return xchannel_name;
	}

	public void setXchannel_name(String xchannel_name) {
		this.xchannel_name = xchannel_name;
	}

	public long getStore_id() {
		return store_id;
	}

	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public long getAuthor() {
		return author;
	}

	public void setAuthor(long author) {
		this.author = author;
	}

	public Timestamp getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Timestamp lastupdate) {
		this.lastupdate = lastupdate;
	}

	public long getMarkfordelete() {
		return markfordelete;
	}

	public void setMarkfordelete(long markfordelete) {
		this.markfordelete = markfordelete;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
