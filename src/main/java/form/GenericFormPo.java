package form;

import java.sql.Timestamp;

public class GenericFormPo {

    // 表单字段

//    private String company;
//    private String department;
//    private String jobTitle;
//    private String industry;    // 行业
//    private String question;    // 问题
//    private String city;
//    private String purpose;
//    private String headCount;

    // 访客信息收集 如果能采集得到，都要填写

    private String name;     // 姓名   必填
    private String phone;           // 电话   必填
    private String email;           // 邮箱   必填
    private String pageTitle;       // 页面标题
    private String pageURL;         // 页面URL
    private String referrerURL;     // 进入京东云网站之前的网站链接
    private String referrerDomain;  // 进入京东云网站之前的网站域名
    private String campaignCode;    // 活动标识
    private String userIP;          // 用户IP
    private String deviceType;      // 用户使用的设备类型
    private String userAgent;       // HTTP请求的用户代理头
    private String isWillingToContact;  // 是否有沟通意愿
    private String jdCloudAccount;  // 京东账号
    private Timestamp createTime;      // 创建时间

    // other
    private String others;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageURL() {
        return pageURL;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    public String getReferrerURL() {
        return referrerURL;
    }

    public void setReferrerURL(String referrerURL) {
        this.referrerURL = referrerURL;
    }

    public String getReferrerDomain() {
        return referrerDomain;
    }

    public void setReferrerDomain(String referrerDomain) {
        this.referrerDomain = referrerDomain;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIsWillingToContact() {
        return isWillingToContact;
    }

    public void setIsWillingToContact(String isWillingToContact) {
        this.isWillingToContact = isWillingToContact;
    }

    public String getJdCloudAccount() {
        return jdCloudAccount;
    }

    public void setJdCloudAccount(String jdCloudAccount) {
        this.jdCloudAccount = jdCloudAccount;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "    GenericFormPo{" + "\r\n" +
                "       requestName=" + name + "\r\n" +
                ",      phone=" + phone + "\r\n" +
                ",      email=" + email + "\r\n" +
                ",      pageTitle=" + pageTitle + "\r\n" +
                ",      pageURL=" + pageURL + "\r\n" +
                ",      referrerURL=" + referrerURL + "\r\n" +
                ",      referrerDomain=" + referrerDomain + "\r\n" +
                ",      campaignCode=" + campaignCode + "\r\n" +
                ",      userIP=" + userIP + "\r\n" +
                ",      createTime=" + createTime + "\r\n" +
                ",      deviceType=" + deviceType + "\r\n" +
                ",      userAgent=" + userAgent + "\r\n" +
                ",      isWillingToContact=" + isWillingToContact + "\r\n" +
                ",      jdCloudAccount=" + jdCloudAccount + "\r\n" +
                ",      others=" + others + "\r\n" +
                "       }";
    }
}
