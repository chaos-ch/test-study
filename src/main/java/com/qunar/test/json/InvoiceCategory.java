package com.qunar.test.json;

/**
 * Created by xiaoxiong.li on 2014/10/27 23:14). version $Id$
 */
public enum InvoiceCategory {
    BUSINESS_TAX_INVOICE("BT", "营业税发票"), EXPORT_INVOICE("E_INV", "出口发票"), ADD_VALUE_TAX_INVOICE("N_VAT", "增值税普通发票"), TRANSPORTATION_INVOICE(
            "T_INV", "交通运输发票"), ADD_VALUE_TAX_SPECIAL_INVOICE("VAT", "增值税专用发票");
    private String invoiceCategoryCode;
    private String invoiceCategoryDesc;

    public static InvoiceCategory defaultValue() {
        return BUSINESS_TAX_INVOICE;
    }

    InvoiceCategory(String invoiceCategoryCode, String invoiceCategoryDesc) {
        this.invoiceCategoryCode = invoiceCategoryCode;
        this.invoiceCategoryDesc = invoiceCategoryDesc;
    }

    /**@author yushen.ma */
    public static InvoiceCategory of(String _name) {
        if (_name == null) {
            throw new IllegalArgumentException("Invalid InvoiceCategory name: null ");
        }
        String name = _name.toUpperCase().trim();
        for (InvoiceCategory InvoiceCategory : values()) {
            if (InvoiceCategory.name().equals(name)) {
                return InvoiceCategory;
            }
        }
        for (InvoiceCategory invoiceCategory : InvoiceCategory.values()) {
            if (invoiceCategory.getInvoiceCategoryCode().equals(name)) {
                return invoiceCategory;
            }
        }
        throw new IllegalArgumentException("Invalid InvoiceCategory name: " + _name);
    }

    public String getInvoiceCategoryCode() {
        return invoiceCategoryCode;
    }

    public void setInvoiceCategoryCode(String invoiceCategoryCode) {
        this.invoiceCategoryCode = invoiceCategoryCode;
    }

    public String getInvoiceCategoryDesc() {
        return invoiceCategoryDesc;
    }

    public void setInvoiceCategoryDesc(String invoiceCategoryDesc) {
        this.invoiceCategoryDesc = invoiceCategoryDesc;
    }
}
