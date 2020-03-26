package com.tang.billing.demoprovider.domain;

/**
 * < 这个一个使用@Repository注解的接口demo，
 * 本项目中，DAO的实现由MyBatis管理，所以这类@Repository注解的接口没有用来实现DAO，
 * 拟用来作为从缓存（如后续会实现的进程内缓存，redis等）中取数据的，业务中心内部自由调用的接口
 * 会提供几个用于测试@Repository的方法
 * 在本中心内部，注入其他bean,被其他类注入，等等><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/19 <br>
 */
public interface AcctRepository {

    /**
     *  < 注入其他服务的bean，展示info > <br>
     *
     * @auther: tang.jian
     * @param info < 待展示的信息 >
     *
     */
    public void showHelloInfo(String info);

}
