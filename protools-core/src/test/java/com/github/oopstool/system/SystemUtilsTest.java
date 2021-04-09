package com.github.oopstool.system;

import com.github.oopstool.json.Student;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.Properties;
import org.junit.Assert;
import org.junit.Test;

public class SystemUtilsTest {

    @Test
    public void get() {
        String s = SystemUtils.get(SystemPropsKeys.CLASS_VERSION, false);
        Assert.assertNotNull(s);
        System.out.println(s);
        String s1 = SystemUtils.get(SystemPropsKeys.CLASS_VERSION);
        Assert.assertNotNull(s1);
        System.out.println(s1);
        String s2 = SystemUtils.get(SystemPropsKeys.CLASS_VERSION, "214");
        Assert.assertNotNull(s2);
        System.out.println(s2);
        String s3 = SystemUtils.get("21434", "214");
        Assert.assertEquals(s3, "214");
        System.out.println(s3);

    }


    @Test
    public void props() {
        Properties props = SystemUtils.props();
        Assert.assertNotNull(props);
        System.out.println(props);
    }

    @Test
    public void getCurrentPID() {
        long currentPID = SystemUtils.getCurrentPID();
        System.out.println(currentPID);
    }

    @Test
    public void getClassLoadingMXBean() {
        ClassLoadingMXBean classLoadingMXBean = SystemUtils.getClassLoadingMXBean();
        classLoadingMXBean.getLoadedClassCount();
        System.out.println(classLoadingMXBean);
    }

    @Test
    public void getMemoryMXBean() {
        MemoryMXBean memoryMXBean = SystemUtils.getMemoryMXBean();
        Assert.assertNotNull(memoryMXBean);
    }

    @Test
    public void getThreadMXBean() {
        ThreadMXBean threadMXBean = SystemUtils.getThreadMXBean();
        Assert.assertNotNull(threadMXBean);
    }

    @Test
    public void getRuntimeMXBean() {
        RuntimeMXBean runtimeMXBean = SystemUtils.getRuntimeMXBean();
        Assert.assertNotNull(runtimeMXBean);
    }

    @Test
    public void getCompilationMXBean() {
        CompilationMXBean compilationMXBean = SystemUtils.getCompilationMXBean();
        Assert.assertNotNull(compilationMXBean);
    }

    @Test
    public void getOperatingSystemMXBean() {
        OperatingSystemMXBean operatingSystemMXBean = SystemUtils.getOperatingSystemMXBean();
        Assert.assertNotNull(operatingSystemMXBean);
    }

    @Test
    public void getMemoryPoolMXBeans() {
        List<MemoryPoolMXBean> memoryPoolMXBeans = SystemUtils.getMemoryPoolMXBeans();
        Assert.assertNotNull(memoryPoolMXBeans);
    }

    @Test
    public void getMemoryManagerMXBeans() {
        List<MemoryManagerMXBean> memoryManagerMXBeans = SystemUtils.getMemoryManagerMXBeans();
        Assert.assertNotNull(memoryManagerMXBeans);
    }

    @Test
    public void getGarbageCollectorMXBeans() {
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = SystemUtils.getGarbageCollectorMXBeans();
        Assert.assertNotNull(garbageCollectorMXBeans);
    }

    @Test
    public void getJvmSpecInfo() {
        JvmSpecInfo jvmSpecInfo = SystemUtils.getJvmSpecInfo();
        Assert.assertNotNull(jvmSpecInfo);
    }

    @Test
    public void getJvmInfo() {
        JvmInfo jvmInfo = SystemUtils.getJvmInfo();
        Assert.assertNotNull(jvmInfo);
    }

    @Test
    public void getJavaSpecInfo() {
        JavaSpecInfo javaSpecInfo = SystemUtils.getJavaSpecInfo();
        System.out.println(javaSpecInfo);
        JavaSpecInfo javaSpecInfo1 = SystemUtils.getJavaSpecInfo();
        System.out.println(javaSpecInfo1);
        JavaSpecInfo javaSpecInfo2 = SystemUtils.getJavaSpecInfo();
        System.out.println(javaSpecInfo2);

    }

    @Test
    public void getJavaInfo() {
        JavaInfo javaInfo = SystemUtils.getJavaInfo();
        Assert.assertNotNull(javaInfo);
    }

    @Test
    public void getJavaRuntimeInfo() {
        JavaRuntimeInfo javaRuntimeInfo = SystemUtils.getJavaRuntimeInfo();
        Assert.assertNotNull(javaRuntimeInfo);
    }

    @Test
    public void getOsInfo() {
        OsInfo osInfo = SystemUtils.getOsInfo();
        Assert.assertNotNull(osInfo);
    }

    @Test
    public void getUserInfo() {
        UserInfo userInfo = SystemUtils.getUserInfo();
        Assert.assertNotNull(userInfo);
    }

    @Test
    public void getHostInfo() {
        HostInfo hostInfo = SystemUtils.getHostInfo();
        Assert.assertNotNull(hostInfo);
    }

    @Test
    public void getRuntimeInfo() {
        RuntimeInfo runtimeInfo = SystemUtils.getRuntimeInfo();
        Assert.assertNotNull(runtimeInfo);
    }

    @Test
    public void getTotalMemory() {
        long totalMemory = SystemUtils.getTotalMemory();
        System.out.println(totalMemory);
    }

    @Test
    public void getFreeMemory() {
        long freeMemory = SystemUtils.getFreeMemory();
        Assert.assertNotNull(freeMemory);
    }

    @Test
    public void getMaxMemory() {
        long maxMemory = SystemUtils.getMaxMemory();
        Assert.assertNotNull(maxMemory);
    }

    @Test
    public void getTotalThreadCount() {
        int totalThreadCount = SystemUtils.getTotalThreadCount();
        Assert.assertNotNull(totalThreadCount);
    }

    @Test
    public void testDumpSystemInfo() {
        SystemUtils.dumpSystemInfo();
    }

    @Test
    public void append() {
        StringBuilder stringBuilder = new StringBuilder();
        SystemUtils.append(stringBuilder, "DSR", new Student(1, "22", 23, "324"));
        System.out.println(stringBuilder.toString());
    }
}