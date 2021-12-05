package cn.jinronga.bank.service.impl;

import cn.jinronga.bank.entity.AccountInfo;
import cn.jinronga.bank.mapper.AccountInfoMapper;
import cn.jinronga.bank.service.IAccountInfoService;
import cn.jinronga.vo.TransFerAccoutsInfoVo;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-11-19
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements IAccountInfoService {

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    /**
     * 收钱
     */
    @Override
    @Transactional
    public String collectMoney(TransFerAccoutsInfoVo transFerAccoutsInfoVo) {
        AccountInfo accountInfo = accountInfoMapper.selectOne(Wrappers.<AccountInfo>query().lambda()
//                .eq(AccountInfo::getId, transFerAccoutsInfoVo.getUserId())
                .eq(AccountInfo::getAccountNo, transFerAccoutsInfoVo.getGatheringAccountNo()));

        if (ObjectUtils.isEmpty(accountInfo)) {
            return "eorr";
        }
        BigDecimal account = accountInfo.getAccountBalance().add(transFerAccoutsInfoVo.getMoneyNumber());

        accountInfo.setAccountBalance(account);
        accountInfoMapper.updateById(accountInfo);
        return "ok";
    }


}
