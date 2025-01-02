package com.lzf.local.d2501.d250102.converter;

import java.io.File;
import java.io.IOException;

/**
 * @author leizefeng
 */

/**
 * 首先，确保你已经在系统上安装了 LibreOffice。你可以从 LibreOffice官网 下载并安装。
 */
public class LibreOfficePdfConverter {

  public static void convertToPdf(String inputFilePath, String outputFilePath) {
    File inputFile = new File(inputFilePath);
    File outputFile = new File(outputFilePath);

    if (!inputFile.exists()) {
      throw new IllegalArgumentException(
          "Input file does not exist: " + inputFilePath);
    }

    // 确保输出目录存在
    File outputDir = outputFile.getParentFile();
    if (!outputDir.exists() && !outputDir.mkdirs()) {
      throw new IllegalArgumentException(
          "Output directory cannot be created: " + outputDir.getAbsolutePath());
    }

    // 构建LibreOffice命令
    String[] cmd = {
        "soffice",
        "--headless",
        "--convert-to",
        "pdf",
        "--outdir",
        outputDir.getAbsolutePath(),
        inputFile.getAbsolutePath()
    };

    // 执行LibreOffice命令
    try {
      Process process = new ProcessBuilder(cmd).start();
      process.waitFor();
      if (process.exitValue() == 0) {
        System.out.println("Conversion successful: " + outputFilePath);
      } else {
        System.err.println("Conversion failed, exit code: " + process.exitValue());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    // 示例文件路径
    String wordFilePath = "path/to/your/input.docx";
    String excelFilePath = "path/to/your/input.xlsx";
    String imgFilePath = "path/to/your/input.png";
    String pptFilePath = "path/to/your/input.pptx";

    // 转换Word文件
    convertToPdf(wordFilePath, "path/to/your/output_word.pdf");

    // 转换Excel文件
    convertToPdf(excelFilePath, "path/to/your/output_excel.pdf");

    // 转换图像文件
    convertToPdf(imgFilePath, "path/to/your/output_img.pdf");

    // 转换PPT文件
    convertToPdf(pptFilePath, "path/to/your/output_ppt.pdf");
  }
}
