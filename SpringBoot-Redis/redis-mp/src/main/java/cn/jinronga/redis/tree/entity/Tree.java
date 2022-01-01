package cn.jinronga.redis.tree.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Tree {
    private String pid;
    private String id;
    private List<Tree> childPaths = new ArrayList<>();
}
