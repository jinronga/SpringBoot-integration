package cn.jinronga.bank.service.impl;

import cn.jinronga.bank.entity.AccountInfo;
import cn.jinronga.bank.mapper.AccountInfoMapper;
import cn.jinronga.bank.service.IAccountInfoService;
import cn.jinronga.feign.TransactionGatherFeignService;
import cn.jinronga.vo.TransFerAccoutsInfoVo;
import cn.jinronga.vo.TransactionVo;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-11-19
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements IAccountInfoService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;
    @Autowired
    private TransactionGatherFeignService transactionGatherFeignService;


    @Override
    @Transactional
    @GlobalTransactional
    public void transferAccounts(TransFerAccoutsInfoVo transFerAccoutsInfoVo) {
        AccountInfo accountInfo = accountInfoMapper.selectOne(Wrappers.<AccountInfo>query().lambda()
//                .eq(AccountInfo::getId, transFerAccoutsInfoVo.getUserId())
                .eq(AccountInfo::getAccountNo, transFerAccoutsInfoVo.getAccountNo()));
        //扣钱
        BigDecimal account = accountInfo.getAccountBalance().subtract(transFerAccoutsInfoVo.getMoneyNumber());
        accountInfo.setAccountBalance(account);
        accountInfoMapper.updateById(accountInfo);
        //转钱
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setAccountNo(transFerAccoutsInfoVo.getGatheringAccountNo());
        transactionVo.setAccountMoney(transFerAccoutsInfoVo.getMoneyNumber());
        transactionGatherFeignService.transferAccounts(transactionVo);
    }


}
