package com.lzf.module.designpattern.create.factory.simplefactory;

/**
 * @author leizefeng
 */
public interface IRuleConfigParser {

  RuleConfig parse(String configText);
}
