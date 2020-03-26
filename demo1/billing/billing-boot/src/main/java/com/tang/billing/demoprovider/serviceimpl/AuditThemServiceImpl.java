package com.tang.billing.demoprovider.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tang.api.billing.AuditThemeService;
import com.tang.base.util.DateUtil;
import com.tang.billing.demoprovider.infrastrucrute.DAO.billing.IAuditThemeDAO;
import com.tang.billing.demoprovider.infrastrucrute.model.AuditThemeDto;
import com.tang.param.billing.AuditThemeParam;

/**
 * < ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/11 <br>
 */
@Service("AuditThemeService")
public class AuditThemServiceImpl implements AuditThemeService {

    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(AuditThemServiceImpl.class);

    /**
     * < >
     */
    @Autowired
    IAuditThemeDAO auditThemeDAO;

    @Override
    public List<AuditThemeParam> queryAllTheme() throws Exception {
        logger.debug("queryAllTheme started......");
        List<AuditThemeParam> paramList = new ArrayList<>();
        List<AuditThemeDto> dtos = auditThemeDAO.selectAllTheme();
        for (AuditThemeDto dto : dtos) {
            AuditThemeParam param = new AuditThemeParam();
            BeanUtils.copyProperties(dto, param, AuditThemeParam.class);
            paramList.add(param);
        }
        logger.debug("queryAllTheme end, size of result is:" + paramList.size() + "......");
        return paramList;
    }

    @Override
    public AuditThemeParam queryById(Long themeId) throws Exception {
        logger.debug("queryById started......");
        AuditThemeDto dto = new AuditThemeDto();
        AuditThemeParam param = new AuditThemeParam();
        dto = auditThemeDAO.selectById(themeId);
        BeanUtils.copyProperties(dto, param, AuditThemeParam.class);
        logger.debug("queryById end......");
        return param;
    }

    @Override
    public void deleteById(Long themeId) {
        this.auditThemeDAO.deleteById(themeId);
    }

    @Override
    public AuditThemeParam createTheme(String themeName, String comments, Long themeType, Date createDate) {
        logger.info("createTheme start");
        logger.info("themeName:{}", themeName);
        logger.info("comments:{}", comments);
        logger.info("themeType:{}", themeType);
        logger.info("createDate:{}", createDate);
        AuditThemeDto dto = new AuditThemeDto();
        AuditThemeParam param = new AuditThemeParam();
        param.setThemeId(99928L); // 后面会改成取序列获取最新的ID
        param.setThemeName(themeName);
        param.setCode("7777777777");
        param.setComments(comments);
        param.setCreatedStaff(1L);
        param.setCreatedDate(createDate);
        param.setState("A");
        param.setStateDate(DateUtil.getNowTimeDate());
        param.setThemeType(themeType);

        BeanUtils.copyProperties(param, dto, AuditThemeDto.class);
        this.auditThemeDAO.createTheme(dto);
        return param;
    }

    @Override
    public void updateTheme(AuditThemeParam param) {
        AuditThemeDto dto = new AuditThemeDto();
        BeanUtils.copyProperties(param, dto, AuditThemeDto.class);
        this.auditThemeDAO.updateTheme(dto);

    }
}
