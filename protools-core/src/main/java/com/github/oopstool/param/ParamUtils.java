package com.github.oopstool.param;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

/**
 * 基于guava Preconditions参数校验相关工具
 *
 * @author : HouGY
 * @since : 1.0.0
 */
public class ParamUtils {

    /**
     * <p>
     * 根据传入的表达式判断结果是否为true，如果表达式为false抛出异常 例：<br>
     * <li>{@code  String a = "a"; ParamUtils.checkArgument(a.length()==2);   returns throw IllegalArgumentException}</li>
     * <li>{@code  String a = "a"; ParamUtils.checkArgument(a.length()==1);  }</li>
     * </p>
     *
     * <p>
     * 注意：该方法与 {@link #checkArgument(boolean, Object)} 的区别是：该方法仅仅抛出异常。
     * </p>
     *
     * @param expression 表达式
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression) {
        Preconditions.checkArgument(expression);
    }

    /**
     * <p>
     * 根据传入的表达式判断结果是否为true，如果表达式为false抛出异常 例：<br>
     * <li>{@code  String a = "a";
     * ParamUtils.checkArgument(a.length()==2,"a的长度必须等于2");
     * returns throw IllegalArgumentException}
     * </li>
     *
     * </p>
     *
     * <p>
     * 注意：该方法与 {@link #checkArgument(boolean)} 的区别是：该方法在抛出异常的同时会返回自定义的错误信息。
     * </p>
     *
     * @param expression   表达式
     * @param errorMessage 错误信息，建议使用String类型 具体实现是调用String.valueOf(errorMessage)
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, @Nullable Object errorMessage) {
        Preconditions.checkArgument(expression, errorMessage);
    }

    /**
     * <p>
     * 根据传入的表达式判断结果是否为true，如果表达式为false抛出异常 例：<br>
     * <li>
     * {@code Student student = new Student(1, "张三", 23, null);
     * ParamUtils.checkArgument(!StringUtils.isBlank(student.getSex()), "学生(id=%s,姓名=%s)的性别不能为空", student.getId(),
     * student.getName());
     * returns throw IllegalArgumentException}
     * </li>
     *
     * </p>
     *
     * <p>
     * 注意：该方法与 {@link #checkArgument(boolean, Object)} 的区别是：该方法支持通过模版自定义的错误信息。
     * </p>
     *
     * @param expression           表达式
     * @param errorMessageTemplate 错误信息模版 字符串占位符%s
     * @param errorMessageArgs     错误信息模版参数
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate,
        @Nullable Object... errorMessageArgs) {
        Preconditions.checkArgument(expression, errorMessageTemplate, errorMessageArgs);
    }

    /**
     * <p>
     * 这个方法是快速判断null并抛出异常，通过方法{@link #checkArgument(boolean)}也可以完成此功能<br>
     * <li>{@code
     * Student student = null;
     * ParamUtils.checkNotNull(student);
     * returns throw NullPointerException}
     * </li>
     *
     * </p>
     *
     * @param reference 泛型对象
     * @param <T>       指定的泛型
     * @return 如果为空则抛出NullPointerException
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference) {
        return Preconditions.checkNotNull(reference);
    }
    /**
     * <p>
     * 这个方法是快速判断null并抛出异常和自定义的异常信息，通过方法{@link #checkArgument(boolean,Object)}也可以完成此功能<br>
     * <li>{@code
     * Student student = null;
     * ParamUtils.checkNotNull(student);
     * returns throw NullPointerException}
     * </li>
     *
     * </p>
     *
     * @param reference 泛型对象
     * @param errorMessage 错误信息
     * @param <T>       指定的泛型
     * @return 如果为空则抛出NullPointerException
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        return Preconditions.checkNotNull(reference, errorMessage);
    }

    /**
     * <p>
     * 这个方法是快速判断null并抛出异常支持异常信息模版。
     * <li>{@code
     * Student student = null;
     * ParamUtils.checkNotNull(student,"%s不能为空","学生信息");
     * returns throw NullPointerException}
     * </li>
     *
     * </p>
     *
     * @param reference 泛型对象
     * @param errorMessageTemplate 错误信息模版
     * @param errorMessageArgs 模版参数
     * @param <T>       指定的泛型
     * @return 如果为空则抛出NullPointerException
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference,
        @Nullable String errorMessageTemplate,
        @Nullable Object... errorMessageArgs) {
        return Preconditions.checkNotNull(reference,errorMessageTemplate,errorMessageArgs);
    }
}
