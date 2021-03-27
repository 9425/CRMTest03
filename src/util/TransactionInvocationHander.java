package util;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//这个类的作用是对代理类功能的增强，也就是所谓的放入张三得到李四
public class TransactionInvocationHander implements InvocationHandler {
    private Object target;

    public TransactionInvocationHander() {
    }
    public TransactionInvocationHander(Object target) {
        this.target = target;
    }

    //所谓的放入张三得到李四原来如此

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //上面的Object proxy,Method method,Object[] args由jdk自动提供，只要写用就行
        SqlSession session=null;
        Object obj=null;
        try {
            //使用SqlSessionUtil工具进行获取session
            session=SqlSessionUtil.getSession();
            //处理业务逻辑
            //method.invoke  此处为功能增强，也就是所谓的李四的表白方法
            obj=method.invoke(target,args);
            //处理完业务逻辑之后，需要进行提交
            SqlSessionUtil.myCommit(session);
        }catch (Exception e){
            session.rollback();
            e.printStackTrace();
        }finally {
            session.commit();
            SqlSessionUtil.myClose(session);
        }
        return obj;
    }

    //创建代理对象
    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }
}
