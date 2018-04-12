package com.zzwdkj.biz.ctrl;

import com.hckj.framework.utils.Formatter;
import com.zzwdkj.base.entity.BaseDict;
import com.zzwdkj.base.service.BaseDictSV;
import com.zzwdkj.biz.entity.Biz;
import com.zzwdkj.biz.service.BizSV;
import com.zzwdkj.common.BaseConfig;
import com.zzwdkj.common.DateUtil;
import com.zzwdkj.common.Pager;
import com.zzwdkj.common.Std;
import com.zzwdkj.ord.entity.Ord;
import com.zzwdkj.ord.service.OrdSV;
import com.zzwdkj.prod.entity.Prod;
import com.zzwdkj.prod.entity.ProdCoinRpt;
import com.zzwdkj.prod.entity.ProdInstlPos;
import com.zzwdkj.prod.service.ProdCoinRptSV;
import com.zzwdkj.prod.service.ProdInstlPosSV;
import com.zzwdkj.prod.service.ProdSV;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stat")
public class StatCtrl {
    @Resource
    private BizSV bizSV;
    @Resource
    private OrdSV ordSV;
    @Resource
    private ProdSV prodSV;
    @Resource
    private BaseDictSV baseDictSV;
    @Resource
    private ProdInstlPosSV prodInstlPosSV;
    @Resource
    private ProdCoinRptSV prodCoinRptSV;

    @RequestMapping("/rpt")
    public String rpt(Pager<Prod> pager, String flag, String devType, String beginDate, String endDate, HttpServletRequest request, Model model) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", biz.getBizNo());
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        params.put("type", devType);
        params.put("devType", devType);
        params.put("flag", flag);
        pager.setAction("/stat/rpt");
        pager.setParams(params);
        //通过产品表和订单表查询出商家下的所有GPRS编号
        List<Prod> prodList =prodSV.queryProdByOrdBizNo(params, pager.getRowStart(), pager.getPageSize());
        if (prodList != null && !prodList.isEmpty()) {
            //处理统计具体方法
            procSta(biz.getBizNo(), flag, devType, beginDate, endDate, prodList, model);
            pager.setRecordList(prodList);
            pager.setTotalRow(prodSV.countProdByOrdBizNo(params));
        }
        List<BaseDict> dictList = baseDictSV.queryBaseDictByType(BaseDict.DictType.PROD_TYPE.getKey());
        model.addAttribute("dictList",dictList);
        model.addAttribute("flag", flag);
        model.addAttribute("devType", devType);
        return "/grzx/bizSta/bizStaList";
    }

    /**
     * 通过产品编号获取产品位置
     * @param prodNo 产品编号
     */
    @RequestMapping("/loadProdInstlPosByProdNo")
    public String loadProdInstlPosByProdNo(String prodNo,Model model){
        ProdInstlPos prodInstlPos= prodInstlPosSV.loadProdInstlPosByProdNo(prodNo);
        model.addAttribute("prodInstlPos",prodInstlPos);
        return "grzx/bizSta/prodInstlPosView";
    }


    @RequestMapping("/exportXsl")
    public void exportXsl(String flag, String devType, String beginDate, String endDate, Model model, HttpServletRequest request, HttpServletResponse response) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bizNo", biz.getBizNo());
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        params.put("type", devType);
        params.put("flag", flag);
        //List<Prod> prodList = prodSV.queryProd(params, 0, 20000);
        List<Prod> prodList =prodSV.queryProdByOrdBizNo(params, -1, -1);
        if (prodList != null && !prodList.isEmpty()) {
            if (StringUtils.isNotEmpty(beginDate) && StringUtils.isNotEmpty(endDate)) {
                beginDate = beginDate.replaceAll("-", "");
                endDate = endDate.replaceAll("-", "");
                procPeriodProdInc(devType, biz.getBizNo(), beginDate, endDate, prodList, model);
            } else if (StringUtils.isEmpty(flag) && (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate))) {
                procAllProdInc(devType, biz.getBizNo(), prodList, model);
            } else if (StringUtils.isNotEmpty(flag) && (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate))) {
                if ("day".equalsIgnoreCase(flag)) {
                    procCurrentDayProdInc(devType, biz.getBizNo(), prodList, model);
                }
                if ("week".equalsIgnoreCase(flag)) {
                    procCurrentWeekProdInc(devType, biz.getBizNo(), prodList, model);
                }
                if ("month".equalsIgnoreCase(flag)) {
                    procCurrentMonthProdInc(devType, biz.getBizNo(), prodList, model);
                }
            }
        }
        Map<String, Object> result = model.asMap();
        Object totalWxInc = result.get("totalWxInc");
        Object totalWltInc = result.get("totalWltInc");
        Object totalCardInc = result.get("totalCardInc");
        Object totalCoinInc = result.get("totalCoinInc");
        export(beginDate, endDate, prodList, totalWxInc.toString(), totalWltInc.toString(), totalCardInc.toString(), totalCoinInc.toString(), flag, biz.getAcct(), response);
    }


    /**
     * 商家管理
     */
    @RequestMapping("statSubBizInc")
    public String query(Pager<Biz> pager, String flag, String beginDate, String endDate, Model model, HttpServletRequest request) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parBizNo", biz.getBizNo());
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        params.put("flag", flag);
        pager.setAction("/stat/statSubBizInc");
        pager.setParams(params);
        if (StringUtils.isNotEmpty(beginDate) && StringUtils.isNotEmpty(endDate)) {
            model.addAttribute("beginDate", beginDate);
            model.addAttribute("endDate", endDate);
        }
        List<Biz> bizList = bizSV.queryBiz(params, pager.getRowStart(), pager.getPageSize());
        if (bizList != null && !bizList.isEmpty()) {
            procSubBizIncStat(bizList, flag, beginDate, endDate);
            model.addAttribute("flag", flag);
            pager.setRecordList(bizList);
            pager.setTotalRow(bizSV.countBiz(params));
        }
        return "ystj/subBizIncList";
    }

    /**
     * 商家管理
     */
    @RequestMapping("exportSubInc")
    public void exportSubInc(String flag, String beginDate, String endDate, Model model, HttpServletRequest request, HttpServletResponse response) {
        Biz biz = (Biz) request.getSession().getAttribute(BaseConfig.SESSION_VAR);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parBizNo", biz.getBizNo());
        params.put("beginDate", beginDate);
        params.put("endDate", endDate);
        params.put("flag", flag);

        List<Biz> bizList = bizSV.queryBiz(params, -1, -1);
        if (bizList != null && !bizList.isEmpty()) {
            procSubBizIncStat(bizList, flag, beginDate, endDate);
        }
        export(beginDate, endDate, bizList, flag, biz.getAcct(), response);
    }

    /**
     * 处理统计方法入口
     */
    private void procSta(String bizNo, String flag, String devType, String beginDate, String endDate, List<Prod> prodList, Model model) {
        if (StringUtils.isNotEmpty(beginDate) && StringUtils.isNotEmpty(endDate)) {
            model.addAttribute("beginDate", beginDate);
            model.addAttribute("endDate", endDate);
            beginDate = beginDate.replaceAll("-", "");
            endDate = endDate.replaceAll("-", "");
            procPeriodProdInc(devType, bizNo, beginDate, endDate, prodList, model);//时间段内统计
        } else if (StringUtils.isEmpty(flag) && (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate))) {
            procAllProdInc(devType, bizNo, prodList, model);//全部统计
        } else if (StringUtils.isNotEmpty(flag) && (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate))) {
            if ("day".equalsIgnoreCase(flag)) {
                procCurrentDayProdInc(devType, bizNo, prodList, model);//当日统计
            }
            if ("week".equalsIgnoreCase(flag)) {
                procCurrentWeekProdInc(devType, bizNo, prodList, model);//当周统计
            }
            if ("month".equalsIgnoreCase(flag)) {
                procCurrentMonthProdInc(devType, bizNo, prodList, model);//当月统计
            }
        }
    }

    /**
     * 处理时间段内收入统计
     */
    private void procPeriodProdInc(String devType, String bizNo, String beginDate, String endDate, List<Prod> prodList, Model model) {
        for (Prod prod : prodList) {
            //通过GPRS模块编号和商家编号查询出来订单金额
            Ord ord = ordSV.loadStaPeriodIncWithPerPayWayByProdNo(prod.getProdNo(), beginDate, endDate);
            ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaPeriodIncWithPerPayWayByProdNo(prod.getProdNo(), beginDate, endDate);
            BigDecimal coinInc = prodCoinRpt.getCoinInc();
            BigDecimal cardInc = prodCoinRpt.getCardInc();
            prod.setWltInc(ord.getWltInc());
            prod.setWxInc(ord.getWxInc());
            prod.setCardInc(cardInc);
            prod.setCoinInc(coinInc);
        }
        ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaPeriodTotalIncWithPerPayWay(devType, bizNo, beginDate, endDate);
        Ord ord = ordSV.loadStaPeriodTotalIncWithPerPayWay(devType, bizNo, beginDate, endDate);
        BigDecimal totalCardInc = prodCoinRpt.getCardInc();
        BigDecimal totalCoinInc = prodCoinRpt.getCoinInc();
        BigDecimal totalWltInc = ord.getWltInc();
        BigDecimal totalWxInc = ord.getWxInc();
        model.addAttribute("totalWxInc", totalWxInc);
        model.addAttribute("totalWltInc", totalWltInc);
        model.addAttribute("totalCardInc", totalCardInc);
        model.addAttribute("totalCoinInc", totalCoinInc);
    }

    /*
    * 处理全部统计收入
    * */
    private void procAllProdInc(String devType, String bizNo, List<Prod> prodList, Model model) {
        for (Prod prod : prodList) {
            ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaIncWithPerPayWayByProdNo(prod.getProdNo());
            Ord ord    = ordSV.loadStaNetIncPerPayWayByProdNo(prod.getProdNo());
            prod.setWltInc(ord.getWltInc());
            prod.setWxInc(ord.getWxInc());
            BigDecimal coinInc = prodCoinRpt.getCoinInc();
            BigDecimal cardInc = prodCoinRpt.getCardInc();
            prod.setCardInc(cardInc);
            prod.setCoinInc(coinInc);
        }
        ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaTotalIncWithPerPayWay(devType, bizNo);
        Ord ord = ordSV.loadStaTotalIncWithPerPayWay(devType, bizNo);
        BigDecimal totalCardInc = prodCoinRpt.getCardInc();
        BigDecimal totalCoinInc = prodCoinRpt.getCoinInc();
        BigDecimal totalWltInc = ord.getWltInc();
        BigDecimal totalWxInc = ord.getWxInc();
        model.addAttribute("totalWxInc", totalWxInc);
        model.addAttribute("totalWltInc", totalWltInc);
        model.addAttribute("totalCardInc", totalCardInc);
        model.addAttribute("totalCoinInc", totalCoinInc);
    }

    /*
     * 处理当日统计收入
     *
     */
    private void procCurrentDayProdInc(String devType, String bizNo, List<Prod> prodList, Model model) {
        for (Prod prod : prodList) {
            Ord ord = ordSV.loadStaDailyIncWithPerPayWayByProdNo(prod.getProdNo());
            ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaDayIncWithPerPayWayByProdNo(prod.getProdNo());
            BigDecimal coinInc = prodCoinRpt.getCoinInc();
            BigDecimal cardInc = prodCoinRpt.getCardInc();
            prod.setWxInc(ord.getWxInc());
            prod.setWltInc(ord.getWltInc());
            prod.setCardInc(cardInc);
            prod.setCoinInc(coinInc);
        }
        ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaDayTotalIncWithPerPayWay(devType, bizNo);
        Ord ord = ordSV.loadStaDailyTotalIncWithPerPayWay(devType, bizNo);
        BigDecimal totalCardInc = prodCoinRpt.getCardInc();
        BigDecimal totalCoinInc = prodCoinRpt.getCoinInc();
        BigDecimal totalWltInc = ord.getWltInc();
        BigDecimal totalWxInc = ord.getWxInc();
        model.addAttribute("totalWxInc", totalWxInc);
        model.addAttribute("totalWltInc", totalWltInc);
        model.addAttribute("totalCardInc", totalCardInc);
        model.addAttribute("totalCoinInc", totalCoinInc);
    }

    /*
     * 处理当周统计收入
     *
     */
    private void procCurrentWeekProdInc(String devType, String bizNo, List<Prod> prodList, Model model) {
        for (Prod prod : prodList) {
            Ord ord = ordSV.loadStaWeekIncWithPerPayWayByProdNo(prod.getProdNo());
            ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaWeekIncWithPerPayWayByProdNo(prod.getProdNo());
            BigDecimal coinInc = prodCoinRpt.getCoinInc();
            BigDecimal cardInc = prodCoinRpt.getCardInc();
            prod.setWltInc(ord.getWltInc());
            prod.setWxInc(ord.getWxInc());
            prod.setCardInc(cardInc);
            prod.setCoinInc(coinInc);
        }
        ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaWeekTotalIncWithPerPayWay(devType, bizNo);
        Ord ord = ordSV.loadStaWeekTotalIncWithPerPayWay(devType, bizNo);
        BigDecimal totalCardInc = prodCoinRpt.getCardInc();
        BigDecimal totalCoinInc = prodCoinRpt.getCoinInc();
        BigDecimal totalWltInc = ord.getWltInc();
        BigDecimal totalWxInc = ord.getWxInc();
        model.addAttribute("totalWxInc", totalWxInc);
        model.addAttribute("totalWltInc", totalWltInc);
        model.addAttribute("totalCardInc", totalCardInc);
        model.addAttribute("totalCoinInc", totalCoinInc);
    }

    /*
     * 处理当月统计收入
     *
     */
    private void procCurrentMonthProdInc(String devType, String bizNo, List<Prod> prodList, Model model) {
        for (Prod prod : prodList) {
            Ord ord = ordSV.loadStaMonthIncWithPerPayWayByProdNo(prod.getProdNo());
            ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaMonthIncWithPerPayWayByProdNo(prod.getProdNo());
            BigDecimal coinInc = prodCoinRpt.getCoinInc();
            BigDecimal cardInc = prodCoinRpt.getCardInc();
            prod.setWltInc(ord.getWltInc());
            prod.setWxInc(ord.getWxInc());
            prod.setCardInc(cardInc);
            prod.setCoinInc(coinInc);
        }
        ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaMonthTotalIncWithPerPayWay(devType, bizNo);
        Ord ord = ordSV.loadStaMonthTotalIncWithPerPayWay(devType, bizNo);
        BigDecimal totalCardInc = prodCoinRpt.getCardInc();
        BigDecimal totalCoinInc = prodCoinRpt.getCoinInc();
        BigDecimal totalWltInc = ord.getWltInc();
        BigDecimal totalWxInc = ord.getWxInc();
        model.addAttribute("totalWxInc", totalWxInc);
        model.addAttribute("totalWltInc", totalWltInc);
        model.addAttribute("totalCardInc", totalCardInc);
        model.addAttribute("totalCoinInc", totalCoinInc);
    }

    public void export(String beginDate, String endDate, List<Prod> prodList, String totalWxInc, String totalWltInc, String totalCardInc, String totalCoinInc, String flag, String bizAct, HttpServletResponse response) {
        //response.setContentType("octets/stream");
        response.setContentType("application/vnd.ms-excel");
        String excelName = "";
        if (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate)) {
            if (StringUtils.isEmpty(flag)) {
                excelName = bizAct + "_" + "(总营收)";
            } else if ("day".equals(flag)) {
                excelName = bizAct + "_" + Formatter.getCurFormalDate() + "_" + Formatter.getCurFormalDate() + "(日营收)";
            } else if ("week".equals(flag)) {
                excelName = bizAct + "_" + DateUtil.getMondayOfWeek() + "_" + Formatter.getCurFormalDate() + "(周营收)";
            } else if ("month".equals(flag)) {
                excelName = bizAct + "_" + Formatter.getCurMonthFirstDay() + "_" + Formatter.getCurFormalDate() + "(月营收)";
            }
        } else {
            excelName = bizAct + "_" + beginDate + "_" + endDate + "(时间段)";
        }

        //转码防止乱码
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(excelName.getBytes("gb2312"), "ISO8859-1") + ".xls");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] headers = new String[]{"产品编号","模块编号", "微信收入 ", "钱包收入", "刷卡收入", "投币收入"};
        try {
            OutputStream out = response.getOutputStream();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(excelName);
            //设置表格默认列宽度为15个字符
            sheet.setDefaultColumnWidth(20);
            //生成一个样式，用来设置标题样式
            HSSFCellStyle style = wb.createCellStyle();
            //设置这些样式
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //生成一个字体
            HSSFFont font = wb.createFont();
            font.setColor(HSSFColor.VIOLET.index);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            //把字体应用到当前的样式
            style.setFont(font);
            // Create a row and put some cells in it. Rows are 0 based.
            HSSFRow row = sheet.createRow(0);
            for (int j = 0; j < headers.length; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString text = new HSSFRichTextString(headers[j]);
                cell.setCellValue(text);
            }
            for (int i = 0; i < prodList.size(); i++) {
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(prodList.get(i).getProdNo());
                row.createCell(1).setCellValue(prodList.get(i).getGprsNo());
                row.createCell(2).setCellValue(prodList.get(i).getWxInc().toString());
                row.createCell(3).setCellValue(prodList.get(i).getWltInc().toString());
                row.createCell(4).setCellValue(prodList.get(i).getCardInc().toString());
                row.createCell(5).setCellValue(prodList.get(i).getCoinInc().toString());
            }
            row = sheet.createRow(prodList.size() + 1);
            // Create a cell and put a value in it.
            row.createCell(0).setCellValue("合计：");
            row.createCell(1).setCellValue("");
            row.createCell(2).setCellValue(totalWxInc);
            row.createCell(3).setCellValue(totalWltInc);
            row.createCell(4).setCellValue(totalCardInc);
            row.createCell(5).setCellValue(totalCoinInc);
            wb.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void procSubBizIncStat(List<Biz> bizList, String flag, String beginDate, String endDate) {
        BigDecimal totalInc = BigDecimal.ZERO;
        if (StringUtils.isEmpty(flag) && StringUtils.isNotEmpty(beginDate) && StringUtils.isNotEmpty(endDate)) {
            beginDate = beginDate.replaceAll("-", "");
            endDate = endDate.replaceAll("-", "");
            for (Biz biz1 : bizList) {
                /*修改人：苏方宁  时间：18-03-15*/
                BigDecimal netInc = ordSV.queryStaPeriodInc(biz1.getBizNo(), beginDate, endDate);
                BigDecimal wltInc = ordSV.queryStaPeriodWltInc(biz1.getBizNo(),beginDate,endDate);
                BigDecimal coinsInc = ordSV.queryStaPeriodCoinsInc(biz1.getBizNo(),beginDate,endDate);
                ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaPeriodTotalIncWithPerPayWay(null, biz1.getBizNo(), beginDate, endDate);
                biz1.setNetInc(netInc);
                biz1.setWltInc(wltInc);
                biz1.setCoinsInc(coinsInc);
                biz1.setCardInc(prodCoinRpt.getCardInc());
                biz1.setCoinInc(prodCoinRpt.getCoinInc());
                biz1.setTotalInc(totalInc.add(prodCoinRpt.getCardInc()).add(prodCoinRpt.getCoinInc()).add(netInc).add(wltInc).add(coinsInc));
                int online = prodSV.statProdOnLineCount(biz1.getBizNo());
                int offline = prodSV.statProdOffLineCount(biz1.getBizNo());
                biz1.setOnline(offline + "/" + online);
            }
        } else if (StringUtils.isEmpty(flag) && StringUtils.isEmpty(beginDate) && StringUtils.isEmpty(endDate)) {
            for (Biz biz1 : bizList) {
                ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaTotalIncWithPerPayWay(null, biz1.getBizNo());
                /*修改人：苏方宁  时间：18-03-15*/
                BigDecimal netInc = ordSV.queryStaNetInc(biz1.getBizNo());/*微信收入*/
                BigDecimal wltInc = ordSV.queryStaWltInc(biz1.getBizNo());/*钱包收入*/
                BigDecimal coinsInc= ordSV.queryStaCoinsInc(biz1.getBizNo());/*积分收入*/
                biz1.setNetInc(netInc);
                biz1.setWltInc(wltInc);
                biz1.setCoinsInc(coinsInc);
                biz1.setCardInc(prodCoinRpt.getCardInc());
                biz1.setCoinInc(prodCoinRpt.getCoinInc());
                biz1.setTotalInc(totalInc.add(prodCoinRpt.getCardInc()).add(prodCoinRpt.getCoinInc()).add(netInc).add(wltInc).add(coinsInc));
                int online = prodSV.statProdOnLineCount(biz1.getBizNo());
                int offline = prodSV.statProdOffLineCount(biz1.getBizNo());
                biz1.setOnline(offline + "/" + online);
            }
        } else if (StringUtils.isNotEmpty(flag)) {
            if ("day".equalsIgnoreCase(flag)) {
                for (Biz biz1 : bizList) {
                    /*修改人：苏方宁  时间：18-03-15*/
                    BigDecimal netInc = ordSV.queryStaDailyInc(biz1.getBizNo());/*微信每天收入*/
                    BigDecimal wltInc = ordSV.queryStaDailyWltInc(biz1.getBizNo());/*钱包每天收入*/
                    BigDecimal coinsInc= ordSV.queryStaDailyCoinsInc(biz1.getBizNo());/*积分每天收入*/
                    ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaDayTotalIncWithPerPayWay(null, biz1.getBizNo());
                    biz1.setNetInc(netInc);
                    biz1.setWltInc(wltInc);
                    biz1.setCoinsInc(coinsInc);
                    biz1.setCardInc(prodCoinRpt.getCardInc());
                    biz1.setCoinInc(prodCoinRpt.getCoinInc());
                    biz1.setTotalInc(totalInc.add(prodCoinRpt.getCardInc()).add(prodCoinRpt.getCoinInc()).add(netInc).add(wltInc).add(coinsInc));
                    int online = prodSV.statProdOnLineCount(biz1.getBizNo());
                    int offline = prodSV.statProdOffLineCount(biz1.getBizNo());
                    biz1.setOnline(offline + "/" + online);
                }
            }
            if ("week".equalsIgnoreCase(flag)) {
                for (Biz biz1 : bizList) {
                    /*修改人：苏方宁  时间：18-03-15*/
                    BigDecimal netInc = ordSV.queryStaWeekInc(biz1.getBizNo());/*微信每周收入*/
                    BigDecimal wltInc = ordSV.queryStaWeekWltInc(biz1.getBizNo());/*钱包每周收入*/
                    BigDecimal coinsInc= ordSV.queryStaWeekCoinsInc(biz1.getBizNo());/*积分每周收入*/
                    ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaWeekTotalIncWithPerPayWay(null, biz1.getBizNo());
                    biz1.setNetInc(netInc);
                    biz1.setWltInc(wltInc);
                    biz1.setCoinsInc(coinsInc);
                    biz1.setCardInc(prodCoinRpt.getCardInc());
                    biz1.setCoinInc(prodCoinRpt.getCoinInc());
                    biz1.setTotalInc(totalInc.add(prodCoinRpt.getCardInc()).add(prodCoinRpt.getCoinInc()).add(netInc).add(wltInc).add(coinsInc));
                    int online = prodSV.statProdOnLineCount(biz1.getBizNo());
                    int offline = prodSV.statProdOffLineCount(biz1.getBizNo());
                    biz1.setOnline(offline + "/" + online);
                }
            }
            if ("month".equalsIgnoreCase(flag)) {
                for (Biz biz1 : bizList) {
                    /*修改人：苏方宁  时间：18-03-15*/
                    BigDecimal netInc = ordSV.queryStaMonthInc(biz1.getBizNo());/*微信每月收入*/
                    BigDecimal wltInc = ordSV.queryStaMonthWltInc(biz1.getBizNo());/*钱包每月收入*/
                    BigDecimal coinsInc= ordSV.queryStaMonthCoinsInc(biz1.getBizNo());/*积分每月收入*/
                    ProdCoinRpt prodCoinRpt = prodCoinRptSV.loadStaMonthTotalIncWithPerPayWay(null, biz1.getBizNo());
                    biz1.setNetInc(netInc);
                    biz1.setNetInc(netInc);
                    biz1.setWltInc(wltInc);
                    biz1.setCoinsInc(coinsInc);
                    biz1.setCardInc(prodCoinRpt.getCardInc());
                    biz1.setCoinInc(prodCoinRpt.getCoinInc());
                    biz1.setTotalInc(totalInc.add(prodCoinRpt.getCardInc()).add(prodCoinRpt.getCoinInc()).add(netInc).add(wltInc).add(coinsInc));
                    int online = prodSV.statProdOnLineCount(biz1.getBizNo());
                    int offline = prodSV.statProdOffLineCount(biz1.getBizNo());
                    biz1.setOnline(offline + "/" + online);
                }
            }
        }
    }

    public void export(String beginDate, String endDate, List<Biz> bizList, String flag, String bizAct, HttpServletResponse response) {
        //response.setContentType("octets/stream");
        response.setContentType("application/vnd.ms-excel");
        String excelName = "";
        if (StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate)) {
            if (StringUtils.isEmpty(flag)) {
                excelName = bizAct + "_" + "(总营收)";
            } else if ("day".equals(flag)) {
                excelName = bizAct + "_" + Formatter.getCurFormalDate() + "_" + Formatter.getCurFormalDate() + "(日营收)";
            } else if ("week".equals(flag)) {
                excelName = bizAct + "_" + DateUtil.getMondayOfWeek() + "_" + Formatter.getCurFormalDate() + "(周营收)";
            } else if ("month".equals(flag)) {
                excelName = bizAct + "_" + Formatter.getCurMonthFirstDay() + "_" + Formatter.getCurFormalDate() + "(月营收)";
            }
        } else {
            excelName = bizAct + "_" + beginDate + "_" + endDate + "(时间段)";
        }

        //转码防止乱码
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(excelName.getBytes("gb2312"), "ISO8859-1") + ".xls");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] headers = new String[]{"商家", "微信收入 ","钱包收入","积分收入", "刷卡收入", "投币收入", "总收入", "设备在线"};
        try {
            OutputStream out = response.getOutputStream();
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(excelName);
            //设置表格默认列宽度为15个字符
            sheet.setDefaultColumnWidth(20);
            //生成一个样式，用来设置标题样式
            HSSFCellStyle style = wb.createCellStyle();
            //设置这些样式
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //生成一个字体
            HSSFFont font = wb.createFont();
            font.setColor(HSSFColor.VIOLET.index);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            //把字体应用到当前的样式
            style.setFont(font);
            // Create a row and put some cells in it. Rows are 0 based.
            HSSFRow row = sheet.createRow(0);
            for (int j = 0; j < headers.length; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(style);
                HSSFRichTextString text = new HSSFRichTextString(headers[j]);
                cell.setCellValue(text);
            }
            for (int i = 0; i < bizList.size(); i++) {
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(bizList.get(i).getName());
                row.createCell(1).setCellValue(bizList.get(i).getNetInc().toString());
                row.createCell(2).setCellValue(bizList.get(i).getWltInc().toString());
                row.createCell(3).setCellValue(bizList.get(i).getCoinsInc().toString());
                row.createCell(4).setCellValue(bizList.get(i).getCardInc().toString());
                row.createCell(5).setCellValue(bizList.get(i).getCoinInc().toString());
                row.createCell(6).setCellValue(bizList.get(i).getTotalInc().toString());
                row.createCell(7).setCellValue(bizList.get(i).getOnline());
            }
            wb.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
