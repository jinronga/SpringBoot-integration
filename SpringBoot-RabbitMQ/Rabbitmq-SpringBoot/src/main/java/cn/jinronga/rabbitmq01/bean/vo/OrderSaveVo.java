package cn.jinronga.rabbitmq01.bean.vo;

/**
 * @ClassName OrderSaveVo
 * @Author 郭金荣
 * @Date 2021/5/7 23:39
 * @Description OrderSaveVo
 * @Version 1.0
 */
public class OrderSaveVo {
    private String userid;
    private String productid;
    private Integer num;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
