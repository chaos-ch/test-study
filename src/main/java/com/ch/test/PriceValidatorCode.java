package com.ch.test;

import java.io.Serializable;

/**
 * User: yanyan
 * Date: 7/22/14
 * Time: 3:55 PM
 */
public enum PriceValidatorCode implements Serializable {

    UNKNOWN_ERROR(1,"未知错误"),

    HOTEL_INFO_ERROR(100,"酒店基础信息错误"),       //修改酒店信息
    HOTEL_INFO_CHANGE(101,"酒店全量接口与报价接口中酒店信息不一致"), //重新同步酒店
    HOTEL_ID_NOT_MATCH(102,"酒店ID与报价中的酒店ID不匹配"), //重新同步酒店
    HOTEL_SEQ_NOT_EXIST(103,"酒店未聚合"),//





    //房型报价级别错误
    ROOM_NAME_EMPTY(200,"房型名称为空"),    //接口基础信息错误
    ROOM_NAME_TOO_LONG(201,"房型名称太长"),        //接口基础信息错误


    ROOM_ID_EMPTY(210,"房型ID为空"),           //接口基础信息错误
    ROOM_ID_TOO_LONG(211,"房型ID太长"),         //接口基础信息错误

    ROOM_PAY_TYPE_ERROR(220,"支付类型错误，只能为0，1"),   //接口基础信息错误

    ROOM_BED_ERROR(230,"床型信息不合法"),               //接口基础信息错误

    ROOM_CHANNEL_UNAVAILABLE(240,"channel跟支付类型不匹配"),       //接口基础信息错误
    ROOM_CHANNEL_NOT_MATCH(241,"channel跟请求值不一致"),        //接口基础信息错误

    ROOM_BROADBAND_ERROR(250,"宽带信息不合法"),                 //接口基础信息错误

    ROOM_CURRENCYTYPE_ERROR(251,"货币类型不支持"),              //接口基础信息错误

    ROOM_LATEST_ARRIVE_TIME_ZERO(260,"最晚到店时间为0：00或00：00不展示报价"),
    ROOM_LATEST_ARRIVE_TIME_AFTER_NOW(261,"当天当前时间晚于最晚到店时间不展示报价"),
    ROOM_LATEST_ARRIVE_TIME_RULE_ERROR(262,"最晚到店时间格式错误，应该为空或者dd:dd"),

    ROOM_MAX_ROOMNUM_ERROR(270,"最大可预订间数错误，应该为空或者大于0"),






    PRICE_NO_RESULT(300,"代理商接口中无返回"),
    PRICE_CACHE_OUT_OF_DATE(301,"报价缓存可能过期。相应部分房型查到有报价，请查看现在是否有展示，如果还未展示，请尝试清缓存或点击下方发送邮件按钮，我们会尽快为你查找原因"),

    PRICE_PRICE_ERROR(310,"报价格式错误，,应该与请求天数一致"),                       //接口基础信息错误
    PRICE_DEPOSIT_PAY_TYPE_NOT_MATCH(311,"直减不支持现付"),      //接口基础信息错误
    PRICE_DEPOSIT_ERROR(312,"直减格式错误,应该为空或者为整数并且与请求天数一致且小于售卖报价"),                         //接口基础信息错误
    PRICE_CASH_BACK_RULE_ERROR(313,"返现格式错误,应该为空，0或与请求天数一致"),             //接口基础信息错误

    PRICE_STATUS_RULE_ERROR(320,"房态格式错误,应该为空或与请求天数一致，有效值为0，1"),                 //接口基础信息错误

    PRICE_COUNT_RULE_ERROR(330,"房量格式错误,应该为空或与请求天数一致"),                      //接口基础信息错误



    PRICE_BREAKFAST_RULE_ERROR(316, "早餐不可为空"),             //接口基础信息错误
    PRICE_LAST_MINUTE_DISCOUNT_ERROR(317, "夜宵折扣错误"),      //接口基础信息错误
    PRICE_LAST_MINUTE_COUNT_ERROR(318, "夜宵房量错误，大于0"),        //接口基础信息错误
    PRICE_GURANTEE_RULE_ERROR(319,"担保规则错误"),              //接口基础信息错误
    PRICE_REFUND_ERROR(321, "退款规则错误"),                   //接口基础信息错误



    //业务过滤
    PRICE_COUNT_REFUSE_ERROR(400,"房量不足拒绝预订"),        //业务过滤，提示
    PRICE_OTATTS_COUNT_RULE_ERROR(401,"OTATTS校验房量格式错误"),                      //业务过滤，提示
    ROOM_INFO_REMARK_CONTAIN_ILLEGAL_WORD(402, "房型特殊说明（接口中remark字段）中含有电话、邮箱、网址等不规范信息"),     //业务过滤

    ROOM_ADVANCE_LAST_ERROR(403,"提前连住信息不满足，请修改搜索日期"),//业务过滤
    ROOM_INFO_NAME_CONTAIN_ILLEGAL_WORD(404, "房型名称中含有敏感词"),
    ROOM_SERVICE_BED_TYPE_NOT_MATCH_ROOM(405, "床型与房型名称不一致"),
    //13:酒店的温馨提示
    HOTEL_INFO_REMARK_CONTAIN_ILLEGAL_WORD(406, "酒店特殊说明（接口中remark字段）中含有电话、邮箱、网址等不规范信息"),
    //14:酒店的优惠信息
    HOTEL_INFO_PROMOTION_CONTAIN_ILLEGAL_WORD(407, "酒店优惠（接口中promotion字段）中含有电话、邮箱、网址等不规范信息"),
    ROOM_FILTER_ONLINE(408,"代理商未开预付功能，过滤预付房型"),
    ROOM_FILTER_CASH(409,"代理商未开现付功能，过滤现付房型"),
    ROOM_FILTER_GURANTEE(410,"过滤25天外的现付担保的房型"),
    DATA_ILLEGAL_WORD(411,"数据中包含不规范字符"),
    REQUEST_PARAM_ERROR(412,"请求参数错误"),
    REQUEST_PARAM_DATE_RULE_ERROR(413,"日期格式错误"),
    REQUEST_PARAM_DATE_LIMIT_ERROR(414,"日期限制错误"),
    OTA_TTS_CONFIG_CLOSED(415,"代理商TTS功能关闭"),
    OTA_PAY_TYPE_CLOSED(416,"代理商预付和现付功能关闭"),
    //tewll中业务过滤
    TWELL_ROOM_KEYWORDS_FILTER(417,"房型名称中包含一下敏感关键字，\"白日\", \"白天\", \"白昼\", \"小时\", \"加床\", \"洗浴\", \"业主房\", \"厨房\", \"吊床\""),
    TWELL_ROOM_TICKET_NOT_BEFORE_FANGJIAN(418,"房型名称中包含票且包含“房”或“间”,“票”应该在“房”或“间”的前面"),
    TWELL_ROOM_TICKET_NOT_IN_BRACKETS(419,"房型名称中包含票，但“票”不在括号里面"),
    TWELL_ROOM_TICKET_NOT_CONTAINS_FANG_OR_JIAN(420,"房型名称中包含票，但不包含“房”&不包含“间”过滤"),
    /**
     * q2mg.trade.qunar.com=\u6C99\u53D1（沙发）
     * m3gw.trade.qunar.com=\u51CC\u6668,\u5348\u591C,\u949F\u70B9,\u65E5\u79DF（凌晨,午夜,钟点,日租）
     * a7db.trade.qunar.com=\u51CC\u6668,\u5348\u591C,\u949F\u70B9,\u65E5\u79DF（凌晨,午夜,钟点,日租）
     * m4gw.trade.qunar.com=\u534A\u65E5,\u5305\u6708（半日,包月）
     * q3zw.trade.qunar.com=\u5C0F\u65F6,\u949F\u70B9,\u4F1A\u5458（小时,钟点,会员）
     * d9py.trade.qunar.com=\u5C0F\u65F6,\u949F\u70B9（小时,钟点）
     * n2na.trade.qunar.com=\u5C0F\u65F6,\u949F\u70B9（小时,钟点）
     * j2dj.trade.qunar.com=\u5C0F\u65F6,\u949F\u70B9（小时,钟点）
     * y2jj.trade.qunar.com=\u5C0F\u65F6,\u949F\u70B9（小时,钟点）
     */
    TWELL_SPECIAL_OTA_KEYWORDS(421,"个别特殊代理商的过滤规则过滤"),

    /**
     * "wiotatts107","wiotatts076","wiotatts085","wiotatts093","wiotatts094"
     * if(搜索日期==1&&房型名称包含"连住") 过滤
     * if(搜索日期为当天&&房型名称包含“提前”) 过滤
     */
    TWELL_SPECIAL_WRAPPER_KEYWORDS(421,"个别特殊wrapper的过滤规则过滤"),


    SYSTEM_ERROR(901,"系统内部错误"),      //各个接口调用的时候参数不正确
    WRAPPER_ONLINE_API_ERROR(902,"wrapper在线接口异常"),
    WRAPPER_NOT_ONLINE(903,"wrapper不在线"),
    WRAPPER_NOT_EXSIST(904,"wrapper不存在"),
    WRAPPER_INTERFACE_NULL_RESULT(905,"price_provider wrapper 接口数据为空"), //为了排查错误，此类型证明wrapper接口为空，避免未检查出内部系统错误而直接返回空，无法定位错误
    QHOTEL_HOTEL_NOT_EXSIST(906,"qhotel中不存在该酒店(Tree信息不存在)"),   //1. 用户重新聚合一下
    QHOTEL_HOTEL_DELETED(907,"qhotel已经删除该酒店"),                    //1.用户检查一下city_url字段
    QHOTEL_HOTEL_NO_SEQ(908,"qhotel中不存在该seq(聚合失败)"),
    QHOTEL_HOTEL_NEED_CHECK(909,"qhotel中该酒店待审核"),
    HOTEL_NOT_EXSITS(910,"tts系统中不存在该酒店"),   //正常情况下不会出现这个错误
    OTA_NOT_EXSIS(910,"tts系统中不存在该代理商")     //正常情况下不会出现这个错误

    ;


    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static PriceValidatorCode getValueByName(String name){
        PriceValidatorCode[] codes = values();
        for(PriceValidatorCode code1 : codes){
            if(code1.name().equals(name)){
                return code1;
            }
        }
        return PriceValidatorCode.UNKNOWN_ERROR;
    }
    PriceValidatorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;

    }
}
