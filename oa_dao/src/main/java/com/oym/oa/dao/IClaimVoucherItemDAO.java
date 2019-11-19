package com.oym.oa.dao;

import com.oym.oa.entity.ClaimVoucherItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("claimVoucherItemDAO")
public interface IClaimVoucherItemDAO {

    /**
     * 插入报销单具体信息
     * @param claimVoucherItem
     */
    void insert(ClaimVoucherItem claimVoucherItem);

    /**
     * 更新报销单具体信息
     * @param claimVoucherItem
     */
    void update(ClaimVoucherItem claimVoucherItem);

    /**
     * 删除报销单具体信息
     * @param id
     */
    void delete(int id);

    /**
     * 查询报销单具体信息
     * @param cvid
     * @return
     */
    List<ClaimVoucherItem> selectByClaimVoucher(int cvid);

}
