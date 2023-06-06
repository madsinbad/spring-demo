package demo.proxytest;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author: weizhengbo
 * @Date: 2023/6/5 15:30
 * @Version: 1.0
 */
public class UserServiceImpl implements UserService{
    @Override
    public void saveUser() {
        System.out.println("调用saveUser方法");
    }
}
