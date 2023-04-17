package com.shugram.demo.utils;

import com.shugram.demo.entity.RouterEntity;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class  Util {
    //sourceList 平铺的原始数据集合
    //sourceList 平铺的原始数据集合
    public static List<RouterEntity> list2Tree(List<RouterEntity> sourceList) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Collections.emptyList();
        }
        List<RouterEntity> resultList = new ArrayList<>();
        //将数据封装成树形结构
        Map<Long,RouterEntity> map = new HashMap<>();
        for (RouterEntity data : sourceList) {
            map.put(data.getRouterId(), data);
        }
        for (RouterEntity data : sourceList) {
            RouterEntity obj = map.get(data.getParentId());
            if (obj != null) {
                List<RouterEntity> children = obj.getChildren();
                if (children == null || children.isEmpty()) {
                    children = new ArrayList<>();
                }
                children.add(data);
                obj.setChildren(children);

            } else {
                resultList.add(data);
            }

        }
        return resultList;
    }


}
