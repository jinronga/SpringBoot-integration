package cn.jinronga.feign;

import cn.jinronga.vo.TransactionVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient(contextId = "transactionGatherService", value = "bank2")
public interface TransactionGatherFeignService {

    /**
     *
     * @param transactionVo
     * @return
     */
    @PostMapping("/bank1/account-info/collectMoney")
    String transferAccounts(TransactionVo transactionVo);


}
