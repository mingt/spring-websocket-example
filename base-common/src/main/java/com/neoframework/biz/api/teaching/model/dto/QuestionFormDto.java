
package com.neoframework.biz.api.teaching.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.StringUtils;
import java.io.Serializable;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.validator.constraints.Length;

/**
 * Model class of 题目 DTO.
 *
 * <p>backend 题目编辑改为 RequestBody 接收参数，要避免 JsonIgnore 的影响，所以另起 DTO .</p>
 */
public class QuestionFormDto extends DataEntity<QuestionFormDto> implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * id.
     */
    private String id;

    /**
     * 教材id.
     */
    private String bookId;

    /**
     * 题目类型.
     */
    private Integer type;

    /**
     * 题目类型名称.
     */
    private String typeName;

    /**
     * 特定显示名称,例如听力选择题.
     */
    private String titleName;

    /**
     * 题目内容.
     */
    private String content;

    /**
     * 选择题选项.
     */
    private String options;

    /**
     * 正确答案选项 -> 选择题和填空题的答案最终在 tc_answer_item(tcAnswerItemSet) 中反应，其他在此字段
     */
    private String answerContent;

    /**
     * 题解或题目分析等
     */
    private String solution;

    /**
     * 传递问题出题顺序
     */
    private Integer sort;
    /**
     * 传递问题分数
     */
    private Double points;
    /**
     * 该题目是否共享
     */
    private Boolean ifShared;
    /**
     * 共享范围
     */
    private String sharedScope;

    /**
     * Constructor.
     */
    public QuestionFormDto() {}

    /**
     * Constructor.
     *
     * @param id the id
     */
    public QuestionFormDto(String id) {
        this();
        this.id = id;
    }

    /**
     * Set the id.
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the id.
     *
     * @return id
     */
    // @ExcelField(title = "ID", type = 0, align = 2, sort = 10000)
    public String getId() {
        return this.id;
    }

    /**
     * Set the 教材id.
     *
     * @param bookId 教材id
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * Get the 教材id.
     *
     * @return 教材id book id
     */
    @JsonIgnore
    public String getBookId() {
        return this.bookId;
    }

    /**
     * Set the 题目类型.
     *
     * @param type 题目类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * Get the 题目类型.
     *
     * @return 题目类型 type
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * Gets title name.
     *
     * @return the title name
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * Sets title name.
     *
     * @param titleName the title name
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    /**
     * Gets type name.
     *
     * @return the type name
     */
    @Length(min = 1, max = 20, message = "题目类型长度介于1和20之间")
    // @ExcelField(title = "题目类型（必填，分单选题、填空题、解答题、写作题、判断题）", align = 2, sort = 20) // 题目类型
    public String getTypeName() {
        return typeName;
    }

    /**
     * Sets type name.
     *
     * @param typeName the type name
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Set the 题目内容.
     *
     * @param content 题目内容
     */
    public void setContent(String content) {
        this.content = StringUtils.replaceHtml(StringEscapeUtils.unescapeHtml4(content));
    }

    /**
     * Get the 题目内容.
     *
     * @return 题目内容 content
     */
    @Length(min = 1, max = 4000, message = "题目描述长度介于1和4000之间")
    // @ExcelField(title = "题目内容（必填）", align = 1, sort = 25)
    public String getContent() {
        return this.content;
    }

    /**
     * Gets options.
     *
     * @return the options
     */
    @Length(max = 2000, message = "选择题选项最大长度为2000")
    // @ExcelField(title = "选项(选择题必填)", align = 1, sort = 30)
    public String getOptions() {
        return options;
    }

    /**
     * Sets options.
     *
     * @param options the options
     */
    public void setOptions(String options) {
        this.options = StringUtils.replaceHtml(StringEscapeUtils.unescapeHtml4(options));
    }

    /**
     * Gets solution.
     *
     * @return the solution
     */
    @Length(max = 4000, message = "题目导读最大长度为2000")
    // @ExcelField(title = "题目分析(选填)", align = 1, sort = 740) // 740 改到答案之后 27 // 原：题目解读
    public String getSolution() {
        return solution;
    }

    /**
     * Sets solution.
     *
     * @param solution the solution
     */
    public void setSolution(String solution) {
        this.solution = StringUtils.replaceHtml(StringEscapeUtils.unescapeHtml4(solution));
    }

    /**
     * Gets sort.
     *
     * @return the sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * Sets sort.
     *
     * @param sort the sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public Double getPoints() {
        return points;
    }

    /**
     * Sets points.
     *
     * @param points the points
     */
    public void setPoints(Double points) {
        this.points = points;
    }

    /**
     * Gets if shared.
     *
     * @return the if shared
     */
    // @JsonIgnore
    public Boolean getIfShared() {
        return ifShared;
    }

    /**
     * Sets if shared.
     *
     * @param ifShared the if shared
     */
    public void setIfShared(Boolean ifShared) {
        this.ifShared = ifShared;
    }

    /**
     * Gets shared scope.
     *
     * @return the shared scope
     */
    // @JsonIgnore
    public String getSharedScope() {
        return sharedScope;
    }

    /**
     * Sets shared scope.
     *
     * @param sharedScope the shared scope
     */
    public void setSharedScope(String sharedScope) {
        this.sharedScope = sharedScope;
    }

    /**
     * Gets answer content.
     *
     * @return the answer content
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * Sets answer content.
     *
     * @param answerContent the answer content
     */
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        QuestionFormDto other = (QuestionFormDto) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
}
