package cn.jinronga.redis.tree;

import cn.jinronga.redis.tree.utils.TreeUtils;

public class GroupPathTree {



    public static void main(String[] args) {
        String paths[] = {
                "/RuoYi科技股份有限公司/RuoYi科技系统云桥",
                "/RuoYi科技股份有限公司/RuoYi科技系统云桥/RuoYi科技投资有限公司",
                "/RuoYi科技股份有限公司/基础架构平台部",
                "/RuoYi科技股份有限公司/基础架构平台部/运维中台中心",
                "/RuoYi科技股份有限公司/基础架构平台部/运维中台中心/产品开发部门/质量管理组",
                "/RuoYi科技股份有限公司/RuoYi科技投资有限公司",
                "/RuoYi科技股份有限公司/基础架构平台部/IT桌面运维中台部",
                "/RuoYi科技股份有限公司/基础架构平台部/运维中台中心/产品开发部门/平台开发组",
                "/RuoYi科技股份有限公司/RuoYi科技投资有限公司",
                "/RuoYi科技股份有限公司/RuoYi科技投资有限公司/广州RuoYi科技分行子公司",
                "/RuoYi科技股份有限公司/基础架构平台部/运维中台中心/运维开发团队/数据库平台开发者"
        };
//        TreeUtils.cutGroupPathsStr("/RuoYi科技股份有限公司/RuoYi科技系统云桥/RuoYi科技投资有限公司",2);
        TreeUtils.convertGroupTrees(paths);

    }


}
