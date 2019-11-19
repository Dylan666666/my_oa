package com.oym.oa.dao;

import com.oym.oa.entity.DealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dealRecordDAO")
public interface IDealRecordDAO {

    /**
     * 插入记录
     * @param dealRecord
     */
    void insert(DealRecord dealRecord);

    /**
     * 查询处理记录
     * @param cvid
     * @return
     */
    List<DealRecord> selectByClaimVoucher(int cvid);

}
