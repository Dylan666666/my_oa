package com.oym.oa.biz.impl;

import com.oym.oa.biz.IClaimVoucherBiz;
import com.oym.oa.dao.IClaimVoucherDAO;
import com.oym.oa.dao.IClaimVoucherItemDAO;
import com.oym.oa.dao.IDealRecordDAO;
import com.oym.oa.dao.IEmployeeDAO;
import com.oym.oa.entity.ClaimVoucher;
import com.oym.oa.entity.ClaimVoucherItem;
import com.oym.oa.entity.DealRecord;
import com.oym.oa.entity.Employee;
import com.oym.oa.global.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("claimVoucherBiz")
public class IClaimVoucherBizImpl implements IClaimVoucherBiz {

    @Autowired
    private IClaimVoucherDAO claimVoucherDAO;
    @Autowired
    private IClaimVoucherItemDAO claimVoucherItemDAO;
    @Autowired
    private IDealRecordDAO dealRecordDAO;
    @Autowired
    private IEmployeeDAO employeeDAO;

    @Override
    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherDAO.insert(claimVoucher);

        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            claimVoucherItemDAO.insert(item);
        }

    }

    @Override
    public ClaimVoucher get(int id) {
        return claimVoucherDAO.select(id);
    }

    @Override
    public List<ClaimVoucherItem> getItems(int cvid) {
        return claimVoucherItemDAO.selectByClaimVoucher(cvid);
    }

    @Override
    public List<DealRecord> getRecords(int cvid) {
        return dealRecordDAO.selectByClaimVoucher(cvid);
    }

    @Override
    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherDAO.selectByCreateSn(sn);
    }

    @Override
    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherDAO.selectByNextDealSn(sn);
    }

    @Override
    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        claimVoucherDAO.update(claimVoucher);

        List<ClaimVoucherItem> olds = claimVoucherItemDAO.selectByClaimVoucher(claimVoucher.getId());
        for (ClaimVoucherItem old : olds) {
            boolean isHave = false;
            for (ClaimVoucherItem item : items) {
                if (item.getId() == old.getId()) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                claimVoucherItemDAO.delete(old.getId());
            }
        }
        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            if (item.getId() != null && item.getId() > 0) {
                claimVoucherItemDAO .update(item);
            } else {
                claimVoucherItemDAO.insert(item);
            }
        }
    }

    @Override
    public void submit(int id) {
        ClaimVoucher claimVoucher = claimVoucherDAO.select(id);
        Employee employee = employeeDAO.select(claimVoucher.getCreateSn());

        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);
        claimVoucher.setNextDealSn(employeeDAO.selectByDepartmentAndPost(employee.getDepartmentSn(),Contant.POST_FM).get(0).getSn());
        claimVoucherDAO.update(claimVoucher);

        DealRecord dealRecord = new DealRecord();
        dealRecord.setDealWay(Contant.DEAL_SUBMIT);
        dealRecord.setDealSn(employee.getSn());
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setDealTime(new Date());
        dealRecord.setComment("无");
        dealRecordDAO.insert(dealRecord);
    }

    /**
     * 处理报销单的各项功能
     * @param dealRecord
     */
    @Override
    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherDAO.select(dealRecord.getClaimVoucherId());
        Employee employee = employeeDAO.select(dealRecord.getDealSn());
        if (dealRecord.getDealWay().equals(Contant.DEAL_PASS)) {
            if (claimVoucher.getTotalAmount() <= Contant.LIMIT_CHECK ||
                    employee.getPost().equals(Contant.POST_GM)) {
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDealSn(employeeDAO.selectByDepartmentAndPost(null,Contant.POST_GASHIER).get(0).getSn());

                dealRecord.setDealTime(new Date());
                dealRecord.setDealResult(Contant.CLAIMVOUCHER_APPROVED);
            } else {
                claimVoucher.setStatus(Contant.CLAIMVOUCHER_RECHECK);
                claimVoucher.setNextDealSn(employeeDAO.selectByDepartmentAndPost(null,Contant.POST_GM).get(0).getSn());

                dealRecord.setDealTime(new Date());
                dealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
            }
        } else if (dealRecord.getDealWay().equals(Contant.DEAL_BACK)) {
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

            dealRecord.setDealTime(new Date());
            dealRecord.setDealResult(Contant.DEAL_REJECT);
        } else if (dealRecord.getDealWay().equals(Contant.DEAL_REJECT)) {
          claimVoucher.setStatus(Contant.CLAIMVOUCHER_TERMINATED);
          claimVoucher.setNextDealSn(null);

          dealRecord.setDealTime(new Date());
          dealRecord.setDealResult(Contant.CLAIMVOUCHER_TERMINATED);
        } else if (dealRecord.getDealWay().equals(Contant.DEAL_PAID)) {
            claimVoucher.setStatus(Contant.CLAIMVOUCHER_PAID);
            claimVoucher.setNextDealSn(null);

            dealRecord.setDealTime(new Date());
            dealRecord.setDealResult(Contant.CLAIMVOUCHER_PAID);
        }
        claimVoucherDAO.update(claimVoucher);
        dealRecordDAO.insert(dealRecord);
    }
}
