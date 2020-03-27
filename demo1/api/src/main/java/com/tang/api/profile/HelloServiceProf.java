package com.tang.api.profile;

import com.tang.param.billing.Iris;

import java.util.List;

/**
 * < ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/11 <br>
 */
public interface HelloServiceProf {

    /**
     * < > <br>
     *
     * @auther: tang.jian
     * @param name < >
     * @return < >
     */
    String sayHello(String name);

    List<Iris> irisTree(List<Iris> param);

}