package com.lzf.local.d2412.d241224;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lzf.local.d2412.d241224.mapper.SysTermsMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RequestMapping("/d241224")
@RestController
public class PageDemoController {

  @Resource
  private SysTermsMapper sysTermsMapper;

  @GetMapping("/page")
  Pagination<SysTerm> page(
      @RequestParam(value = "number", required = false) Integer number,
      @RequestParam(value = "size", required = false) Integer size) {
    LambdaUpdateWrapper<SysTerm> wrapper = new LambdaUpdateWrapper<>();
    return PageUtil.page(() -> sysTermsMapper.selectList(wrapper));
  }

  @GetMapping("/test")
  List<SysTerm> test() {
    LambdaUpdateWrapper<SysTerm> wrapper = new LambdaUpdateWrapper<>();
    return sysTermsMapper.selectList(wrapper);
  }
}
