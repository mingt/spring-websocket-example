
package com.thinkgem.jeesite.modules.sys.entity;

import com.thinkgem.jeesite.common.persistence.TreeEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 区域 Entity .
 *
 * @author ThinkGem
 * @version 2013-05-15
 */
public class Area extends TreeEntity<Area> {

    private static final long serialVersionUID = 1L;
    private String code; // 区域编码
    // private String name; // 区域名称
    // private Integer sort; // 排序
    private String type; // 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）

    private Integer no; // 编号
    private Integer parentNo; // 父编号
    private String shortName; // 短名称
    private String cityCode; // 区号
    private String zipCode; // 邮编
    private String mergerName; // 合并名称
    private Double lng; // 经度
    private Double lat; // 纬度
    private String pinyin; // 拼音
    private Boolean ifShow; // 是否显示：1是0否

    /**
     * Instantiates a new Area.
     */
    public Area() {
        super();
        this.sort = 30;
    }

    /**
     * Instantiates a new Area.
     *
     * @param id the id
     */
    public Area(String id) {
        super(id);
    }

    // @JsonBackReference
    // @NotNull
    public Area getParent() {
        return parent;
    }

    public void setParent(Area parent) {
        this.parent = parent;
    }

    // @ExcelField(title = "区域名称", type = 2, sort = 20)
    @Length(min = 1, max = 100)
    public String getName() {
        return name;
    }

    @Length(min = 1, max = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    @Length(min = 0, max = 100)
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets no.
     *
     * @return the no
     */
    // @ExcelField(title = "编号", type = 2, sort = 10)
    public Integer getNo() {
        return no;
    }

    /**
     * Sets no.
     *
     * @param no the no
     */
    public void setNo(Integer no) {
        this.no = no;
    }

    /**
     * Gets parent no.
     *
     * @return the parent no
     */
    // @ExcelField(title = "父编号", type = 2, sort = 30)
    public Integer getParentNo() {
        return parentNo;
    }

    /**
     * Sets parent no.
     *
     * @param parentNo the parent no
     */
    public void setParentNo(Integer parentNo) {
        this.parentNo = parentNo;
    }

    /**
     * Gets short name.
     *
     * @return the short name
     */
    // @ExcelField(title = "短名称", type = 2, sort = 40)
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets short name.
     *
     * @param shortName the short name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    // @ExcelField(title = "层次", type = 2, sort = 50)
    public Integer getLevel() {
        return level;
    }

    /**
     * Gets city code.
     *
     * @return the city code
     */
    // @ExcelField(title = "区号", type = 2, sort = 60)
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Sets city code.
     *
     * @param cityCode the city code
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    // @ExcelField(title = "区域邮编", type = 2, sort = 70)
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets zip code.
     *
     * @param zipCode the zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets merger name.
     *
     * @return the merger name
     */
    // @ExcelField(title = "合并名称", type = 2, sort = 80)
    public String getMergerName() {
        return mergerName;
    }

    /**
     * Sets merger name.
     *
     * @param mergerName the merger name
     */
    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    /**
     * Gets lng.
     *
     * @return the lng
     */
    // @ExcelField(title = "经度", type = 2, sort = 90)
    public Double getLng() {
        return lng;
    }

    /**
     * Sets lng.
     *
     * @param lng the lng
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * Gets lat.
     *
     * @return the lat
     */
    // @ExcelField(title = "纬度", type = 2, sort = 100)
    public Double getLat() {
        return lat;
    }

    /**
     * Sets lat.
     *
     * @param lat the lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * Gets pinyin.
     *
     * @return the pinyin
     */
    // @ExcelField(title = "拼音", type = 2, sort = 110)
    public String getPinyin() {
        return pinyin;
    }

    /**
     * Sets pinyin.
     *
     * @param pinyin the pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * Gets if show.
     *
     * @return the if show
     */
    public Boolean getIfShow() {
        return this.ifShow;
    }

    /**
     * Sets if show.
     *
     * @param ifShow the if show
     */
    public void setIfShow(Boolean ifShow) {
        this.ifShow = ifShow;
    }

}
