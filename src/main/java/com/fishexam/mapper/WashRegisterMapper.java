package com.fishexam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fishexam.pojo.WashRegister;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cativen
 * @since 2023-03-02
 */
@Mapper
public interface WashRegisterMapper extends BaseMapper<WashRegister> {

    IPage<WashRegister> selectPageInfo(IPage<WashRegister> washRegisterIPage);

    List<WashRegister> queryRecentMsg();

    IPage<WashRegister> selectDistinctPage(IPage<WashRegister> washRegisterIPage);
}
