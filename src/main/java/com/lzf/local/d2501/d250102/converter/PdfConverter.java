package com.lzf.local.d2501.d250102.converter;


import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author leizefeng
 */
public interface PdfConverter {

  void convertToPdf(InputStream inputStream, OutputStream outputStream);
}
