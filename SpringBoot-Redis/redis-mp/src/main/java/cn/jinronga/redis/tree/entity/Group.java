package cn.jinronga.redis.tree.entity;

import lombok.Data;

@Data
public class Group {

    private Long id;
    private String groupName;
    private Long parentId;
}

