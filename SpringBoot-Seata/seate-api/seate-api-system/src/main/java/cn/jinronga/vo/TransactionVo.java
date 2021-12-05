package cn.jinronga.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionVo {
    BigDecimal accountMoney;
    String accountNo;
}
