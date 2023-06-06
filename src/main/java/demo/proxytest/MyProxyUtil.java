package demo.proxytest;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: MyProxyUtil
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/6/5 15:34
 * @Version: 1.0
 */
public class MyProxyUtil {
    @Test
    public void testJDKProxy() {
        // 创建目标对象
        UserService userService = new UserServiceImpl();
        // 生成代理对象
        UserService proxy = MyProxyUtil.getProxyByJDK(userService);
        // 调用目标对象方法
        userService.saveUser();
        System.out.println("===================================");
        // 调用代理对象方法
        proxy.saveUser();
    }
    public static UserService getProxyByJDK(UserService service) {
        UserService userService = (UserService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("记录日志-开始");
                        Object object = method.invoke(service, args);
                        System.out.println("记录日志-结束");
                        return object;
                    }
                });
        return userService;
    }
}

