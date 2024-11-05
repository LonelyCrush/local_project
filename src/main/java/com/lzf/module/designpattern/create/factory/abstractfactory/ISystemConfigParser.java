package com.lzf.module.designpattern.create.factory.abstractfactory;

import com.lzf.module.designpattern.create.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public interface ISystemConfigParser {

  RuleConfig parse(String configText);
}
