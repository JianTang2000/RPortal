package com.tang.profile.demoprovider.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tang.api.billing.AuditThemeService;
import com.tang.api.profile.AcctService;
import com.tang.param.billing.AuditThemeParam;
import com.tang.profile.demoprovider.infrastrucrute.DAO.profile.IAcctDAO;

/**
 * < ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/13 <br>
 */
@Service("AcctService")
public class AcctServiceImpl implements AcctService {
    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(AcctServiceImpl.class);

    @Autowired
    private IAcctDAO acctDAO;

    @Autowired
    private AuditThemeService auditThemeService;

    @Override
    public Long querySizeOfTableAcct() throws Exception {
        return this.acctDAO.querySizeNumberOfTableAcct();
    }

    @Override
    public AuditThemeParam dbLinkTest(Long themeId) throws Exception {
        logger.debug("dbLinkTest started--------");
        AuditThemeParam param = new AuditThemeParam();
        param = this.auditThemeService.queryById(themeId);
        logger.debug("dbLinkTest end--------");
        return param;
    }
}
