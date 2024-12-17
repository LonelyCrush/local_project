package com.lzf.modules.designpattern.create.factory.factorymethod;

import com.lzf.modules.designpattern.create.factory.simplefactory.IRuleConfigParser;

/**
 * @author leizefeng
 */
public interface IRuleConfigParserFactory {

  IRuleConfigParser createParser();
}
