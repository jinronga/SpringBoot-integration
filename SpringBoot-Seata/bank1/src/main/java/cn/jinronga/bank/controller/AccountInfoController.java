package cn.jinronga.bank.controller;


import cn.jinronga.bank.entity.AccountInfo;
import cn.jinronga.bank.service.IAccountInfoService;
import cn.jinronga.vo.TransFerAccoutsInfoVo;
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

    /**
     * 给指定的银行卡号转账
     * @param accountMoney
     * @param accountNo
     * @return
     */
    @PostMapping("/collectMoney")
    public String collectMoney(BigDecimal accountMoney, String accountNo) {
        TransFerAccoutsInfoVo transFerAccoutsInfoVo = new TransFerAccoutsInfoVo();
        transFerAccoutsInfoVo.setMoneyNumber(accountMoney);
        transFerAccoutsInfoVo.setGatheringAccountNo(accountNo);
        accountInfoService.transferAccounts(transFerAccoutsInfoVo);

        return "转账成功！";
    }

}
