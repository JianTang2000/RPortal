package com.tang.api.billing;

import java.util.Date;
import java.util.List;

import com.tang.param.billing.AuditThemeParam;

/**
 * < ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/11 <br>
 */
public interface AuditThemeService {
    /**
     * < > <br>
     *
     * @return < >
     * @throws Exception <br>
     * @auther: tang.jian
     */
    List<AuditThemeParam> queryAllTheme() throws Exception;

    /**
     * < > <br>
     * 
     * @auther: tang.jian
     * @param themeId < >
     * @return < >
     */
    AuditThemeParam queryById(Long themeId) throws Exception;

    /**
     * < > <br>
     *
     * @auther: tang
     * @param themeId < >
     */
    void deleteById(Long themeId);

    /**
     * < > <br>
     *
     * @auther: tang
     * @param themeName < >
     * @param comments < >
     * @param themeType < >
     * @param createDate < >
     */
    AuditThemeParam createTheme(String themeName, String comments, Long themeType, Date createDate);

    /**
     * < > <br>
     *
     * @auther: tang
     * @param param < >
     */
    void updateTheme(AuditThemeParam param);
}
