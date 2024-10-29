package com.lzf.module.designpattern.factory.simplefactory;

/**
 * @author leizefeng
 */
public interface IRuleConfigParser {

  RuleConfig parse(String configText);
}
