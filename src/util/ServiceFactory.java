package util;

import org.apache.ibatis.session.TransactionIsolationLevel;


//这一个类的作用是传入需要进行代理的服务对象，然后进行代理对象的创建
public class ServiceFactory {
    public static Object getService(Object service){
        //传递张三对象，然后进行功能增强之后，得到想要的李四对象
        return new TransactionInvocationHander(service).getProxy();

        //通过上面一行的代码，我们即可得到功能增强之后的对象
    }
}
