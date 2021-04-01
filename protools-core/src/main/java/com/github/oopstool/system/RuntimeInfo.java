package com.github.oopstool.system;

import com.github.oopstool.file.DataSizeUtil;

import java.io.Serializable;

/**
 * 运行时信息，包括内存总大小、已用大小、可用大小等
 *
 * @author houGY
 * @since 1.0.4
 */
public class RuntimeInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Runtime currentRuntime = Runtime.getRuntime();

	private static class RuntimeInfoHolder{
		private static final RuntimeInfo RUNTIME_INFO = new RuntimeInfo();
	}

	private RuntimeInfo() {
	}

	public static RuntimeInfo getInstance(){
		return  RuntimeInfoHolder.RUNTIME_INFO;
	}

	/**
	 * 获得运行时对象
	 *
	 * @return {@link Runtime}
	 */
	public final Runtime getRuntime() {
		return currentRuntime;
	}

	/**
	 * 获得JVM最大内存
	 *
	 * @return 最大内存
	 */
	public final long getMaxMemory() {
		return currentRuntime.maxMemory();
	}

	/**
	 * 获得JVM已分配内存
	 *
	 * @return 已分配内存
	 */
	public final long getTotalMemory() {
		return currentRuntime.totalMemory();
	}

	/**
	 * 获得JVM已分配内存中的剩余空间
	 *
	 * @return 已分配内存中的剩余空间
	 */
	public final long getFreeMemory() {
		return currentRuntime.freeMemory();
	}

	/**
	 * 获得JVM最大可用内存
	 *
	 * @return 最大可用内存
	 */
	public final long getUsableMemory() {
		return currentRuntime.maxMemory() - currentRuntime.totalMemory() + currentRuntime.freeMemory();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		SystemUtils.append(builder, "Max Memory:    ", DataSizeUtil.format(getMaxMemory()));
		SystemUtils.append(builder, "Total Memory:     ", DataSizeUtil.format(getTotalMemory()));
		SystemUtils.append(builder, "Free Memory:     ", DataSizeUtil.format(getFreeMemory()));
		SystemUtils.append(builder, "Usable Memory:     ", DataSizeUtil.format(getUsableMemory()));

		return builder.toString();
	}
}
