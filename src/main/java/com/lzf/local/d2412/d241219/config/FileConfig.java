package com.lzf.local.d2412.d241219.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@ConfigurationProperties(prefix = "file-upload")
@Component
@Data
public class FileConfig {

  private String allowContentType;

  private String localFolderPath;
}
