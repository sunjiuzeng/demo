package com.demo.model;

import java.io.Serializable;

/**
 * Desc: 业务操作model
 * User: sunjz
 * Email: jiuzeng.sun@leappmusic.com
 * Date: 2017/5/3
 * Time: 下午1:53
 * To change this template use File | Settings | File Templates.
 */
public class ResultPutModel implements Serializable {

    public static final ResultPutModel SUCCESS_MODEL = new ResultPutModel(true);
    public static final ResultPutModel FAIL_MODEL = new ResultPutModel(false);

    private boolean ret;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public ResultPutModel(boolean ret) {
        this.ret = ret;
    }

    public static ResultPutModel successModel(){
        return SUCCESS_MODEL;
    }

    public static ResultPutModel failModel(){
        return FAIL_MODEL;
    }

}
