package com.zzwdkj.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 分页处理
 *
 * @author guoxianwei 2015-02-07 16:12
 */
public class Pager<T> {


    /**
     * 总记录行数
     */
    private int totalRow = 0;
    /**
     * 当前页，当传递到后台时候，就是要获取的页，从1开始
     */
    private int curPage = 1;
    /**
     * 分页大小(从系统公共配置中获取)
     */
    private int pageSize = 20;

    private String url = "";

    private Map<String, Object> params;

    private String action;

    private String formPageStr;

    private int pageLength = 3;

    private String formName = "pageForm";

    private List<T> recordList;

    /**
     * 默认构造方法，所有的构造方法当中，总会初始化voList和params
     */
    public Pager() {
        if (curPage > getTotalPage()) this.curPage = getTotalPage();
        if (curPage < 1) curPage = 1;
    }

    /**
     * Page构造方法
     *
     * @param pageSize 分页大小
     */
    public Pager(int pageSize) {
        this.pageSize = pageSize;
        if (curPage > getTotalPage()) this.curPage = getTotalPage();
        if (curPage < 1) curPage = 1;
    }

    /**
     * Page构造方法
     *
     * @param pageSize 分页大小
     * @param curPage  当前页
     */
    public Pager(int pageSize, int curPage) {
        this.pageSize = pageSize;
        this.curPage = curPage;
        if (curPage > getTotalPage()) this.curPage = getTotalPage();
        if (curPage < 1) this.curPage = 1;
    }

    /**
     * Page构造方法
     *
     * @param pageSize 分页大小
     * @param curPage  当前页
     * @param totalRow 总行数
     */
    public Pager(int pageSize, int curPage, int totalRow) {
        this.totalRow = totalRow;
        this.pageSize = pageSize;
        this.curPage = curPage;
        if (curPage > getTotalPage()) this.curPage = getTotalPage();
        if (curPage < 1) curPage = 1;
    }

    /**
     * 获取总页数
     *
     * @return 总页数
     */
    public int getTotalPage() {
        return totalRow <= pageSize ? 1 : (totalRow + pageSize - 1) / pageSize;
    }

    /**
     * 获取起始行数,不包含起始行,对于MySQL来说，limit 0，x 表示，从0行开始，不含0行，取x条记录。
     *
     * @return 起始行数
     */
    public int getRowStart() {
        if (curPage < 1) curPage = 1;
        return curPage * pageSize - pageSize;
    }

    /**
     * 是否最后一页
     *
     * @return 是否最后一页，是返回true，否返回false
     */
    public boolean isLastPage() {
        if (totalRow == 0 || totalRow <= pageSize) return true;
        if (curPage == ((totalRow + pageSize - 1) / pageSize)) return true;
        return false;
    }

    public void setFormPageStr(String formPageStr) {
        this.formPageStr = formPageStr;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public List<T> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }

    /**
     * 获取html字符串输出到页面
     *
     * @return
     */
    public String getFormPageStr() {
        StringBuffer sb = new StringBuffer("");
        if (totalRow > 0 && curPage <= getTotalPage()) {
            sb.append("<form name='" + formName + "' method='post' action=\"" + action + "\" onkeydown=\"if(event.keyCode==13){return false;}\">\n");
            sb.append("<input type='hidden' name='curPage' value=''>\n");
            if (params != null && params.size() > 0) {
                Iterator<String> keys = params.keySet().iterator();
                String name = "";
                while (keys.hasNext()) {
                    name = keys.next();
                    if (params.get(name) != null && !"null".equals(params.get(name))) {
                        sb.append("<input type='hidden' name='" + name + "' value='" + params.get(name) + "'>\n");
                    }
                }
            }
            sb.append("<span>\n");
            int diff = (curPage % pageLength == 0 ? 3 : curPage % pageLength);
            int startPage = (curPage - diff) + 1;
            int offset = (startPage + pageLength) >= getTotalPage() ? getTotalPage() : (startPage + pageLength);
            if ((offset - 1) % 3 != 0 || startPage==offset) {
                offset = offset + 1;
            }
            String _can_prev = curPage <= pageLength ? "class=\"disabled\"" : "";
            String _can_next = offset - 1 >= getTotalPage() ? "class=\"disabled\"" : "";

            String _be_first = curPage <= 1 ? "class=\"disabled\"" : "";
            String _be_last = curPage >= getTotalPage() ? "class=\"disabled\"" : "";

            String page_first, page_last, page_prev, page_next;

            int prev_page_num = 1;

            if (curPage <= 1) {
                page_first = "<img src='/static/img/page-first-disabled.gif' width='13px' height='13px'/>";
            } else {
                page_first = "<img src='/static/img/page-first-disabled.gif' width='13px' height='13px'/>";
            }
            if (curPage >= getTotalPage()) {
                page_last = "<img src='/static/img/page-last-disabled.gif' width='13px' height='13px'/>";
            } else {
                page_last = "<img src='/static/img/page-last-disabled.gif' width='13px' height='13px'/>";
            }
            if (curPage <= pageLength) {
                page_prev = "<img src='/static/img/page-prev-disabled.gif' width='13px' height='13px'/>";
            } else {
                page_prev = "<img src='/static/img/page-prev-disabled.gif' width='13px' height='13px'/>";
            }
            if (offset >= getTotalPage()) {
                page_next = "<img src='/static/img/page-next-disabled.gif' width='13px' height='13px'/>";
            } else {
                page_next = "<img src='/static/img/page-next-disabled.gif' width='13px' height='13px'/>";
            }
            if (getTotalPage() > 0) {
                if (curPage >= getTotalPage()) {
                    prev_page_num = getTotalPage() - (getTotalPage() % 3 == 0 ? 3 : getTotalPage() % 3) - 2;
                } else {
                    prev_page_num = getCurPage() - (getCurPage() % 3 == 0 ? 3 : getCurPage() % 3) - 2;
                }
            }
            sb.append("<a href=\"javascript:" + formName + ".curPage.value=1;" + formName + ".submit();\" " + _be_first + ">" + page_first + "</a>\n");
            sb.append("<a href=\"javascript:" + formName + ".curPage.value=" + (prev_page_num) + ";" + formName + ".submit();\" " + _can_prev + ">" + page_prev + "</a>\n");
            for (int i = startPage; i < offset; i++) {
                String active = curPage == i ? " class=\"active\"" : "";
                sb.append("<a href=\"javascript:" + formName + ".curPage.value=" + (i) + ";" + formName + ".submit();\" " + active + ">" + i + "</a>\n");
            }
            sb.append("<a href=\"javascript:" + formName + ".curPage.value=" + (offset) + ";" + formName + ".submit();\" " + _can_next + ">" + page_next + "</a>\n");
            sb.append("<a href=\"javascript:" + formName + ".curPage.value=" + (getTotalPage()) + ";" + formName + ".submit();\" " + _be_last + ">" + page_last + "</a>\n");
            sb.append("<a href=\"javascript:void(0);\">&nbsp;<input type='number' name='turnPage' size='0.1rem' value=''  min=\"1\" max='" + getTotalPage() + "' style='background-color: #ece4e4;line-height: 1.6em;'></a>\n");
            sb.append("<a href=\"javascript:" + formName + ".curPage.value=" + (formName) + ".turnPage.value==''?1:" + (formName) + ".turnPage.value;" + formName + ".submit();\">GO</a>\n");
            sb.append("</span>\n");
            sb.append("<br><em>共 " + getTotalPage() + " 页</em>&nbsp;\n");
            sb.append("&nbsp;<em>总计" + getTotalRow() + " 条记录</em>\n");
            sb.append("<br>&nbsp;");
            sb.append("</form>");
        }
        formPageStr = sb.toString();
        return formPageStr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public static void main(String[] args) {

        System.out.printf(152 % 3 + "");

    }
}
