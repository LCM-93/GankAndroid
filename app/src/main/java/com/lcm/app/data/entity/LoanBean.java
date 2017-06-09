package com.lcm.app.data.entity;

import java.util.List;

/**
 * Created by guozhong on 16/10/21.
 */

public class LoanBean {

    /**
     * autokid : 37
     * id : 40
     * androidpaymenturl :
     * androidtarget : 0
     * apply : 18-55周岁，持中国居民身份证的中国大陆公民
     * applycondition : 18-55周岁的大陆公民，手机号使用时间超过半年
     * applyexplain : 审核周期：5分钟内纯线上自动审核
     放款时间：1小时内放款
     还款方式：自动扣款或去平台还款，支持提前还款
     * applyimagepath : http://ai.jielema.com:10080/iotglb-openapi/images/
     * applyprocess :
     * buttonname : 快速进入
     * category : 3&8
     * click : 1463961
     * comment : 1387****645-3小时内就放款了..|1860****681-资料填写少，反馈速度快|1860****765-这个速度值得表扬一下!
     * company : https://weixin.cashbus.com/#/events/promo/18101
     * url_h5 : https://weixin.cashbus.com/#/events/promo/22301
     * url_h5_xiaoyu : https://weixin.cashbus.com/#/events/promo/26001
     * createdate : 04月07日
     * createtime : 04-07 16:00
     * xxx : 7
     * creditdatemin :
     * creditdatemax :
     * creditdates : ,,
     * creditmaxamount : 20000
     * creditminamount : 500
     * defalutcreditdate : 7
     * defaultmoney : 1000
     * defaulttarget :
     * fitpeople : 1$2
     * grouptype : 3
     * home : 1
     * imagepath : https://dn-kdjz.qbox.me/jiedai/icon/40/1479137572.png
     * interest : [
     {
     "creditdate": 7,
     "id": 497,
     "monthinterest": "0.72",
     "productid": "40",
     "type": "2",
     "version": 0
     },
     {
     "creditdate": 14,
     "id": 499,
     "monthinterest": "0.72",
     "productid": "40",
     "type": "2",
     "version": 0
     }
     ]
     * interesttype :
     * iospaymenturl :
     * iostarget : 8
     * iosweixinhome : 2$3
     * joincount : 826435
     * jumptype : 16
     * largeloan : 0
     * modifytime : 1472912995000
     * money : .00
     * monthinterest :
     * monthmoney : 181
     * name : 现金巴士-十万火急
     * othercomment : 申请其他产品,提高成功率
     * otherurl :
     * phone : 021-10101058
     * process : [
     {
     "comment": "",
     "image": "productdetail_btn_sqlc_ysysq.png",
     "name": "手机认证"
     },
     {
     "comment": "",
     "image": "productdetail_btn_sqlc_jbxx.png",
     "name": "身份认证"
     },
     {
     "comment": "",
     "image": "productdetail_btn_sqlc_ddfk.png",
     "name": "等待放款"
     }
     ]
     * producttype : 2
     * producttypeid : 0
     * proof : 身份证;
     手机运营商;
     * rate : 0.72
     * ratearea : 0.72%
     * rate_month : 0.72
     * ratetype : 1
     * refproductid :
     * remark : 1、短期小额应急借款，额度500/1000元，门槛低
     2、18-55周岁，有手机就能申请
     * repaymenttype : 2
     * serial : 94
     * strategyurl : http://ai.jielema.com:10080/iotglb-openapi/html/raiders_dk2_xjbs.html
     * target : 1
     * templet :
     * templetid :
     * tips : 尝试多种贷款产品，能大大提高您的贷款成功率|请确保信息完整，可减少审核时间，快速放款|信用是一个人最大的无形资产，请珍惜您的信用
     * totalinterest : 172
     * usetime : 60
     * version : 0
     * weixindetail : ["只凭身份证\r","手机号验证\r","轻松借款2000\r"]
     * weixinno :
     * weixinserial : 50
     * weixintarget : 热门
     * order_ : 3100
     * order_home : 300
     * havacreditcard : 0
     * is_deleted : 0
     * row_created_at : 2016-09-03 22:30:02
     * row_updated_at : 2016-12-14 17:41:30
     * order_group_0 : 700
     * order_group_1 : 0
     * order_group_2 : 0
     * order_group_3 : 700
     * order_group_4 : 0
     * order_group_6 : 0
     * order_group_7 : 0
     * order_group_8 : 0
     * order_group_9 : 0
     * is_hot : 0
     * success_rate : 98
     * mobilelong :
     * income_type :
     * house :
     * car :
     * gongjijin_6month : 否
     * shebao_6month : 否
     * cardit :
     * list_type : ,loan,cards,
     * app_path_android :
     * app_path_ios :
     * check_text :
     * loan_text :
     */

    private int autokid;
    private int id;
    private String androidpaymenturl;
    private String androidtarget;
    private String apply;
    private String applycondition;
    private String applyexplain;
    private String applyimagepath;
    private String applyprocess;
    private String buttonname;
    private String category;
    private String click;
    private String comment;
    private String company;
    private String url_h5;
    private String url_h5_xiaoyu;
    private String createdate;
    private String createtime;
    private String xxx;
    private String creditdatemin;
    private String creditdatemax;
    private String creditdates;
    private int creditmaxamount;
    private int creditminamount;
    private String defalutcreditdate;
    private int defaultmoney;
    private String defaulttarget;
    private String fitpeople;
    private String grouptype;
    private String home;
    private String imagepath;
    private String interest;
    private String interesttype;
    private String iospaymenturl;
    private String iostarget;
    private String iosweixinhome;
    private String joincount;
    private String jumptype;
    private String largeloan;
    private String modifytime;
    private String money;
    private String monthinterest;
    private String monthmoney;
    private String name;
    private String othercomment;
    private String otherurl;
    private String phone;
    private String process;
    private String producttype;
    private String producttypeid;
    private String proof;
    private String rate;
    private String ratearea;
    private double rate_month;
    private String ratetype;
    private String refproductid;
    private String remark;
    private String repaymenttype;
    private String serial;
    private String strategyurl;
    private String target;
    private String templet;
    private String templetid;
    private String tips;
    private String totalinterest;
    private String usetime;
    private String version;
    private String weixinno;
    private String weixinserial;
    private String weixintarget;
    private int order_;
    private int order_home;
    private int havacreditcard;
    private int is_deleted;
    private String row_created_at;
    private String row_updated_at;
    private int order_group_0;
    private int order_group_1;
    private int order_group_2;
    private int order_group_3;
    private int order_group_4;
    private int order_group_6;
    private int order_group_7;
    private int order_group_8;
    private int order_group_9;
    private int is_hot;
    private String success_rate;
    private String mobilelong;
    private String income_type;
    private String house;
    private String car;
    private String gongjijin_6month;
    private String shebao_6month;
    private String cardit;
    private String list_type;
    private String app_path_android;
    private String app_path_ios;
    private String check_text;
    private String loan_text;
    private List<String> weixindetail;

    public int getAutokid() {
        return autokid;
    }

    public void setAutokid(int autokid) {
        this.autokid = autokid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAndroidpaymenturl() {
        return androidpaymenturl;
    }

    public void setAndroidpaymenturl(String androidpaymenturl) {
        this.androidpaymenturl = androidpaymenturl;
    }

    public String getAndroidtarget() {
        return androidtarget;
    }

    public void setAndroidtarget(String androidtarget) {
        this.androidtarget = androidtarget;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public String getApplycondition() {
        return applycondition;
    }

    public void setApplycondition(String applycondition) {
        this.applycondition = applycondition;
    }

    public String getApplyexplain() {
        return applyexplain;
    }

    public void setApplyexplain(String applyexplain) {
        this.applyexplain = applyexplain;
    }

    public String getApplyimagepath() {
        return applyimagepath;
    }

    public void setApplyimagepath(String applyimagepath) {
        this.applyimagepath = applyimagepath;
    }

    public String getApplyprocess() {
        return applyprocess;
    }

    public void setApplyprocess(String applyprocess) {
        this.applyprocess = applyprocess;
    }

    public String getButtonname() {
        return buttonname;
    }

    public void setButtonname(String buttonname) {
        this.buttonname = buttonname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl_h5() {
        return url_h5;
    }

    public void setUrl_h5(String url_h5) {
        this.url_h5 = url_h5;
    }

    public String getUrl_h5_xiaoyu() {
        return url_h5_xiaoyu;
    }

    public void setUrl_h5_xiaoyu(String url_h5_xiaoyu) {
        this.url_h5_xiaoyu = url_h5_xiaoyu;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }

    public String getCreditdatemin() {
        return creditdatemin;
    }

    public void setCreditdatemin(String creditdatemin) {
        this.creditdatemin = creditdatemin;
    }

    public String getCreditdatemax() {
        return creditdatemax;
    }

    public void setCreditdatemax(String creditdatemax) {
        this.creditdatemax = creditdatemax;
    }

    public String getCreditdates() {
        return creditdates;
    }

    public void setCreditdates(String creditdates) {
        this.creditdates = creditdates;
    }

    public int getCreditmaxamount() {
        return creditmaxamount;
    }

    public void setCreditmaxamount(int creditmaxamount) {
        this.creditmaxamount = creditmaxamount;
    }

    public int getCreditminamount() {
        return creditminamount;
    }

    public void setCreditminamount(int creditminamount) {
        this.creditminamount = creditminamount;
    }

    public String getDefalutcreditdate() {
        return defalutcreditdate;
    }

    public void setDefalutcreditdate(String defalutcreditdate) {
        this.defalutcreditdate = defalutcreditdate;
    }

    public int getDefaultmoney() {
        return defaultmoney;
    }

    public void setDefaultmoney(int defaultmoney) {
        this.defaultmoney = defaultmoney;
    }

    public String getDefaulttarget() {
        return defaulttarget;
    }

    public void setDefaulttarget(String defaulttarget) {
        this.defaulttarget = defaulttarget;
    }

    public String getFitpeople() {
        return fitpeople;
    }

    public void setFitpeople(String fitpeople) {
        this.fitpeople = fitpeople;
    }

    public String getGrouptype() {
        return grouptype;
    }

    public void setGrouptype(String grouptype) {
        this.grouptype = grouptype;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getInteresttype() {
        return interesttype;
    }

    public void setInteresttype(String interesttype) {
        this.interesttype = interesttype;
    }

    public String getIospaymenturl() {
        return iospaymenturl;
    }

    public void setIospaymenturl(String iospaymenturl) {
        this.iospaymenturl = iospaymenturl;
    }

    public String getIostarget() {
        return iostarget;
    }

    public void setIostarget(String iostarget) {
        this.iostarget = iostarget;
    }

    public String getIosweixinhome() {
        return iosweixinhome;
    }

    public void setIosweixinhome(String iosweixinhome) {
        this.iosweixinhome = iosweixinhome;
    }

    public String getJoincount() {
        return joincount;
    }

    public void setJoincount(String joincount) {
        this.joincount = joincount;
    }

    public String getJumptype() {
        return jumptype;
    }

    public void setJumptype(String jumptype) {
        this.jumptype = jumptype;
    }

    public String getLargeloan() {
        return largeloan;
    }

    public void setLargeloan(String largeloan) {
        this.largeloan = largeloan;
    }

    public String getModifytime() {
        return modifytime;
    }

    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getMonthinterest() {
        return monthinterest;
    }

    public void setMonthinterest(String monthinterest) {
        this.monthinterest = monthinterest;
    }

    public String getMonthmoney() {
        return monthmoney;
    }

    public void setMonthmoney(String monthmoney) {
        this.monthmoney = monthmoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOthercomment() {
        return othercomment;
    }

    public void setOthercomment(String othercomment) {
        this.othercomment = othercomment;
    }

    public String getOtherurl() {
        return otherurl;
    }

    public void setOtherurl(String otherurl) {
        this.otherurl = otherurl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getProducttypeid() {
        return producttypeid;
    }

    public void setProducttypeid(String producttypeid) {
        this.producttypeid = producttypeid;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRatearea() {
        return ratearea;
    }

    public void setRatearea(String ratearea) {
        this.ratearea = ratearea;
    }

    public double getRate_month() {
        return rate_month;
    }

    public void setRate_month(double rate_month) {
        this.rate_month = rate_month;
    }

    public String getRatetype() {
        return ratetype;
    }

    public void setRatetype(String ratetype) {
        this.ratetype = ratetype;
    }

    public String getRefproductid() {
        return refproductid;
    }

    public void setRefproductid(String refproductid) {
        this.refproductid = refproductid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRepaymenttype() {
        return repaymenttype;
    }

    public void setRepaymenttype(String repaymenttype) {
        this.repaymenttype = repaymenttype;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getStrategyurl() {
        return strategyurl;
    }

    public void setStrategyurl(String strategyurl) {
        this.strategyurl = strategyurl;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTemplet() {
        return templet;
    }

    public void setTemplet(String templet) {
        this.templet = templet;
    }

    public String getTempletid() {
        return templetid;
    }

    public void setTempletid(String templetid) {
        this.templetid = templetid;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTotalinterest() {
        return totalinterest;
    }

    public void setTotalinterest(String totalinterest) {
        this.totalinterest = totalinterest;
    }

    public String getUsetime() {
        return usetime;
    }

    public void setUsetime(String usetime) {
        this.usetime = usetime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWeixinno() {
        return weixinno;
    }

    public void setWeixinno(String weixinno) {
        this.weixinno = weixinno;
    }

    public String getWeixinserial() {
        return weixinserial;
    }

    public void setWeixinserial(String weixinserial) {
        this.weixinserial = weixinserial;
    }

    public String getWeixintarget() {
        return weixintarget;
    }

    public void setWeixintarget(String weixintarget) {
        this.weixintarget = weixintarget;
    }

    public int getOrder_() {
        return order_;
    }

    public void setOrder_(int order_) {
        this.order_ = order_;
    }

    public int getOrder_home() {
        return order_home;
    }

    public void setOrder_home(int order_home) {
        this.order_home = order_home;
    }

    public int getHavacreditcard() {
        return havacreditcard;
    }

    public void setHavacreditcard(int havacreditcard) {
        this.havacreditcard = havacreditcard;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getRow_created_at() {
        return row_created_at;
    }

    public void setRow_created_at(String row_created_at) {
        this.row_created_at = row_created_at;
    }

    public String getRow_updated_at() {
        return row_updated_at;
    }

    public void setRow_updated_at(String row_updated_at) {
        this.row_updated_at = row_updated_at;
    }

    public int getOrder_group_0() {
        return order_group_0;
    }

    public void setOrder_group_0(int order_group_0) {
        this.order_group_0 = order_group_0;
    }

    public int getOrder_group_1() {
        return order_group_1;
    }

    public void setOrder_group_1(int order_group_1) {
        this.order_group_1 = order_group_1;
    }

    public int getOrder_group_2() {
        return order_group_2;
    }

    public void setOrder_group_2(int order_group_2) {
        this.order_group_2 = order_group_2;
    }

    public int getOrder_group_3() {
        return order_group_3;
    }

    public void setOrder_group_3(int order_group_3) {
        this.order_group_3 = order_group_3;
    }

    public int getOrder_group_4() {
        return order_group_4;
    }

    public void setOrder_group_4(int order_group_4) {
        this.order_group_4 = order_group_4;
    }

    public int getOrder_group_6() {
        return order_group_6;
    }

    public void setOrder_group_6(int order_group_6) {
        this.order_group_6 = order_group_6;
    }

    public int getOrder_group_7() {
        return order_group_7;
    }

    public void setOrder_group_7(int order_group_7) {
        this.order_group_7 = order_group_7;
    }

    public int getOrder_group_8() {
        return order_group_8;
    }

    public void setOrder_group_8(int order_group_8) {
        this.order_group_8 = order_group_8;
    }

    public int getOrder_group_9() {
        return order_group_9;
    }

    public void setOrder_group_9(int order_group_9) {
        this.order_group_9 = order_group_9;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    public String getSuccess_rate() {
        return success_rate;
    }

    public void setSuccess_rate(String success_rate) {
        this.success_rate = success_rate;
    }

    public String getMobilelong() {
        return mobilelong;
    }

    public void setMobilelong(String mobilelong) {
        this.mobilelong = mobilelong;
    }

    public String getIncome_type() {
        return income_type;
    }

    public void setIncome_type(String income_type) {
        this.income_type = income_type;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getGongjijin_6month() {
        return gongjijin_6month;
    }

    public void setGongjijin_6month(String gongjijin_6month) {
        this.gongjijin_6month = gongjijin_6month;
    }

    public String getShebao_6month() {
        return shebao_6month;
    }

    public void setShebao_6month(String shebao_6month) {
        this.shebao_6month = shebao_6month;
    }

    public String getCardit() {
        return cardit;
    }

    public void setCardit(String cardit) {
        this.cardit = cardit;
    }

    public String getList_type() {
        return list_type;
    }

    public void setList_type(String list_type) {
        this.list_type = list_type;
    }

    public String getApp_path_android() {
        return app_path_android;
    }

    public void setApp_path_android(String app_path_android) {
        this.app_path_android = app_path_android;
    }

    public String getApp_path_ios() {
        return app_path_ios;
    }

    public void setApp_path_ios(String app_path_ios) {
        this.app_path_ios = app_path_ios;
    }

    public String getCheck_text() {
        return check_text;
    }

    public void setCheck_text(String check_text) {
        this.check_text = check_text;
    }

    public String getLoan_text() {
        return loan_text;
    }

    public void setLoan_text(String loan_text) {
        this.loan_text = loan_text;
    }

    public List<String> getWeixindetail() {
        return weixindetail;
    }

    public void setWeixindetail(List<String> weixindetail) {
        this.weixindetail = weixindetail;
    }
}
