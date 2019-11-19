package com.oym.oa.dao;

import com.oym.oa.entity.ClaimVoucher;
import com.oym.oa.entity.ClaimVoucherItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("claimVoucherDAO")
public interface IClaimVoucherDAO {

    /**
     * 插入新报销单
     * @param claimVoucher
     */
    void insert(ClaimVoucher claimVoucher);

    /**
     * 更新报销单信息
     * @param claimVoucher
     */
    void update(ClaimVoucher claimVoucher);

    /**
     * 删除该报销单
     * @param id
     */
    void delete(int id);

    /**
     * 查询目的报销单
     * @param id
     * @return
     */
    ClaimVoucher select(int id);

    /**
     * 查询报销单创建人
     * @return
     */
    List<ClaimVoucher> selectByCreateSn(String csn);

    /**
     * 查询报销单待处理人
     * @param ndsn
     * @return
     */
    List<ClaimVoucher> selectByNextDealSn(String ndsn);



}
