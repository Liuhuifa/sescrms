package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerLogPo {

    public CustomerLogPo() {
    }

    public CustomerLogPo(String name, String belongsname, int rate, String question, String remark, Integer money, String time) {
        this.name = name;
        this.belongsname = belongsname;
        this.rate = rate;
        this.question = question;
        this.remark = remark;
        this.money = money;
        this.time = time;
    }

    private Long id; //客户id  外键
    private String name;//客户姓名
    private String belongsname;//管理者名字

    private int rate;//联系进度
    private String question;//询问的问题
    private String remark;//回答

    private Integer money;
    private String time;//创建时间
    private String keyword;//关键字

}
