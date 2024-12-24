package com.lzf.local.d2412.d241224;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author leizefeng
 */
public class PageUtil {

  private static final String numberParameter = "number";

  private static final String sizeParameter = "size";

  private static final int defaultNumber = 1;

  private static final int defaultSize = 10;

  private PageUtil() {
  }

  public static long count(ISelect iSelect) {
    return PageMethod.count(iSelect);
  }

  public static <T> Pagination<T> empty() {
    Pagination<T> pagination = pageInfo();
    return pagination.setSum(0L).setRecord(Collections.emptyList());
  }

  public static <T> Pagination<T> page(ISelect iSelect) {
    Pagination<T> pagination = pageInfo();
    PageInfo<T> objectPageInfo =
        PageMethod.startPage(pagination.getNumber(), pagination.getSize())
            .doSelectPageInfo(iSelect);
    return pagination.setSum(objectPageInfo.getTotal()).setRecord(objectPageInfo.getList());
  }

  private static <T> Pagination<T> pageInfo() {
    // get the HttpServletRequest
    ServletRequestAttributes servletRequestAttributes =
        (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    HttpServletRequest request = servletRequestAttributes.getRequest();

    // get the page number and size
    int pageNumber = defaultNumber;
    int pageSize = defaultSize;
    String number = request.getParameter(numberParameter);
    if (StringUtils.hasText(number)) {
      pageNumber = Integer.parseInt(number);
    }
    String size = request.getParameter(sizeParameter);
    if (StringUtils.hasText(size)) {
      pageSize = Integer.parseInt(size);
    }

    // return
    Pagination<T> tPagination = new Pagination<>();
    return tPagination.setNumber(pageNumber).setSize(pageSize);
  }
}
