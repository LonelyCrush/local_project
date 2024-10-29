package com.lzf.module.designpattern.factory.factorymethod;

import com.lzf.module.designpattern.factory.simplefactory.IRuleConfigParser;

/**
 * @author leizefeng
 */
public interface IRuleConfigParserFactory {

  IRuleConfigParser createParser();
}
