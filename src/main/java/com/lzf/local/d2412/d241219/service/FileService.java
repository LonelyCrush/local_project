package com.lzf.local.d2412.d241219.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author leizefeng
 */
public interface FileService {

  String upload(MultipartFile file);

  String upload(MultipartFile file,
      Long blockCount, Long blockIndex, Long blockSize, String fileId);
}
