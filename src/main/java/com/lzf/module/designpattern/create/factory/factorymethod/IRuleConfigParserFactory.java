package com.lzf.module.designpattern.create.factory.factorymethod;

import com.lzf.module.designpattern.create.factory.simplefactory.IRuleConfigParser;

/**
 * @author leizefeng
 */
public interface IRuleConfigParserFactory {

  IRuleConfigParser createParser();
}
