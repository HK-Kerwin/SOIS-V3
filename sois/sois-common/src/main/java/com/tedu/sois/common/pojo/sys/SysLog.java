package com.tedu.sois.common.pojo.sys;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * pojo:(Plain Ordinary Java Object) 简单java对象:属性/set/get
 * 1)po(persistant object) 持久化对象:与表中字段有一一映射关系
 * 2)vo(Value Object) 值对象:负责每层对象数据值的封装和传递
 */
public class SysLog implements Serializable{
	private static final long serialVersionUID = 8924387722922123121L;
	private Long logId;
	/**用户名*/
	private String userName;
	/**用户操作*/
	private String operation;
	/**请求方法*/
	private String method;
	/**请求参数*/
	private String params;
	/**执行时长(毫秒)*/
	private Long millisecond;
	/**IP地址*/
	private String ip;
	/**IP对应地址*/
	private String address;
	/**IP对应网络服务提供者*/
	private String isp;
	/**创建时间*/
	private Date createdTime;

	public SysLog() {
	}

	public SysLog(Long logId, String userName, String operation, String method, String params, Long millisecond, String ip, String address, String isp, Date createdTime) {
		this.logId = logId;
		this.userName = userName;
		this.operation = operation;
		this.method = method;
		this.params = params;
		this.millisecond = millisecond;
		this.ip = ip;
		this.address = address;
		this.isp = isp;
		this.createdTime = createdTime;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Long getMillisecond() {
		return millisecond;
	}

	public void setMillisecond(Long millisecond) {
		this.millisecond = millisecond;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SysLog sysLog = (SysLog) o;
		return Objects.equals(logId, sysLog.logId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(logId);
	}

	@Override
	public String toString() {
		return "SysLog{" +
				"logId=" + logId +
				", userName='" + userName + '\'' +
				", operation='" + operation + '\'' +
				", method='" + method + '\'' +
				", params='" + params + '\'' +
				", millisecond=" + millisecond +
				", ip='" + ip + '\'' +
				", address='" + address + '\'' +
				", isp='" + isp + '\'' +
				", createdTime=" + createdTime +
				'}';
	}
}
