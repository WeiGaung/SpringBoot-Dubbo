package com.wenba.service.expression.syntax;

import com.wenba.service.expression.tokens.VariableToken;

/**
 * 变量未初始化异常
 * @author shanxuecheng
 *
 */
@SuppressWarnings("serial")
public class VariableNotInitializedException extends SyntaxException {
	
	public VariableNotInitializedException(String message) {
		super(message);
	}
	
	public VariableNotInitializedException(VariableToken variable) {
		super("Variable \"" + variable.getText() + "\" may not have been initialized." , variable);
	}
}
