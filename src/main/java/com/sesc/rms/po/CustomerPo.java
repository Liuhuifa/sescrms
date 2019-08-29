package com.sesc.rms.po;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class CustomerPo implements Serializable {
    private static final long serialVersionUID = 224641825474290467L;

    public CustomerPo() {
    }

    public CustomerPo(String name, String address, String tel, Integer belongs, String cfrom, Integer pageindex, Integer group) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.belongs = belongs;
        this.cfrom = cfrom;
        this.pageindex = pageindex;
        this.group = group;
    }

    public CustomerPo(Long id,String name, String address, String qq, String tel, String email, Integer look, Integer caim, String cfrom, String etime, Integer rate, String remark) {
        this.id=id;
        this.name = name;
        this.address = address;
        this.qq = qq;
        this.tel = tel;
        this.email = email;
        this.look = look;
        this.caim = caim;
        this.cfrom = cfrom;
        this.etime = etime;
        this.rate = rate;
        this.remark = remark;
    }

    private Long id;//用户id
    private String name;//姓名
    private String address;//住址

    private String qq;//qq号
    private String tel;//电话
    private String email;//电子邮件

    private Integer belongs;//-1.公共客户 0.未分配
    private String lasttime;//分配时间
    private String time;//创建时间

    private Integer status;//联系状态合作状态 0.联系中1.合作失败2.合作成功
    private Integer look;//新客户分配给员工后员工确认否   0未确认  1确认
    private Integer restatus;//是否回访 0否  1是

    private Integer caim;//客户意向 客户意向1-3 针创无
    private String cfrom;//客户来源
    private String type;//1~7 A+~F 级别

    private String keyword;//关键字
    private String etime;//最近沟通时间
    private Integer rate;//进度

    private String remark;//沟通内容
    private Integer count;//沟通的次数
    private String project;//询问的项目

//    分装聊天内容
    private List<CustomerLogPo> logs;

//    只是为了前端传值,后端接收是方便
    private Integer pageindex=1;//当前页码,默认显示第一页
    private Integer pagesize=15;//每页显示数量,默认每页显示15条数据
    private String uname;//归属坐席的名字

    private Integer group;//分组查询
}
