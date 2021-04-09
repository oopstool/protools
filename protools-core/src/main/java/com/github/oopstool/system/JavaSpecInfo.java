package com.github.oopstool.system;

import java.io.Serializable;

/**
 * 代表Java Specification的信息。
 *
 * @author houGY
 * @since 1.0.3
 */
public class JavaSpecInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	private final String JAVA_SPECIFICATION_NAME = SystemUtils.get("java.specification.name", false);
	private final String JAVA_SPECIFICATION_VERSION = SystemUtils.get("java.specification.version", false);
	private final String JAVA_SPECIFICATION_VENDOR = SystemUtils.get("java.specification.vendor", false);

	private static class JavaSpecInfoHolder{
		private static final JavaSpecInfo JAVA_SPEC_INFO = new JavaSpecInfo();
	}

	private JavaSpecInfo() {
	}

	public static JavaSpecInfo getInstance(){
		return  JavaSpecInfoHolder.JAVA_SPEC_INFO;
	}

	/**
	 * 取得当前Java Spec.的名称（取自系统属性：<code>java.specification.name</code>）。
	 * 
	 * <p>
	 * 例如Sun JDK 1.4.2：<code>"Java Platform API Specification"</code>
	 * </p>
	 * 
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>。
	 * 
	 */
	public final String getName() {
		return JAVA_SPECIFICATION_NAME;
	}

	/**
	 * 取得当前Java Spec.的版本（取自系统属性：<code>java.specification.version</code>）。
	 * 
	 * <p>
	 * 例如Sun JDK 1.4.2：<code>"1.4"</code>
	 * </p>
	 * 
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>。
	 * 
	 * @since Java 1.3
	 */
	public final String getVersion() {
		return JAVA_SPECIFICATION_VERSION;
	}

	/**
	 * 取得当前Java Spec.的厂商（取自系统属性：<code>java.specification.vendor</code>）。
	 * 
	 * <p>
	 * 例如Sun JDK 1.4.2：<code>"Sun Microsystems Inc."</code>
	 * </p>
	 * 
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>。
	 * 
	 */
	public final String getVendor() {
		return JAVA_SPECIFICATION_VENDOR;
	}

	/**
	 * 将Java Specification的信息转换成字符串。
	 * 
	 * @return JVM spec.的字符串表示
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();

		SystemUtils.append(builder, "Java Spec. Name:    ", getName());
		SystemUtils.append(builder, "Java Spec. Version: ", getVersion());
		SystemUtils.append(builder, "Java Spec. Vendor:  ", getVendor());

		return builder.toString();
	}

}