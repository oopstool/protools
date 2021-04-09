package com.github.oopstool.system;


import com.github.oopstool.string.StringUtils;
import java.io.PrintWriter;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.Properties;

/**
 * Java的System类封装工具类。<br>
 * 参考：http://blog.csdn.net/zhongweijian/article/details/7619383
 * 来源hutool,进行了部分修改
 *
 * @author houGY
 * @since 1.0.3
 */
public class SystemUtils {

    /**
     * 取得系统属性，如果因为Java安全的限制而失败，则将错误打在Log中，然后返回 defaultValue
     *
     * @param name         属性名
     * @param defaultValue 默认值
     * @return 属性值或defaultValue
     * @see System#getProperty(String)
     * @see System#getenv(String)
     */
    public static String get(String name, String defaultValue) {
        return StringUtils.nullToDefault(get(name, false), defaultValue);
    }

    /**
     * 取得系统属性，如果因为Java安全的限制而失败，则将错误打在Log中，然后返回 {@code null}
     *
     * @param name  属性名
     * @param quiet 安静模式，不将出错信息打在<code>System.err</code>中
     * @return 属性值或{@code null}
     * @see System#getProperty(String)
     * @see System#getenv(String)
     */
    public static String get(String name, boolean quiet) {
        String value = null;
        try {
            value = System.getProperty(name);
        } catch (SecurityException e) {
            if (false == quiet) {
                System.err.println(StringUtils.format("Caught a SecurityException reading the system property %s; " +
                    "the SystemUtil property value will default to null.", name));
            }
        }

        if (null == value) {
            try {
                value = System.getenv(name);
            } catch (SecurityException e) {
                if
                (false == quiet) {
                    System.err.println(StringUtils.format("Caught a SecurityException reading the system env %s; " +
                        "the SystemUtil env value will default to null.", name));
                }
            }
        }

        return value;
    }

    /**
     * 获得System属性
     *
     * @param key 键
     * @return 属性值
     * @see System#getProperty(String)
     * @see System#getenv(String)
     */
    public static String get(String key) {
        return get(key, null);
    }

    /*	*//**
     * 获得boolean类型值
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     *//*
	public static boolean getBoolean(String key, boolean defaultValue) {
		String value = get(key);
		if (value == null) {
			return defaultValue;
		}

		value = value.trim().toLowerCase();
		if (value.isEmpty()) {
			return true;
		}

		return Convert.toBool(value, defaultValue);
	}

	*//**
     * 获得int类型值
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     *//*
	public static long getInt(String key, int defaultValue) {
		return Convert.toInt(get(key), defaultValue);
	}

	*//**
     * 获得long类型值
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 值
     *//*
	public static long getLong(String key, long defaultValue) {
		return Convert.toLong(get(key), defaultValue);
	}*/

    /**
     * @return 属性列表
     */
    public static Properties props() {
        return System.getProperties();
    }

    /**
     * 获取当前进程 PID
     *
     * @return 当前进程 ID
     */
    public static long getCurrentPID() {
        return Long.parseLong(getRuntimeMXBean().getName().split("@")[0]);
    }

    /**
     * 返回Java虚拟机类加载系统相关属性
     *
     * @return {@link ClassLoadingMXBean}
     */
    public static ClassLoadingMXBean getClassLoadingMXBean() {
        return ManagementFactory.getClassLoadingMXBean();
    }

    /**
     * 返回Java虚拟机内存系统相关属性
     *
     * @return {@link MemoryMXBean}
     */
    public static MemoryMXBean getMemoryMXBean() {
        return ManagementFactory.getMemoryMXBean();
    }

    /**
     * 返回Java虚拟机线程系统相关属性
     *
     * @return {@link ThreadMXBean}
     */
    public static ThreadMXBean getThreadMXBean() {
        return ManagementFactory.getThreadMXBean();
    }

    /**
     * 返回Java虚拟机运行时系统相关属性
     *
     * @return {@link RuntimeMXBean}
     */
    public static RuntimeMXBean getRuntimeMXBean() {
        return ManagementFactory.getRuntimeMXBean();
    }

    /**
     * 返回Java虚拟机编译系统相关属性<br>
     * 如果没有编译系统，则返回<code>null</code>
     *
     * @return a {@link CompilationMXBean} ，如果没有编译系统，则返回<code>null</code>
     */
    public static CompilationMXBean getCompilationMXBean() {
        return ManagementFactory.getCompilationMXBean();
    }

    /**
     * 返回Java虚拟机运行下的操作系统相关信息属性
     *
     * @return {@link OperatingSystemMXBean}
     */
    public static OperatingSystemMXBean getOperatingSystemMXBean() {
        return ManagementFactory.getOperatingSystemMXBean();
    }

    /**
     * 获取Java虚拟机中的{@link MemoryPoolMXBean}列表<br>
     * The Java virtual machine can have one or more memory pools. It may add or remove memory pools during execution.
     *
     * @return a list of <tt>MemoryPoolMXBean</tt> objects.
     */
    public static List<MemoryPoolMXBean> getMemoryPoolMXBeans() {
        return ManagementFactory.getMemoryPoolMXBeans();
    }

    /**
     * 获取Java虚拟机中的{@link MemoryManagerMXBean}列表<br>
     * The Java virtual machine can have one or more memory managers. It may add or remove memory managers during execution.
     *
     * @return a list of <tt>MemoryManagerMXBean</tt> objects.
     */
    public static List<MemoryManagerMXBean> getMemoryManagerMXBeans() {
        return ManagementFactory.getMemoryManagerMXBeans();
    }

    /**
     * 获取Java虚拟机中的{@link GarbageCollectorMXBean}列表
     *
     * @return {@link GarbageCollectorMXBean}列表
     */
    public static List<GarbageCollectorMXBean> getGarbageCollectorMXBeans() {
        return ManagementFactory.getGarbageCollectorMXBeans();
    }

    /**
     * 取得Java Virtual Machine Specification的信息。
     *
     * @return <code>JvmSpecInfo</code>对象
     */
    public static JvmSpecInfo getJvmSpecInfo() {
        return JvmSpecInfo.getInstance();
    }

    /**
     * 取得Java Virtual Machine Implementation的信息。
     *
     * @return <code>JvmInfo</code>对象
     */
    public static JvmInfo getJvmInfo() {
        return JvmInfo.getInstance();
    }

    /**
     * 取得Java Specification的信息。
     *
     * @return <code>JavaSpecInfo</code>对象
     */
    public static JavaSpecInfo getJavaSpecInfo() {
        return JavaSpecInfo.getInstance();
    }

    /**
     * 取得Java Implementation的信息。
     *
     * @return <code>JavaInfo</code>对象
     */
    public static JavaInfo getJavaInfo() {
        return JavaInfo.getInstance();
    }

    /**
     * 取得当前运行的JRE的信息。
     *
     * @return <code>JreInfo</code>对象
     */
    public static JavaRuntimeInfo getJavaRuntimeInfo() {
        return JavaRuntimeInfo.getInstance();
    }

    /**
     * 取得OS的信息。
     *
     * @return <code>OsInfo</code>对象
     */
    public static OsInfo getOsInfo() {
        return OsInfo.getInstance();
    }

    /**
     * 取得User的信息。
     *
     * @return <code>UserInfo</code>对象
     */
    public static UserInfo getUserInfo() {
        return UserInfo.getInstance();
    }

    /**
     * 取得Host的信息。
     *
     * @return <code>HostInfo</code>对象
     */
    public static HostInfo getHostInfo() {
        return HostInfo.getInstance();
    }

    /**
     * 取得Runtime的信息。
     *
     * @return <code>RuntimeInfo</code>对象
     */
    public static RuntimeInfo getRuntimeInfo() {
        return RuntimeInfo.getInstance();
    }

    /**
     * 获取JVM中内存总大小
     *
     * @return 内存总大小
     * @since 4.5.4
     */
    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 获取JVM中内存剩余大小
     *
     * @return 内存剩余大小
     * @since 4.5.4
     */
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 获取JVM可用的内存总大小
     *
     * @return JVM可用的内存总大小
     * @since 4.5.4
     */
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 获取总线程数
     *
     * @return 总线程数
     */
    public static int getTotalThreadCount() {
        ThreadGroup parentThread = Thread.currentThread().getThreadGroup();
        while (null != parentThread.getParent()) {
            parentThread = parentThread.getParent();
        }
        return parentThread.activeCount();
    }

    // ------------------------------------------------------------------ Dump

    /**
     * 将系统信息输出到<code>System.out</code>中。
     */
    public static void dumpSystemInfo() {
        dumpSystemInfo(new PrintWriter(System.out));
    }

    /**
     * 将系统信息输出到指定<code>PrintWriter</code>中。
     *
     * @param out <code>PrintWriter</code>输出流
     */
    public static void dumpSystemInfo(PrintWriter out) {
        out.println("--------------");
        out.println(getJvmSpecInfo());
        out.println("--------------");
        out.println(getJvmInfo());
        out.println("--------------");
        out.println(getJavaSpecInfo());
        out.println("--------------");
        out.println(getJavaInfo());
        out.println("--------------");
        out.println(getJavaRuntimeInfo());
        out.println("--------------");
        out.println(getOsInfo());
        out.println("--------------");
        out.println(getUserInfo());
        out.println("--------------");
        out.println(getHostInfo());
        out.println("--------------");
        out.println(getRuntimeInfo());
        out.println("--------------");
        out.flush();
    }

    /**
     * 输出到<code>StringBuilder</code>。
     *
     * @param builder <code>StringBuilder</code>对象
     * @param caption 标题
     * @param value   值
     */
    protected static void append(StringBuilder builder, String caption, Object value) {
        builder.append(caption).append(StringUtils.nullToDefault(String.valueOf(value), "[n/a]")).append("\n");
    }
}
