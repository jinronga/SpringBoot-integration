package cn.jinronga.bank.service;

import cn.jinronga.bank.entity.AccountInfo;
import cn.jinronga.vo.TransFerAccoutsInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2021-11-19
 */
public interface IAccountInfoService extends IService<AccountInfo> {

    /**
     * shou
     */
    String collectMoney(TransFerAccoutsInfoVo transFerAccoutsInfoVo);



}
