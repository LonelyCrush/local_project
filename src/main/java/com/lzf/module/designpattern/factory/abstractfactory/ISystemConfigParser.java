package com.lzf.module.designpattern.factory.abstractfactory;

import com.lzf.module.designpattern.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public interface ISystemConfigParser {

  RuleConfig parse(String configText);
}
