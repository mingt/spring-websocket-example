
package com.neoframework.biz.api.teaching.model.easypoi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Date: 2020/8/4 Ahming
 */
@RunWith(JUnit4.class)
public class QuestionVoExcelTest {

    @Test
    public void setAnswerContent() {
        final String content1 = "选项1##选项2";
        final String content2 = "选项1##";
        final String content3 = "##";
        final String content4 = "";
        final String content5 = " ## ";

        // QuestionVoExcel voExcel1 = new QuestionVoExcel();
        // voExcel1.setAnswerContent(content1);
        // Assert.assertTrue(voExcel1.getTcAnswerItemSet() != null &&
        // voExcel1.getTcAnswerItemSet().size() == 2);
        //
        // QuestionVoExcel voExcel2 = new QuestionVoExcel();
        // voExcel2 = new QuestionVoExcel();
        // voExcel2.setAnswerContent(content2);
        // Assert.assertTrue(voExcel2.getTcAnswerItemSet() != null &&
        // voExcel2.getTcAnswerItemSet().size() == 1);
        //
        // QuestionVoExcel voExcel3 = new QuestionVoExcel();
        // voExcel3 = new QuestionVoExcel();
        // voExcel3.setAnswerContent(content3);
        // Assert.assertTrue(voExcel3.getTcAnswerItemSet() != null &&
        // voExcel3.getTcAnswerItemSet().size() == 0);
        //
        // QuestionVoExcel voExcel4 = new QuestionVoExcel();
        // voExcel4 = new QuestionVoExcel();
        // voExcel4.setAnswerContent(content4);
        // Assert.assertTrue(voExcel4.getTcAnswerItemSet() != null &&
        // voExcel4.getTcAnswerItemSet().size() == 0);
        //
        // QuestionVoExcel voExcel5 = new QuestionVoExcel();
        // voExcel5 = new QuestionVoExcel();
        // voExcel5.setAnswerContent(content5);
        // Assert.assertTrue(voExcel5.getTcAnswerItemSet() != null &&
        // voExcel5.getTcAnswerItemSet().size() == 0);
    }

    @Test
    public void getAnswerContent() {
        // QuestionVoExcel voExcel1 = new QuestionVoExcel();
        // Assert.assertEquals("", voExcel1.getAnswerContent());
        //
        // QuestionVoExcel voExcel2 = new QuestionVoExcel();
        // voExcel2.setAnswerContent("");
        // Assert.assertEquals("", voExcel2.getAnswerContent());
        //
        // QuestionVoExcel voExcel3 = new QuestionVoExcel();
        // voExcel3.setAnswerContent("答案1");
        // Assert.assertEquals("答案1", voExcel3.getAnswerContent());
        //
        // QuestionVoExcel voExcel4 = new QuestionVoExcel();
        // voExcel4.setAnswerContent("选项1##选项2");
        // Assert.assertEquals("选项1##选项2", voExcel4.getAnswerContent());
    }
}
