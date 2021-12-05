package cn.jinronga.bank.service.impl;

import cn.jinronga.bank.entity.UndoLog;
import cn.jinronga.bank.mapper.UndoLogMapper;
import cn.jinronga.bank.service.IUndoLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-11-19
 */
@Service
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper, UndoLog> implements IUndoLogService {

}
