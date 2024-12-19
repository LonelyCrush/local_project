package com.lzf.local.d2412.d241219.mapper.entity;

import com.lzf.local.d2412.d241219.enums.FileStatusEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author leizefeng
 */
@Data
@Accessors(chain = true)
public class FileInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;

  /** 每个文件对应的唯一File ID */
  private String fileId;

  /** 文件名，带后缀 */
  private String fileName;

  /** 文件大小。单位Byte */
  private Long fileSize;

  /** AVAILABLE：可用。DELETE：标记删除（第三方数据删除后，删除本条数据） */
  private FileStatusEnum fileStatus;

  /** 第三方文件保存地址 */
  private String thirdFileLocation;

  private LocalDateTime createTime;

  private String uploadIp;

  /** 分段上传使用,保存当前分段上传的标号 */
  private Long fileBlock;
}
