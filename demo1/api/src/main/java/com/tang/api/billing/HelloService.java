package com.tang.api.billing;

/**
 * < ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/11 <br>
 */
public interface HelloService {

    /**
     * < 打印一个显示信息 > <br>
     * 
     * @auther: tang.jian
     * @param name < >
     * @return < >
     */
    String sayHello(String name);

    /**
     * < 调其他业务中心的某个接口，接口里面打印了一个显示信息 > <br>
     * 
     * @auther: tang.jian
     * @param info < >
     * @throws Exception <br>
     */
    void testProfileLink(String info) throws Exception;

    /**
     * < 注入调用Repository > <br>
     *
     * @auther: tang.jian
     * @param info < >
     * @throws Exception <br>
     */
    void testRepository(String info) throws Exception;

}