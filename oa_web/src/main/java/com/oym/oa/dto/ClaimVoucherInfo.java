package com.oym.oa.dto;

import com.oym.oa.entity.ClaimVoucher;
import com.oym.oa.entity.ClaimVoucherItem;

import java.io.Serializable;
import java.util.List;

public class ClaimVoucherInfo implements Serializable {

    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItem> items;

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
