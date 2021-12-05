package cn.jinronga.bank.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LocalCancelLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事务id
     */
    private String txNo;

    private LocalDateTime createTime;


}
