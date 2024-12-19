package com.lzf.local.d2412.d241219.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author leizefeng
 */
@RequestMapping("/d241219/file")
public interface FileApi {

  @PostMapping
  String upload(
      @RequestParam(value = "file") MultipartFile file,
      @RequestParam(value = "blockCount", required = false) Long blockCount,
      @RequestParam(value = "blockIndex", required = false) Long blockIndex,
      @RequestParam(value = "blockSize", required = false) Long blockSize,
      @RequestParam(value = "fileId", required = false) String fileId);
}
