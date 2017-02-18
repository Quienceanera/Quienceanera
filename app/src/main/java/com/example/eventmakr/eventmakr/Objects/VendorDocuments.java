package com.example.eventmakr.eventmakr.Objects;

public class VendorDocuments {

    private String nonDisclosureAgreement;
    private String partnershipAgreement;
    private String saasOrderForm;
    private String businessLicense;
    private String foodVendorsPermit;
    private String alcoholBeverageControlLicense;
    private String stateBoardEqualizationHealth;
    private String businessInsurance;
    private String deliveryInsurance;

    private VendorDocuments() {
    }

    public VendorDocuments(String nonDisclosureAgreement, String partnershipAgreement, String saasOrderForm, String businessLicense, String foodVendorsPermit, String alcoholBeverageControlLicense, String stateBoardEqualizationHealth, String businessInsurance, String deliveryInsurance) {
        this.nonDisclosureAgreement = nonDisclosureAgreement;
        this.partnershipAgreement = partnershipAgreement;
        this.saasOrderForm = saasOrderForm;
        this.businessLicense = businessLicense;
        this.foodVendorsPermit = foodVendorsPermit;
        this.alcoholBeverageControlLicense = alcoholBeverageControlLicense;
        this.stateBoardEqualizationHealth = stateBoardEqualizationHealth;
        this.businessInsurance = businessInsurance;
        this.deliveryInsurance = deliveryInsurance;
    }

    public String getNonDisclosureAgreement() {
        return nonDisclosureAgreement;
    }

    public void setNonDisclosureAgreement(String nonDisclosureAgreement) {
        this.nonDisclosureAgreement = nonDisclosureAgreement;
    }

    public String getPartnershipAgreement() {
        return partnershipAgreement;
    }

    public void setPartnershipAgreement(String partnershipAgreement) {
        this.partnershipAgreement = partnershipAgreement;
    }

    public String getSaasOrderForm() {
        return saasOrderForm;
    }

    public void setSaasOrderForm(String saasOrderForm) {
        this.saasOrderForm = saasOrderForm;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getFoodVendorsPermit() {
        return foodVendorsPermit;
    }

    public void setFoodVendorsPermit(String foodVendorsPermit) {
        this.foodVendorsPermit = foodVendorsPermit;
    }

    public String getAlcoholBeverageControlLicense() {
        return alcoholBeverageControlLicense;
    }

    public void setAlcoholBeverageControlLicense(String alcoholBeverageControlLicense) {
        this.alcoholBeverageControlLicense = alcoholBeverageControlLicense;
    }

    public String getStateBoardEqualizationHealth() {
        return stateBoardEqualizationHealth;
    }

    public void setStateBoardEqualizationHealth(String stateBoardEqualizationHealth) {
        this.stateBoardEqualizationHealth = stateBoardEqualizationHealth;
    }

    public String getBusinessInsurance() {
        return businessInsurance;
    }

    public void setBusinessInsurance(String businessInsurance) {
        this.businessInsurance = businessInsurance;
    }

    public String getDeliveryInsurance() {
        return deliveryInsurance;
    }

    public void setDeliveryInsurance(String deliveryInsurance) {
        this.deliveryInsurance = deliveryInsurance;
    }
}
