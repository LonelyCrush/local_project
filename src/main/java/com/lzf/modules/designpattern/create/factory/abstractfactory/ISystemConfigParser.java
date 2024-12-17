package com.lzf.modules.designpattern.create.factory.abstractfactory;

import com.lzf.modules.designpattern.create.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public interface ISystemConfigParser {

  RuleConfig parse(String configText);
}
