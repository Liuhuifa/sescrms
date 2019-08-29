package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerLogPo {

    public CustomerLogPo() {
    }

    public CustomerLogPo(Long id,String name, String belongsname, int rate, String question, String remark, Integer money, String time) {
        this.logid=id;
        this.logname = name;
        this.belongsname = belongsname;
        this.lograte = rate;
        this.logquestion = question;
        this.logremark = remark;
        this.logmoney = money;
        this.logtime = time;
    }

    private Long logid; //客户id  外键
    private String logname;//客户姓名
    private String belongsname;//管理者名字

    private int lograte;//联系进度
    private String logquestion;//询问的问题
    private String logremark;//回答

    private Integer logmoney;
    private String logtime;//创建时间

}
