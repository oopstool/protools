package com.github.oopstool.system;

import java.io.Serializable;

/**
 * 代表Java Virutal Machine Specification的信息。
 *
 * @author houGY
 * @since 1.0.4
 */
public class JvmSpecInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	private final String JAVA_VM_SPECIFICATION_NAME = SystemUtils.get("java.vm.specification.name", false);
	private final String JAVA_VM_SPECIFICATION_VERSION = SystemUtils.get("java.vm.specification.version", false);
	private final String JAVA_VM_SPECIFICATION_VENDOR = SystemUtils.get("java.vm.specification.vendor", false);

	private static class JvmSpecInfoHolder{
		private static final JvmSpecInfo JVM_SPEC_INFO= new JvmSpecInfo();
	}

	private JvmSpecInfo() {
	}

	public static JvmSpecInfo getInstance(){
		return  JvmSpecInfoHolder.JVM_SPEC_INFO;
	}

	/**
	 * 取得当前JVM spec.的名称（取自系统属性：<code>java.vm.specification.name</code>）。
	 * 
	 * <p>
	 * 例如Sun JDK 1.4.2：<code>"Java Virtual Machine Specification"</code>
	 * </p>
	 * 
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>。
	 * 
	 */
	public final String getName() {
		return JAVA_VM_SPECIFICATION_NAME;
	}

	/**
	 * 取得当前JVM spec.的版本（取自系统属性：<code>java.vm.specification.version</code>）。
	 * 
	 * <p>
	 * 例如Sun JDK 1.4.2：<code>"1.0"</code>
	 * </p>
	 * 
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>。
	 * 
	 */
	public final String getVersion() {
		return JAVA_VM_SPECIFICATION_VERSION;
	}

	/**
	 * 取得当前JVM spec.的厂商（取自系统属性：<code>java.vm.specification.vendor</code>）。
	 * 
	 * <p>
	 * 例如Sun JDK 1.4.2：<code>"Sun Microsystems Inc."</code>
	 * </p>
	 * 
	 * @return 属性值，如果不能取得（因为Java安全限制）或值不存在，则返回<code>null</code>。
	 * 
	 */
	public final String getVendor() {
		return JAVA_VM_SPECIFICATION_VENDOR;
	}

	/**
	 * 将Java Virutal Machine Specification的信息转换成字符串。
	 * 
	 * @return JVM spec.的字符串表示
	 */
	@Override
	public final String toString() {
		StringBuilder builder = new StringBuilder();

		SystemUtils.append(builder, "JavaVM Spec. Name:    ", getName());
		SystemUtils.append(builder, "JavaVM Spec. Version: ", getVersion());
		SystemUtils.append(builder, "JavaVM Spec. Vendor:  ", getVendor());

		return builder.toString();
	}

}
