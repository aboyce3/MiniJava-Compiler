// Generated from MiniJavaGrammar.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniJavaGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, Identifier=37, INTEGER_LITERAL=38, 
		DOUBLE_LITERAL=39, WhiteSpace=40, COMMENT=41;
	public static final int
		RULE_goal = 0, RULE_mainClass = 1, RULE_classDeclaration = 2, RULE_varDeclaration = 3, 
		RULE_methodDeclaration = 4, RULE_type = 5, RULE_statement = 6, RULE_expression = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"goal", "mainClass", "classDeclaration", "varDeclaration", "methodDeclaration", 
			"type", "statement", "expression"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'{'", "'public'", "'static'", "'void'", "'main'", "'('", 
			"'String'", "'['", "']'", "')'", "'}'", "'extends'", "';'", "','", "'return'", 
			"'int'", "'double'", "'boolean'", "'if'", "'else'", "'while'", "'System.out.println'", 
			"'='", "'&&'", "'<'", "'+'", "'-'", "'*'", "'.'", "'length'", "'true'", 
			"'false'", "'this'", "'new'", "'!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "Identifier", "INTEGER_LITERAL", "DOUBLE_LITERAL", "WhiteSpace", 
			"COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MiniJavaGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniJavaGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class GoalContext extends ParserRuleContext {
		public MainClassContext mainClass() {
			return getRuleContext(MainClassContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MiniJavaGrammarParser.EOF, 0); }
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public GoalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).enterGoal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).exitGoal(this);
		}
	}

	public final GoalContext goal() throws RecognitionException {
		GoalContext _localctx = new GoalContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_goal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			mainClass();
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(17);
				classDeclaration();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainClassContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(MiniJavaGrammarParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MiniJavaGrammarParser.Identifier, i);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public MainClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mainClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).enterMainClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).exitMainClass(this);
		}
	}

	public final MainClassContext mainClass() throws RecognitionException {
		MainClassContext _localctx = new MainClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mainClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(T__0);
			setState(26);
			match(Identifier);
			setState(27);
			match(T__1);
			setState(28);
			match(T__2);
			setState(29);
			match(T__3);
			setState(30);
			match(T__4);
			setState(31);
			match(T__5);
			setState(32);
			match(T__6);
			setState(33);
			match(T__7);
			setState(34);
			match(T__8);
			setState(35);
			match(T__9);
			setState(36);
			match(Identifier);
			setState(37);
			match(T__10);
			setState(38);
			match(T__1);
			setState(39);
			statement();
			setState(40);
			match(T__11);
			setState(41);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(MiniJavaGrammarParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MiniJavaGrammarParser.Identifier, i);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public TerminalNode EOF() { return getToken(MiniJavaGrammarParser.EOF, 0); }
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(T__0);
			setState(44);
			match(Identifier);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(45);
				match(T__12);
				setState(46);
				match(Identifier);
				}
			}

			setState(49);
			match(T__1);
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << Identifier))) != 0)) {
				{
				{
				setState(50);
				varDeclaration();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(56);
				methodDeclaration();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(T__11);
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(63);
				match(EOF);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MiniJavaGrammarParser.Identifier, 0); }
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).exitVarDeclaration(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			type();
			setState(67);
			match(Identifier);
			setState(68);
			match(T__13);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(MiniJavaGrammarParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(MiniJavaGrammarParser.Identifier, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).exitMethodDeclaration(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_methodDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(T__2);
			setState(71);
			type();
			setState(72);
			match(Identifier);
			setState(73);
			match(T__6);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << Identifier))) != 0)) {
				{
				setState(74);
				type();
				setState(75);
				match(Identifier);
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__14) {
					{
					{
					setState(76);
					match(T__14);
					setState(77);
					type();
					setState(78);
					match(Identifier);
					}
					}
					setState(84);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(87);
			match(T__10);
			setState(88);
			match(T__1);
			setState(92);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(89);
					varDeclaration();
					}
					} 
				}
				setState(94);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << Identifier))) != 0)) {
				{
				{
				setState(95);
				statement();
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			match(T__15);
			setState(102);
			expression(0);
			setState(103);
			match(T__13);
			setState(104);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MiniJavaGrammarParser.Identifier, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		try {
			setState(116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				match(T__16);
				setState(107);
				match(T__8);
				setState(108);
				match(T__9);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				match(T__17);
				setState(110);
				match(T__8);
				setState(111);
				match(T__9);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				match(T__18);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
				match(T__16);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(114);
				match(T__17);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(115);
				match(Identifier);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(MiniJavaGrammarParser.Identifier, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		int _la;
		try {
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				match(T__1);
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__19) | (1L << T__21) | (1L << T__22) | (1L << Identifier))) != 0)) {
					{
					{
					setState(119);
					statement();
					}
					}
					setState(124);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(125);
				match(T__11);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(126);
				match(T__19);
				setState(127);
				match(T__6);
				setState(128);
				expression(0);
				setState(129);
				match(T__10);
				setState(130);
				statement();
				setState(131);
				match(T__20);
				setState(132);
				statement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				match(T__21);
				setState(135);
				match(T__6);
				setState(136);
				expression(0);
				setState(137);
				match(T__10);
				setState(138);
				statement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				match(T__22);
				setState(141);
				match(T__6);
				setState(142);
				expression(0);
				setState(143);
				match(T__10);
				setState(144);
				match(T__13);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(146);
				match(Identifier);
				setState(147);
				match(T__23);
				setState(148);
				expression(0);
				setState(149);
				match(T__13);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(151);
				match(Identifier);
				setState(152);
				match(T__8);
				setState(153);
				expression(0);
				setState(154);
				match(T__9);
				setState(155);
				match(T__23);
				setState(156);
				expression(0);
				setState(157);
				match(T__13);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(MiniJavaGrammarParser.INTEGER_LITERAL, 0); }
		public TerminalNode DOUBLE_LITERAL() { return getToken(MiniJavaGrammarParser.DOUBLE_LITERAL, 0); }
		public TerminalNode Identifier() { return getToken(MiniJavaGrammarParser.Identifier, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniJavaGrammarListener ) ((MiniJavaGrammarListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(162);
				match(INTEGER_LITERAL);
				}
				break;
			case 2:
				{
				setState(163);
				match(DOUBLE_LITERAL);
				}
				break;
			case 3:
				{
				setState(164);
				match(T__31);
				}
				break;
			case 4:
				{
				setState(165);
				match(T__32);
				}
				break;
			case 5:
				{
				setState(166);
				match(Identifier);
				}
				break;
			case 6:
				{
				setState(167);
				match(T__33);
				}
				break;
			case 7:
				{
				setState(168);
				match(T__33);
				setState(169);
				expression(7);
				}
				break;
			case 8:
				{
				setState(170);
				match(T__34);
				setState(171);
				match(T__16);
				setState(172);
				match(T__8);
				setState(173);
				expression(0);
				setState(174);
				match(T__9);
				}
				break;
			case 9:
				{
				setState(176);
				match(T__34);
				setState(177);
				match(T__17);
				setState(178);
				match(T__8);
				setState(179);
				expression(0);
				setState(180);
				match(T__9);
				}
				break;
			case 10:
				{
				setState(182);
				match(T__34);
				setState(183);
				match(Identifier);
				setState(184);
				match(T__6);
				setState(185);
				match(T__10);
				}
				break;
			case 11:
				{
				setState(186);
				match(T__34);
				setState(187);
				match(Identifier);
				setState(188);
				match(T__6);
				setState(189);
				match(T__10);
				setState(190);
				expression(3);
				}
				break;
			case 12:
				{
				setState(191);
				match(T__35);
				setState(192);
				expression(2);
				}
				break;
			case 13:
				{
				setState(193);
				match(T__6);
				setState(194);
				expression(0);
				setState(195);
				match(T__10);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(227);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(225);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(200);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(201);
						expression(18);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(203);
						match(T__8);
						setState(204);
						expression(0);
						setState(205);
						match(T__9);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(207);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(208);
						match(T__29);
						setState(209);
						match(T__30);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(210);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(211);
						match(T__29);
						setState(212);
						match(Identifier);
						setState(213);
						match(T__6);
						setState(222);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << Identifier) | (1L << INTEGER_LITERAL) | (1L << DOUBLE_LITERAL))) != 0)) {
							{
							setState(214);
							expression(0);
							setState(219);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==T__14) {
								{
								{
								setState(215);
								match(T__14);
								setState(216);
								expression(0);
								}
								}
								setState(221);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(224);
						match(T__10);
						}
						break;
					}
					} 
				}
				setState(229);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 17);
		case 1:
			return precpred(_ctx, 16);
		case 2:
			return precpred(_ctx, 15);
		case 3:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u00e9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\7\2\25"+
		"\n\2\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4\62\n\4\3\4\3\4\7"+
		"\4\66\n\4\f\4\16\49\13\4\3\4\7\4<\n\4\f\4\16\4?\13\4\3\4\3\4\5\4C\n\4"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6S\n\6\f\6"+
		"\16\6V\13\6\5\6X\n\6\3\6\3\6\3\6\7\6]\n\6\f\6\16\6`\13\6\3\6\7\6c\n\6"+
		"\f\6\16\6f\13\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7w\n\7\3\b\3\b\7\b{\n\b\f\b\16\b~\13\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a2\n\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00c8"+
		"\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\7\t\u00dc\n\t\f\t\16\t\u00df\13\t\5\t\u00e1\n\t\3\t\7\t\u00e4\n"+
		"\t\f\t\16\t\u00e7\13\t\3\t\2\3\20\n\2\4\6\b\n\f\16\20\2\3\3\2\33\37\2"+
		"\u0106\2\22\3\2\2\2\4\33\3\2\2\2\6-\3\2\2\2\bD\3\2\2\2\nH\3\2\2\2\fv\3"+
		"\2\2\2\16\u00a1\3\2\2\2\20\u00c7\3\2\2\2\22\26\5\4\3\2\23\25\5\6\4\2\24"+
		"\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2\30"+
		"\26\3\2\2\2\31\32\7\2\2\3\32\3\3\2\2\2\33\34\7\3\2\2\34\35\7\'\2\2\35"+
		"\36\7\4\2\2\36\37\7\5\2\2\37 \7\6\2\2 !\7\7\2\2!\"\7\b\2\2\"#\7\t\2\2"+
		"#$\7\n\2\2$%\7\13\2\2%&\7\f\2\2&\'\7\'\2\2\'(\7\r\2\2()\7\4\2\2)*\5\16"+
		"\b\2*+\7\16\2\2+,\7\16\2\2,\5\3\2\2\2-.\7\3\2\2.\61\7\'\2\2/\60\7\17\2"+
		"\2\60\62\7\'\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\63\3\2\2\2\63\67\7\4\2\2"+
		"\64\66\5\b\5\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28=\3"+
		"\2\2\29\67\3\2\2\2:<\5\n\6\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>"+
		"@\3\2\2\2?=\3\2\2\2@B\7\16\2\2AC\7\2\2\3BA\3\2\2\2BC\3\2\2\2C\7\3\2\2"+
		"\2DE\5\f\7\2EF\7\'\2\2FG\7\20\2\2G\t\3\2\2\2HI\7\5\2\2IJ\5\f\7\2JK\7\'"+
		"\2\2KW\7\t\2\2LM\5\f\7\2MT\7\'\2\2NO\7\21\2\2OP\5\f\7\2PQ\7\'\2\2QS\3"+
		"\2\2\2RN\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UX\3\2\2\2VT\3\2\2\2WL\3"+
		"\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7\r\2\2Z^\7\4\2\2[]\5\b\5\2\\[\3\2\2\2]`"+
		"\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_d\3\2\2\2`^\3\2\2\2ac\5\16\b\2ba\3\2\2\2"+
		"cf\3\2\2\2db\3\2\2\2de\3\2\2\2eg\3\2\2\2fd\3\2\2\2gh\7\22\2\2hi\5\20\t"+
		"\2ij\7\20\2\2jk\7\16\2\2k\13\3\2\2\2lm\7\23\2\2mn\7\13\2\2nw\7\f\2\2o"+
		"p\7\24\2\2pq\7\13\2\2qw\7\f\2\2rw\7\25\2\2sw\7\23\2\2tw\7\24\2\2uw\7\'"+
		"\2\2vl\3\2\2\2vo\3\2\2\2vr\3\2\2\2vs\3\2\2\2vt\3\2\2\2vu\3\2\2\2w\r\3"+
		"\2\2\2x|\7\4\2\2y{\5\16\b\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177"+
		"\3\2\2\2~|\3\2\2\2\177\u00a2\7\16\2\2\u0080\u0081\7\26\2\2\u0081\u0082"+
		"\7\t\2\2\u0082\u0083\5\20\t\2\u0083\u0084\7\r\2\2\u0084\u0085\5\16\b\2"+
		"\u0085\u0086\7\27\2\2\u0086\u0087\5\16\b\2\u0087\u00a2\3\2\2\2\u0088\u0089"+
		"\7\30\2\2\u0089\u008a\7\t\2\2\u008a\u008b\5\20\t\2\u008b\u008c\7\r\2\2"+
		"\u008c\u008d\5\16\b\2\u008d\u00a2\3\2\2\2\u008e\u008f\7\31\2\2\u008f\u0090"+
		"\7\t\2\2\u0090\u0091\5\20\t\2\u0091\u0092\7\r\2\2\u0092\u0093\7\20\2\2"+
		"\u0093\u00a2\3\2\2\2\u0094\u0095\7\'\2\2\u0095\u0096\7\32\2\2\u0096\u0097"+
		"\5\20\t\2\u0097\u0098\7\20\2\2\u0098\u00a2\3\2\2\2\u0099\u009a\7\'\2\2"+
		"\u009a\u009b\7\13\2\2\u009b\u009c\5\20\t\2\u009c\u009d\7\f\2\2\u009d\u009e"+
		"\7\32\2\2\u009e\u009f\5\20\t\2\u009f\u00a0\7\20\2\2\u00a0\u00a2\3\2\2"+
		"\2\u00a1x\3\2\2\2\u00a1\u0080\3\2\2\2\u00a1\u0088\3\2\2\2\u00a1\u008e"+
		"\3\2\2\2\u00a1\u0094\3\2\2\2\u00a1\u0099\3\2\2\2\u00a2\17\3\2\2\2\u00a3"+
		"\u00a4\b\t\1\2\u00a4\u00c8\7(\2\2\u00a5\u00c8\7)\2\2\u00a6\u00c8\7\"\2"+
		"\2\u00a7\u00c8\7#\2\2\u00a8\u00c8\7\'\2\2\u00a9\u00c8\7$\2\2\u00aa\u00ab"+
		"\7$\2\2\u00ab\u00c8\5\20\t\t\u00ac\u00ad\7%\2\2\u00ad\u00ae\7\23\2\2\u00ae"+
		"\u00af\7\13\2\2\u00af\u00b0\5\20\t\2\u00b0\u00b1\7\f\2\2\u00b1\u00c8\3"+
		"\2\2\2\u00b2\u00b3\7%\2\2\u00b3\u00b4\7\24\2\2\u00b4\u00b5\7\13\2\2\u00b5"+
		"\u00b6\5\20\t\2\u00b6\u00b7\7\f\2\2\u00b7\u00c8\3\2\2\2\u00b8\u00b9\7"+
		"%\2\2\u00b9\u00ba\7\'\2\2\u00ba\u00bb\7\t\2\2\u00bb\u00c8\7\r\2\2\u00bc"+
		"\u00bd\7%\2\2\u00bd\u00be\7\'\2\2\u00be\u00bf\7\t\2\2\u00bf\u00c0\7\r"+
		"\2\2\u00c0\u00c8\5\20\t\5\u00c1\u00c2\7&\2\2\u00c2\u00c8\5\20\t\4\u00c3"+
		"\u00c4\7\t\2\2\u00c4\u00c5\5\20\t\2\u00c5\u00c6\7\r\2\2\u00c6\u00c8\3"+
		"\2\2\2\u00c7\u00a3\3\2\2\2\u00c7\u00a5\3\2\2\2\u00c7\u00a6\3\2\2\2\u00c7"+
		"\u00a7\3\2\2\2\u00c7\u00a8\3\2\2\2\u00c7\u00a9\3\2\2\2\u00c7\u00aa\3\2"+
		"\2\2\u00c7\u00ac\3\2\2\2\u00c7\u00b2\3\2\2\2\u00c7\u00b8\3\2\2\2\u00c7"+
		"\u00bc\3\2\2\2\u00c7\u00c1\3\2\2\2\u00c7\u00c3\3\2\2\2\u00c8\u00e5\3\2"+
		"\2\2\u00c9\u00ca\f\23\2\2\u00ca\u00cb\t\2\2\2\u00cb\u00e4\5\20\t\24\u00cc"+
		"\u00cd\f\22\2\2\u00cd\u00ce\7\13\2\2\u00ce\u00cf\5\20\t\2\u00cf\u00d0"+
		"\7\f\2\2\u00d0\u00e4\3\2\2\2\u00d1\u00d2\f\21\2\2\u00d2\u00d3\7 \2\2\u00d3"+
		"\u00e4\7!\2\2\u00d4\u00d5\f\20\2\2\u00d5\u00d6\7 \2\2\u00d6\u00d7\7\'"+
		"\2\2\u00d7\u00e0\7\t\2\2\u00d8\u00dd\5\20\t\2\u00d9\u00da\7\21\2\2\u00da"+
		"\u00dc\5\20\t\2\u00db\u00d9\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db\3"+
		"\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0"+
		"\u00d8\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4\7\r"+
		"\2\2\u00e3\u00c9\3\2\2\2\u00e3\u00cc\3\2\2\2\u00e3\u00d1\3\2\2\2\u00e3"+
		"\u00d4\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2"+
		"\2\2\u00e6\21\3\2\2\2\u00e7\u00e5\3\2\2\2\23\26\61\67=BTW^dv|\u00a1\u00c7"+
		"\u00dd\u00e0\u00e3\u00e5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}