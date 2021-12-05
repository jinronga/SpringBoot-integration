package cn.jinronga.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransFerAccoutsInfoVo {
    private Long userId;
    /**
     *转账银行卡号码
     */
    private String accountNo;
    /**
     * 收款银行卡号码
     */
    private String gatheringAccountNo;
    private BigDecimal moneyNumber;
}