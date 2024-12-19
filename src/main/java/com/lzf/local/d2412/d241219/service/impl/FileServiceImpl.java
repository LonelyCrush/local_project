package com.lzf.local.d2412.d241219.service.impl;

import com.lzf.local.d2412.d241219.config.FileConfig;
import com.lzf.local.d2412.d241219.enums.FileStatusEnum;
import com.lzf.local.d2412.d241219.mapper.entity.FileInfo;
import com.lzf.local.d2412.d241219.service.FileService;
import com.lzf.local.d2412.d241219.utils.DateTimeUtils;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author leizefeng
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

  @Resource
  private FileConfig fileConfig;

  @Override
  public String upload(MultipartFile file) {
    FileInfo fileInfo = buildFileInfo(file);
    log.info("保存信息至数据库：{}", fileInfo);
    return fileInfo.getThirdFileLocation();
  }

  private FileInfo buildFileInfo(MultipartFile file) {
    // 获取文件名（带后缀）
    String originalFilename = file.getOriginalFilename();
    if (!StringUtils.hasText(originalFilename)) {
      throw new IllegalArgumentException("文件名不能为空");
    }

    // 获取文件后缀
    String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));

    // 创建保存路径
    String fileId = UUID.randomUUID().toString().replace("-", "");
    String BASE_PATH = "F:\\lzf\\test\\local_project\\src\\main\\resources";
    File folderPath = new File(BASE_PATH + fileConfig.getLocalFolderPath(),
        DateTimeUtils.DTF_yyyyMMdd.format(LocalDate.now()));
    if (!folderPath.exists() && !folderPath.mkdirs()) {
      throw new RuntimeException("创建文件夹失败：" + folderPath.getAbsolutePath());
    }

    // 创建保存名称
    File target = new File(folderPath, fileId + fileSuffix);
    try {
      file.transferTo(target);
    } catch (IOException e) {
      throw new RuntimeException("上传文件失败：" + e.getMessage());
    }
    return new FileInfo()
        .setFileId(fileId)
        .setFileSize(file.getSize())
        .setFileName(originalFilename)
        .setFileStatus(FileStatusEnum.AVAILABLE)
        .setThirdFileLocation(target.getAbsolutePath())
        .setCreateTime(LocalDateTime.now());
  }

  @Override
  public String upload(MultipartFile file,
      Long blockCount, Long blockIndex, Long blockSize, String fileId) {
    return "";
  }
}
