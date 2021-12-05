package cn.jinronga.bank.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class LocalConfirmLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事务id
     */
    private String txNo;

    private LocalDateTime createTime;


}
