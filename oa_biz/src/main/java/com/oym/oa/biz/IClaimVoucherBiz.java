package com.oym.oa.biz;

import com.oym.oa.entity.ClaimVoucher;
import com.oym.oa.entity.ClaimVoucherItem;
import com.oym.oa.entity.DealRecord;

import java.util.List;

public interface IClaimVoucherBiz {

    /**
     * 保存报销单
     * @param claimVoucher
     * @param items
     */
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    /**
     * 获取报销单信息
     * @param id
     * @return
     */
    ClaimVoucher get(int id);

    /**
     * 获取报销单具体信息
     * @param cvid
     * @return
     */
    List<ClaimVoucherItem> getItems(int cvid);

    /**
     * 获取报销记录
     * @param cvid
     * @return
     */
    List<DealRecord> getRecords(int cvid);


    /**
     * 获取个人的报销单
     * @param sn
     * @return
     */
    List<ClaimVoucher> getForSelf(String sn);

    /**
     * 获取待处理的报销单
     * @param sn
     * @return
     */
    List<ClaimVoucher> getForDeal(String sn);

    /**
     * 修改报销单
     * @param claimVoucher
     * @param items
     */
    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    /**
     * 提交报销单
     * @param id
     */
    void submit(int id);

    /**
     * 审核报销单
     * @param dealRecord
     */
    void deal(DealRecord dealRecord);

}
