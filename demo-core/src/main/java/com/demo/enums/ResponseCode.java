package com.demo.enums;

/**
 * Created by sunjz on 2017/4/18.
 */
public enum ResponseCode {
    _000(0, "成功", "Success."),
    _001(1, "对不起，请登录后进行操作.", "Oops, please log in to continue."),
    _002(2, "对不起，您的账号已被禁用.", " Oops, your account has been locked."),
    _003(3, "对不起，用户不存在", " Oops, the user does not exist."),
    _004(4, "对不起，登录失败", "Oops, login has failed."),
    _005(5, "对不起，登录信息失效", "Oops, login information has expired."),
    _006(6, "对比起，您账号在其他设备登录，已被踢出", "Sorry, your account has been used to log into another device. You have been logged off."),
    _010(10, "服务器开小差了~等一下下嘛~", "The server is temporarily unavailable. Please try again later."),
    _011(11, "服务器在偷懒儿,请稍候再试", "The server is temporarily unavailable. Please try again later."),//数据源或者缓存问题     
    _012(12, "不要着急,休息一下", "Just a moment, we are processing your request."),//dubbo异常  
    _013(13, "对不起，您权限不足！", "Access denied.  You do not have the permission."),
    _014(14, "未注册的设备，无法使用", "Operation is not allowed. You have not registered yet."),
    _015(15, "您的设备已被禁止访问，请联系客服", "Your account has been locked. Please contact customer service."),
    _016(16, "参数(%s)错误", "Parameter (%s) error."),
    _017(17, "请求失败,稍候再试,如仍未成功请联系客服人员", " Request has failed.  Please try again later. If  your issue is still not resolved, please contact customer service"),
    _018(18, "该操作不被支持", "This operation could not be completed at the moment."),
    _019(19, "资源未找到", "Resource  is not found."),
    _020(20, "请求时间过期", "The  request has timed out."),
    _021(21, "签名不合法", " Signature  is invalid."),
    _022(22, "UA解析失败", "Failed to parse UA."),
    _023(23, "协议版本不匹配", "Protocol mismatch."),
    _041(41, "发生未知错误", "An unknown error has occurred."),//api拦截的所有错误
    _042(42, "appId不存在", "AppId  does not exist."),
    _043(43, "账户在其他设备登录，您被踢出!", " Your account has been used to log into another device. You have been logged off."),
    _044(44, "对不起，您请求的URL不存在!", "Sorry, the url you requested does not exist."),

    //上传内容提示
    _045(45, "上传成功","Upload successfully"),
    _046(46, "上传失败","Upload failed"),
    _047(47, "超过上传大小限制","Exceed upload size limit"),
    _048(48, "不支持的扩展名","Unsupported extensions");

    private int code;

    private String msg;

    private String enMsg;

    ResponseCode(int code, String msg, String enMsg) {
        this.code = code;
        this.msg = msg;
        this.enMsg = enMsg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getEnMsg() {
        return enMsg;
    }
}
