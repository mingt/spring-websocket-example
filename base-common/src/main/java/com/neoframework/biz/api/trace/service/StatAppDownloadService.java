
package com.neoframework.biz.api.trace.service;

import com.neoframework.biz.api.trace.model.StatAppDownload;
import com.neoframework.biz.api.trace.model.StatResourceAction;
import java.util.List;

/**
 * 下载上报 service .
 *
 */
public interface StatAppDownloadService {

    /**
     * 根据 ID 获取单个.
     *
     * @param id the id
     * @return by id
     */
    public StatAppDownload getById(Long id);

    /**
     * 另一个例子.
     *
     * @param id the id
     * @return by id sample 2
     */
    public StatAppDownload getByIdSample2(Long id);

    /**
     * 插入数据
     *
     * @param download the download
     * @return int
     */
    public int insert(StatAppDownload download);

    /**
     * 批量插入数据
     *
     * @param actions the actions
     * @return int
     */
    public int batchAddActions(List<StatResourceAction> actions);
}
