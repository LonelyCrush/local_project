package com.lzf.local.d2412.d241224;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author leizefeng
 */
@JsonInclude(Include.NON_NULL)
@Data
@Accessors(chain = true)
public class Pagination<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  private long sum;

  private int number;

  private int size;

  private List<T> record;
}
