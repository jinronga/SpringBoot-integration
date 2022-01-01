package cn.jinronga.redis.tree.entity;

import cn.jinronga.redis.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "GroupDto", description = "用户组")
public class GroupDto  {

    private String id;

    @ApiModelProperty(value = "用户组名称")
    private String name;

    @ApiModelProperty(value = "用户组编码")
    private String code;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;

    @ApiModelProperty(value = "描述")
    private String remark;

    @ApiModelProperty(value = "用户组全路径路径")
    private String path;

    @ApiModelProperty(value = "租户ID")
    private String tenantId;

    @ApiModelProperty(value = "用户组类型（organization/project）")
    private String groupType;

    @ApiModelProperty(value = "用户组下的用户ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Long> users;

    @ApiModelProperty(value = "是否启用")
    private Integer enabled;

    @ApiModelProperty(value = "用户组所拥有的角色ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Long> roleIds;

    @ApiModelProperty(value = "负责人ID")
    private Long leaderId;

    @ApiModelProperty(value = "负责人名字")
    private String leaderName;

    @ApiModelProperty(value = "用户组下的用户")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<UserDto> userList;

    @ApiModelProperty(value = "oa_id")
    private Integer oaId;
    @ApiModelProperty(value = "税务机关级别")
    private String oaSwjgjb;
    @ApiModelProperty(value = "序号")
    private String oaXh;
    @ApiModelProperty(value = "备注")
    private String oaBz;
    @ApiModelProperty(value = "oa创建时间")
    private Date oaCreatedTime;
    @ApiModelProperty(value = "oa更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date oaUpdatedTime;
    @ApiModelProperty(value = "金三税务机关代码")
    private String jsSwjgDm;
    @ApiModelProperty(value = "金三税务机关名称")
    private String jsSwjgmc;
    @ApiModelProperty(value = "")
    private String jsSwjgjc;
    @ApiModelProperty(value = "")
    private String jsSwjgbz;
    @ApiModelProperty(value = "金三上级税务机关名称")
    private String jsSjswjgDm;

    private List<GroupDto> children = new ArrayList<>();
}