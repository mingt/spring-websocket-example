
package com.neoframework.common.api;

import java.io.Serializable;

/**
 * Created by think on 2018-06-06.
 *
 * @param <T> the type parameter
 */
public class BaseModel<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // TODO: 处理好分页。例如引进 page-helper

    // /**
    //  * 当前实体分页对象.
    //  * Refers to: BaseEntity#page
    //  */
    // protected Page<T> page;
    //
    // @JsonIgnore
    // @XmlTransient
    // public Page<T> getPage() {
    //     // //modify by zhangshun 不需要默认值
    //     // if (page == null){
    //     // page = new Page<T>();
    //     // }
    //     return page;
    // }
    //
    // public Page<T> setPage(Page<T> page) {
    //     this.page = page;
    //     return page;
    // }
}
