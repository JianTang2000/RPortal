package com.tang.api.profile;

import com.tang.param.billing.AuditThemeParam;

/**
 * < ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/13 <br>
 */
public interface AcctService {

    /**
     * < 查询acct的所有记录数，用来测试db连接是否ok，由于这个表数据较大，大概有十万数据，后面可以用这个表来做些性能上面的测试 > <br>
     * 
     * @auther: tang.jian
     * @return < >
     */
    public Long querySizeOfTableAcct() throws Exception;

    /**
     *  < 这个方法仅仅单纯的调一下其他中心的接口，用来测试中心间dubbo调用是否OK > <br>
     *
     * @auther: tang.jian
     * @param themeId < >
     * @return < >
     *
     */
    public AuditThemeParam dbLinkTest(Long themeId) throws Exception;
}
