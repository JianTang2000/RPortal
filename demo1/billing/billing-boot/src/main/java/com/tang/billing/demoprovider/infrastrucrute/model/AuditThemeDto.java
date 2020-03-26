package com.tang.billing.demoprovider.infrastrucrute.model;

import java.util.Date;

/**
 * < Dto作为Mybatis的返回结果model，仅在业务中心内调用。 param实现序列化，放入api，跨中心调用。
 * 写一份Dto形式上会有些冗余，但可以考虑为未来，dto和mybatis集成包装，可以封很多常用代码进来~不过暂时还没想好要怎么玩><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/12 <br>
 */
public class AuditThemeDto {
    /**
     * 主题标识
     */
    private Long themeId;

    /**
     * 主题名称
     */
    private String themeName;

    /**
     * 主题编码
     */
    private String code;

    /**
     * 描述
     */
    private String comments;

    /**
     * 创建人
     */
    private Long createdStaff;

    /**
     * 创建时间
     */
    private Date createdDate;

    /**
     * 状态
     */
    private String state;

    /**
     * 状态时间
     */
    private Date stateDate;

    /**
     * 状态时间
     */
    private Long themeType;

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getCreatedStaff() {
        return createdStaff;
    }

    public void setCreatedStaff(Long createdStaff) {
        this.createdStaff = createdStaff;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getStateDate() {
        return stateDate;
    }

    public void setStateDate(Date stateDate) {
        this.stateDate = stateDate;
    }

    public Long getThemeType() {
        return themeType;
    }

    public void setThemeType(Long themeType) {
        this.themeType = themeType;
    }
}
