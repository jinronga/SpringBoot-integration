package cn.jinronga.redis.tree.utils;

import cn.jinronga.redis.tree.entity.Group;
import cn.jinronga.redis.tree.entity.Tree;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class TreeUtils {

    public static String cutGroupPathsStr(String groupPaths, int level) {
        if (groupPaths.isEmpty() || StringUtils.isEmpty(groupPaths)) {
            return null;
        }
        if (String.valueOf(groupPaths.charAt(0)).equals("/")) {
            groupPaths = groupPaths.replaceFirst("/", "");
        }

        StringBuffer resultStr = new StringBuffer();

        for (String s : Arrays.asList(groupPaths.split("/"))) {
            resultStr = level > 0 ? resultStr.append(s + "/") : resultStr.append(s);
            level--;
            if (level < 0) {
                break;
            }
        }
        log.info("resultStr:{}", resultStr);
        return resultStr.toString();
    }


    public static List<Group> convertGroupTrees(String[] paths) {

        if (paths.length > 0) {
            List<String> pathList = Arrays.asList(paths);
           List<Group> groupArrayList = new ArrayList<>();
            pathList.forEach(item -> {
                List<String> pathS = Arrays.asList(item.split("/"));
                if (pathS.size() > 0) {
                    for (int i = 0; i < pathS.size(); i++) {
                        Random rand =new Random(100);

                        Group group = new Group();
                        if (i == 0) {
                            group.setParentId(rand.nextLong());
                        }
                        group.setId((long) i);
                        group.setGroupName(pathS.get(i));
                        group.setParentId(rand.nextLong());
                        //去重已经存在的子集部门
                        initGroupInfo(groupArrayList,group);
                        groupArrayList.add(group);
                    }
                }
            });
            return groupArrayList;
        }
        return null;
    }


  static   void aa() {
        String paths[] = {
                "/RuoYi科技股份有限公司/RuoYi科技OA系统云桥",
                "/RuoYi科技股份有限公司/RuoYi科技系统云桥/RuoYi科技投资有限公司",
                "/RuoYi科技股份有限公司/基础架构平台部",
                "/RuoYi科技股份有限公司/基础架构平台部/运维中台中心",
                "/RuoYi科技股份有限公司/基础架构平台部/运维中台中心/产品开发部门/质量管理组",
                "/RuoYi科技股份有限公司/RuoYi科技投资有限公司",
                "/RuoYi科技股份有限公司/基础架构平台部/IT桌面运维中台部",
                "/RuoYi科技股份有限公司/基础架构平台部/运维中台中心/产品开发部门/平台开发组",
                "/RuoYi科技股份有限公司/RuoYi科技投资有限公司",
                "/RuoYi科技股份有限公司/RuoYi科技投资有限公司/广州RuoYi科技分行子公司",
                "/RuoYi科技股份有限公司/基础架构平台部/运维中台中心/运维开发团队/数据库平台开发者",
                "RuoYi科技有限公司/运维开发团队/数据库平台开发者",
                "RuoYi科技有限公司/基础架构平台部/IT桌面运维中台部",
                "RuoYi科技有限公司/运维中台中心/产品开发部门/平台开发组",
        };
        HashMap<String, String> hashMap = new HashMap<>();
        for (String path : paths) {
            String subPath = path.substring(1) + "/";
            String[] split = subPath.split("/");
            for (int i = 1; i < split.length; i++) {
                hashMap.put(subPath.substring(0, StringUtils.lastOrdinalIndexOf(subPath, "/", i)), "");
            }
        }
        ArrayList<Tree> nodes = new ArrayList<>();
        hashMap.put("RuoYi科技股份有限公司", "");
        hashMap.put("RuoYi科技有限公司","");
        hashMap.forEach((k, v) -> {
            Tree tree = new Tree();
            tree.setId(k);
            int index = k.lastIndexOf("/");
            if (index == -1) {
                tree.setPid("-1");
            } else {
                String pid = k.substring(0, index);
                tree.setPid(pid);
            }
            nodes.add(tree);
        });
        ArrayList<Tree> rootNodes = new ArrayList<>();
        nodes.forEach(node -> {
            if (node.getPid().equals("-1")) {
                rootNodes.add(node);
            }
        });
        rootNodes.forEach(node->{
            dg(nodes, node);
        });
      String toJSONString = JSON.toJSONString(rootNodes);
      System.out.println(toJSONString);
    }

    private static void dg(List<Tree> trees, Tree tree) {
        // 找出所有子节点
        for (int i = 0; i < trees.size(); i++) {
            if (trees.get(i).getPid().equals(tree.getId())) {
                tree.getChildPaths().add(trees.get(i));
                dg(trees, trees.get(i));
            }
        }
    }


    public static void initGroupInfo(List<Group> groupArrayList, Group newGroup){
        groupArrayList.forEach(group->{
            if (group.getGroupName().equals(newGroup.getGroupName())){
                newGroup.setGroupName(group.getGroupName());
                newGroup.setParentId(group.getParentId());
                newGroup.setId(group.getId());
            }
        });

    }



    public static void main(String[] args) {
        aa();
    }
    public boolean filterGroupName(List<Group> groups,String name){

        List<String> tempName = groups.stream().map(Group::getGroupName).collect(Collectors.toList());
        if (tempName.contains(name)) {
            return true;
        }
        return false;
    }
}
