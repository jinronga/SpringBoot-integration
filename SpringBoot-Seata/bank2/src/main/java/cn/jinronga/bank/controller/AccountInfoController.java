package cn.jinronga.bank.controller;


import cn.jinronga.bank.service.IAccountInfoService;
import cn.jinronga.vo.TransFerAccoutsInfoVo;
import cn.jinronga.vo.TransactionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2021-11-19
 */
@RestController
@RequestMapping("/bank1/account-info")
public class AccountInfoController {

    @Autowired
    private IAccountInfoService accountInfoService;

    @PostMapping("/collectMoney")
    public String collectMoney(TransactionVo transactionVo) {
        TransFerAccoutsInfoVo transFerAccoutsInfoVo = new TransFerAccoutsInfoVo();
        transFerAccoutsInfoVo.setGatheringAccountNo(transactionVo.getAccountNo());
        transFerAccoutsInfoVo.setMoneyNumber(transactionVo.getAccountMoney());
        return accountInfoService.collectMoney(transFerAccoutsInfoVo);
    }

}
