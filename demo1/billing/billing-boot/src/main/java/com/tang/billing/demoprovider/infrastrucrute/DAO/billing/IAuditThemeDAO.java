package com.tang.billing.demoprovider.infrastrucrute.DAO.billing;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.tang.billing.demoprovider.infrastrucrute.model.AuditThemeDto;

/**
 * < 基础的DAO层接口，包括增删改查，实现由Mybatis管理，这边写出来主要测了进行dbLink测试><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/12 <br>
 */
public interface IAuditThemeDAO {

    /**
     * < 查询返回list > <br>
     * 
     * @auther: tang.jian
     * @return < >
     */
    List<AuditThemeDto> selectAllTheme();

    /**
     * < 查询返回单条记录 > <br>
     *
     * @auther: tang.jian
     * @param themeId < 主键 >
     * @return < >
     */
    AuditThemeDto selectById(Long themeId);

    /**
     * < 根据主键删除 > <br>
     *
     * @param themeId < >
     */
    @Delete("DELETE\n" + //
        "FROM\n" + //
        "AUDIT_THEME\n" + //
        "WHERE\n" + //
        "THEME_ID = #{Id}")
    void deleteById(@Param("Id") Long themeId);

    /**
     * < 新增一条记录 > <br>
     *
     * @auther: tang
     * @param dto < AuditThemeDto >
     */
    @Insert("INSERT INTO AUDIT_THEME (\n" + //
        "   THEME_ID,\n" + //
        "   THEME_NAME,\n" + //
        "   CODE,\n" + //
        "   COMMENTS,\n" + //
        "   CREATED_STAFF,\n" + //
        "   CREATED_DATE,\n" + //
        "   STATE,\n" + //
        "   STATE_DATE,\n" + //
        "   THEME_TYPE\n" + //
        ")\n" + //
        "VALUES\n" + //
        "   (\n" + //
        "       #{dto.themeId},\n" + //
        "       #{dto.themeName},\n" + //
        "       #{dto.code},\n" + //
        "       #{dto.comments},\n" + //
        "       #{dto.createdStaff},\n" + //
        "       #{dto.createdDate},\n" + //
        "       #{dto.state},\n" + //
        "       #{dto.stateDate},\n" + //
        "       #{dto.themeType})")
    void createTheme(@Param("dto") AuditThemeDto dto);

    /**
     *  < 更新，只更新某几个字段  > <br>
     *
     * @auther: tang
     * @param dto < AuditThemeDto >
     * @return < >
     *
     */
    @Update("UPDATE AUDIT_THEME\n" + //
        "SET THEME_NAME = #{dto.themeName},\n" + //
        " CODE = #{dto.code},\n" + //
        " STATE_DATE = #{dto.stateDate},\n" + //
        " COMMENTS = #{dto.comments}\n" + //
        "WHERE\n" + //
        "   THEME_ID = #{dto.themeId}")
    void updateTheme(@Param("dto") AuditThemeDto dto);

}
