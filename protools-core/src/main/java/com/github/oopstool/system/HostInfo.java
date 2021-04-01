package com.github.oopstool.system;


import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 代表当前主机的信息。
 *
 * @author houGY
 * @since 1.0.4
 */
public class HostInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private  String HOST_NAME;
	private  String HOST_ADDRESS;

	private static class HostInfoHolder{
		private static final HostInfo HOST_INFO = new HostInfo();
	}

	public static HostInfo getInstance(){
		return  HostInfo.HostInfoHolder.HOST_INFO;
	}


	private HostInfo()  {
		InetAddress localhost ;
		try {
			localhost = InetAddress.getLocalHost();
			if(null != localhost){
				HOST_NAME = localhost.getHostName();
				HOST_ADDRESS = localhost.getHostAddress();
			} else{
				HOST_NAME = null;
				HOST_ADDRESS = null;
			}
		} catch (UnknownHostException e) {
		}


	}

	/**
	 * 取得当前主机的名称。
	 *
	 * <p>
	 * 例如：<code>"webserver1"</code>
	 * </p>
	 *
	 * @return 主机名
	 */
	public final String getName() {
		return HOST_NAME;
	}

	/**
	 * 取得当前主机的地址。
	 *
	 * <p>
	 * 例如：<code>"192.168.0.1"</code>
	 * </p>
	 *
	 * @return 主机地址
	 */
	public final String getAddress() {
		return HOST_ADDRESS;
	}

	/**
	 * 将当前主机的信息转换成字符串。
	 *
	 * @return 主机信息的字符串表示
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();

		SystemUtils.append(builder, "Host Name:    ", getName());
		SystemUtils.append(builder, "Host Address: ", getAddress());

		return builder.toString();
	}


}
