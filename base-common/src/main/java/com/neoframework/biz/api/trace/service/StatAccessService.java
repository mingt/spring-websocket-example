
package com.neoframework.biz.api.trace.service;

import com.neoframework.biz.api.trace.model.StatAccess;
import java.util.List;

/**
 * 用户访问上报服务.
 *
 */
public interface StatAccessService {

    /**
     * 根据id获取数据.
     *
     * @param id the id
     * @return by id
     */
    StatAccess getById(Long id);

    /**
     * 插入数据
     *
     * @param statAccess the stat access
     * @return int
     */
    int insert(StatAccess statAccess);

    /**
     * 批量插入数据
     *
     * @param statAccessList the stat access list
     * @return int
     */
    int insertBatch(List<StatAccess> statAccessList);

}
