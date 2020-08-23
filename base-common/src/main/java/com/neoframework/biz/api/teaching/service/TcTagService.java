
package com.neoframework.biz.api.teaching.service;

import com.neoframework.biz.api.teaching.model.TcTag;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import java.util.List;

/**
 * 教材标签 Service.
 */
public interface TcTagService {

    /**
     * 获取某标签及其全部父亲, 父类在前.
     *
     * @param entity 带 id 和 parent_ids
     * @return 列表
     */
    List<TcTag> findToParents(TcTag entity);

    /**
     * 查找指定id列表对应的标签.
     *
     * @param ids ID列表
     * @return 列表
     */
    List<TcTag> getTagsByIds(List<String> ids);

    /**
     * 获取对应年级的年级tag(根据relat_level值映射).
     *
     * @param offices 年级等信息
     * @return 列表
     */
    List<TcTag> getTagsByRelatLevels(List<Office> offices);
}
