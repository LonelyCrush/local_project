package com.lzf.local.d2412.d241219.controller;

import com.lzf.local.d2412.d241219.api.FileApi;
import com.lzf.local.d2412.d241219.config.FileConfig;
import com.lzf.local.d2412.d241219.service.FileService;
import java.util.Arrays;
import javax.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author leizefeng
 */
@RestController
public class FileController implements FileApi {

  @Resource
  private FileConfig fileConfig;

  @Resource
  private FileService fileService;

  @Override
  public String upload(MultipartFile file,
      Long blockCount, Long blockIndex, Long blockSize, String fileId) {
    String contentType = file.getContentType();
    if (!StringUtils.hasText(contentType)) {
      throw new RuntimeException("文件类型不能为空");
    }
    boolean isTypeAllow =
        Arrays.stream(fileConfig.getAllowContentType().split(","))
            .parallel() // 并行流匹配
            .anyMatch(s -> contentType.toLowerCase().startsWith(s.toLowerCase()));
    if (!isTypeAllow) {
      throw new RuntimeException("不允许的文件类型");
    }

    // 断点续传
    if (blockCount != null && blockCount > 1) {
      return fileService.upload(file, blockCount, blockIndex, blockSize, fileId);
    }
    // 普通上传
    return fileService.upload(file);
  }
}
