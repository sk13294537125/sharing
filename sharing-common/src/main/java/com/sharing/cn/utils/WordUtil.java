package com.sharing.cn.utils;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordUtil {

    public static void create() {
// 创建一个空白文档
        XWPFDocument document = new XWPFDocument();

        // 添加标题
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER); // 居中对齐
        XWPFRun titleRun = title.createRun();
        titleRun.setText("北京金控集团外部董事履职台账");
        titleRun.setBold(true);
        titleRun.setFontSize(16);

        // 添加时间段
        XWPFParagraph dateRange = document.createParagraph();
        dateRange.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun dateRun = dateRange.createRun();
        dateRun.setText("（年月日至月日）");
        dateRun.setFontSize(12);

        // 添加一、基本情况
        addSectionHeader(document, "一、基本情况");
        addField(document, "外部董事姓名：");
        addField(document, "任职企业名称：");
        addField(document, "任职专委会名称与职务：");

        // 添加二、参加董事会情况
        addSectionHeader(document, "二、参加董事会情况");
        addField(document, "出席会议名称：");
        addField(document, "1.议题一：");
        addField(document, "会议表决结果：");
        addField(document, "发表的主要意见建议及采纳情况：");
        addField(document, "2.议题二：");
        addField(document, "会议表决结果：");
        addField(document, "发表的主要意见建议及采纳情况：");
        addField(document, "参会方式：");
        addField(document, "缺席与委托情况：");

        // 添加三、参加董事会专门委员会情况
        addSectionHeader(document, "三、参加董事会专门委员会情况");
        addField(document, "出席会议名称：");
        addField(document, "1.议题一：");
        addField(document, "发表的主要意见建议及采纳情况：");
        addField(document, "参会方式：");
        addField(document, "缺席与委托情况：");

        // 添加四、参加工作调研情况
        addSectionHeader(document, "四、参加工作调研情况");
        addField(document, "调研主题：");
        addField(document, "是否为外董个人独立调研：");
        addField(document, "是否有调研报告：");
        addField(document, "（如有报告或企业有价值的报告，可附加提供。）");

        // 添加五、列席或参加企业其他会议、活动情况
        addSectionHeader(document, "五、列席或参加企业其他会议、活动情况");
        addField(document, "活动名称：");

        // 添加六、参加董事会决议执行、董事会授权管理等监督情况
        addSectionHeader(document, "六、参加董事会决议执行、董事会授权管理等监督情况");
        addField(document, "活动名称：");
        addField(document, "参与方式：");

        // 添加七、其他服务企业情况
        addSectionHeader(document, "七、其他服务企业情况");
        addField(document, "如，提供培训授课、咨询论证等");

        // 添加八、其他情况
        addSectionHeader(document, "八、其他情况");

        // 添加填写人及联系方式
        XWPFParagraph footer = document.createParagraph();
        XWPFRun footerRun = footer.createRun();
        footerRun.setText("填写人及联系方式：");
        footerRun.addBreak();
        footerRun.setText("董事会办公室负责人审核：");
        footerRun.addBreak();
        footerRun.setText("填报时间：年月日");

        // 保存文档
        try (FileOutputStream out = new FileOutputStream("北京金控集团外部董事履职台账.docx")) {
            document.write(out);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 添加章节标题
    private static void addSectionHeader(XWPFDocument document, String headerText) {
        XWPFParagraph header = document.createParagraph();
        XWPFRun headerRun = header.createRun();
        headerRun.setText(headerText);
        headerRun.setBold(true);
        headerRun.setFontSize(14);
    }

    // 添加字段
    private static void addField(XWPFDocument document, String fieldText) {
        XWPFParagraph field = document.createParagraph();
        XWPFRun fieldRun = field.createRun();
        fieldRun.setText(fieldText);
        fieldRun.setFontSize(12);
    }

}
