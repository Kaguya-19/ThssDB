// Generated from SQL.g4 by ANTLR 4.8

package cn.edu.thssdb.sql;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, EQ=6, NE=7, LT=8, GT=9, LE=10, 
		GE=11, ADD=12, SUB=13, MUL=14, DIV=15, AND=16, OR=17, T_INT=18, T_LONG=19, 
		T_FLOAT=20, T_DOUBLE=21, T_STRING=22, K_ADD=23, K_ALL=24, K_AS=25, K_BY=26, 
		K_COLUMN=27, K_CREATE=28, K_DATABASE=29, K_DATABASES=30, K_DELETE=31, 
		K_DISTINCT=32, K_DROP=33, K_EXISTS=34, K_FROM=35, K_GRANT=36, K_IF=37, 
		K_IDENTIFIED=38, K_INSERT=39, K_INTO=40, K_JOIN=41, K_KEY=42, K_NOT=43, 
		K_NULL=44, K_ON=45, K_PRIMARY=46, K_QUIT=47, K_REVOKE=48, K_SELECT=49, 
		K_SET=50, K_SHOW=51, K_TABLE=52, K_TO=53, K_UPDATE=54, K_USE=55, K_USER=56, 
		K_VALUES=57, K_VIEW=58, K_WHERE=59, IDENTIFIER=60, NUMERIC_LITERAL=61, 
		EXPONENT=62, STRING_LITERAL=63, SINGLE_LINE_COMMENT=64, MULTILINE_COMMENT=65, 
		SPACES=66;
	public static final int
		RULE_parse = 0, RULE_sqlStmtList = 1, RULE_sqlStmt = 2, RULE_createDbStmt = 3, 
		RULE_dropDbStmt = 4, RULE_createUserStmt = 5, RULE_dropUserStmt = 6, RULE_createTableStmt = 7, 
		RULE_grantStmt = 8, RULE_revokeStmt = 9, RULE_useDbStmt = 10, RULE_deleteStmt = 11, 
		RULE_dropTableStmt = 12, RULE_showDbStmt = 13, RULE_quitStmt = 14, RULE_showTableStmt = 15, 
		RULE_insertStmt = 16, RULE_valueEntry = 17, RULE_selectStmt = 18, RULE_createViewStmt = 19, 
		RULE_dropViewStmt = 20, RULE_updateStmt = 21, RULE_columnDef = 22, RULE_typeName = 23, 
		RULE_columnConstraint = 24, RULE_multipleCondition = 25, RULE_condition = 26, 
		RULE_comparer = 27, RULE_comparator = 28, RULE_expression = 29, RULE_tableConstraint = 30, 
		RULE_resultColumn = 31, RULE_tableQuery = 32, RULE_authLevel = 33, RULE_literalValue = 34, 
		RULE_columnFullName = 35, RULE_databaseName = 36, RULE_tableName = 37, 
		RULE_userName = 38, RULE_columnName = 39, RULE_viewName = 40, RULE_password = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "sqlStmtList", "sqlStmt", "createDbStmt", "dropDbStmt", "createUserStmt", 
			"dropUserStmt", "createTableStmt", "grantStmt", "revokeStmt", "useDbStmt", 
			"deleteStmt", "dropTableStmt", "showDbStmt", "quitStmt", "showTableStmt", 
			"insertStmt", "valueEntry", "selectStmt", "createViewStmt", "dropViewStmt", 
			"updateStmt", "columnDef", "typeName", "columnConstraint", "multipleCondition", 
			"condition", "comparer", "comparator", "expression", "tableConstraint", 
			"resultColumn", "tableQuery", "authLevel", "literalValue", "columnFullName", 
			"databaseName", "tableName", "userName", "columnName", "viewName", "password"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'.'", "'='", "'<>'", "'<'", "'>'", 
			"'<='", "'>='", "'+'", "'-'", "'*'", "'/'", "'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "EQ", "NE", "LT", "GT", "LE", "GE", 
			"ADD", "SUB", "MUL", "DIV", "AND", "OR", "T_INT", "T_LONG", "T_FLOAT", 
			"T_DOUBLE", "T_STRING", "K_ADD", "K_ALL", "K_AS", "K_BY", "K_COLUMN", 
			"K_CREATE", "K_DATABASE", "K_DATABASES", "K_DELETE", "K_DISTINCT", "K_DROP", 
			"K_EXISTS", "K_FROM", "K_GRANT", "K_IF", "K_IDENTIFIED", "K_INSERT", 
			"K_INTO", "K_JOIN", "K_KEY", "K_NOT", "K_NULL", "K_ON", "K_PRIMARY", 
			"K_QUIT", "K_REVOKE", "K_SELECT", "K_SET", "K_SHOW", "K_TABLE", "K_TO", 
			"K_UPDATE", "K_USE", "K_USER", "K_VALUES", "K_VIEW", "K_WHERE", "IDENTIFIER", 
			"NUMERIC_LITERAL", "EXPONENT", "STRING_LITERAL", "SINGLE_LINE_COMMENT", 
			"MULTILINE_COMMENT", "SPACES"
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
	public String getGrammarFileName() { return "SQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public SqlStmtListContext sqlStmtList() {
			return getRuleContext(SqlStmtListContext.class,0);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			sqlStmtList();
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

	public static class SqlStmtListContext extends ParserRuleContext {
		public List<SqlStmtContext> sqlStmt() {
			return getRuleContexts(SqlStmtContext.class);
		}
		public SqlStmtContext sqlStmt(int i) {
			return getRuleContext(SqlStmtContext.class,i);
		}
		public SqlStmtListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStmtList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSqlStmtList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSqlStmtList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitSqlStmtList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStmtListContext sqlStmtList() throws RecognitionException {
		SqlStmtListContext _localctx = new SqlStmtListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sqlStmtList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(86);
				match(T__0);
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(92);
			sqlStmt();
			setState(101);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(94); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(93);
						match(T__0);
						}
						}
						setState(96); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(98);
					sqlStmt();
					}
					} 
				}
				setState(103);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(104);
				match(T__0);
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class SqlStmtContext extends ParserRuleContext {
		public CreateTableStmtContext createTableStmt() {
			return getRuleContext(CreateTableStmtContext.class,0);
		}
		public CreateDbStmtContext createDbStmt() {
			return getRuleContext(CreateDbStmtContext.class,0);
		}
		public CreateUserStmtContext createUserStmt() {
			return getRuleContext(CreateUserStmtContext.class,0);
		}
		public DropDbStmtContext dropDbStmt() {
			return getRuleContext(DropDbStmtContext.class,0);
		}
		public DropUserStmtContext dropUserStmt() {
			return getRuleContext(DropUserStmtContext.class,0);
		}
		public DeleteStmtContext deleteStmt() {
			return getRuleContext(DeleteStmtContext.class,0);
		}
		public DropTableStmtContext dropTableStmt() {
			return getRuleContext(DropTableStmtContext.class,0);
		}
		public InsertStmtContext insertStmt() {
			return getRuleContext(InsertStmtContext.class,0);
		}
		public SelectStmtContext selectStmt() {
			return getRuleContext(SelectStmtContext.class,0);
		}
		public CreateViewStmtContext createViewStmt() {
			return getRuleContext(CreateViewStmtContext.class,0);
		}
		public DropViewStmtContext dropViewStmt() {
			return getRuleContext(DropViewStmtContext.class,0);
		}
		public GrantStmtContext grantStmt() {
			return getRuleContext(GrantStmtContext.class,0);
		}
		public RevokeStmtContext revokeStmt() {
			return getRuleContext(RevokeStmtContext.class,0);
		}
		public UseDbStmtContext useDbStmt() {
			return getRuleContext(UseDbStmtContext.class,0);
		}
		public ShowDbStmtContext showDbStmt() {
			return getRuleContext(ShowDbStmtContext.class,0);
		}
		public ShowTableStmtContext showTableStmt() {
			return getRuleContext(ShowTableStmtContext.class,0);
		}
		public QuitStmtContext quitStmt() {
			return getRuleContext(QuitStmtContext.class,0);
		}
		public UpdateStmtContext updateStmt() {
			return getRuleContext(UpdateStmtContext.class,0);
		}
		public SqlStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSqlStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSqlStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitSqlStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStmtContext sqlStmt() throws RecognitionException {
		SqlStmtContext _localctx = new SqlStmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sqlStmt);
		try {
			setState(128);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				createTableStmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				createDbStmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				createUserStmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
				dropDbStmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(114);
				dropUserStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(115);
				deleteStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(116);
				dropTableStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(117);
				insertStmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(118);
				selectStmt();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(119);
				createViewStmt();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(120);
				dropViewStmt();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(121);
				grantStmt();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(122);
				revokeStmt();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(123);
				useDbStmt();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(124);
				showDbStmt();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(125);
				showTableStmt();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(126);
				quitStmt();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(127);
				updateStmt();
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

	public static class CreateDbStmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_DATABASE() { return getToken(SQLParser.K_DATABASE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public CreateDbStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createDbStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateDbStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateDbStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreateDbStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateDbStmtContext createDbStmt() throws RecognitionException {
		CreateDbStmtContext _localctx = new CreateDbStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_createDbStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(K_CREATE);
			setState(131);
			match(K_DATABASE);
			setState(132);
			databaseName();
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

	public static class DropDbStmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_DATABASE() { return getToken(SQLParser.K_DATABASE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public DropDbStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropDbStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDropDbStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDropDbStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDropDbStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropDbStmtContext dropDbStmt() throws RecognitionException {
		DropDbStmtContext _localctx = new DropDbStmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_dropDbStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(K_DROP);
			setState(135);
			match(K_DATABASE);
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(136);
				match(K_IF);
				setState(137);
				match(K_EXISTS);
				}
			}

			setState(140);
			databaseName();
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

	public static class CreateUserStmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_USER() { return getToken(SQLParser.K_USER, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public TerminalNode K_IDENTIFIED() { return getToken(SQLParser.K_IDENTIFIED, 0); }
		public TerminalNode K_BY() { return getToken(SQLParser.K_BY, 0); }
		public PasswordContext password() {
			return getRuleContext(PasswordContext.class,0);
		}
		public CreateUserStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createUserStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateUserStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateUserStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreateUserStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateUserStmtContext createUserStmt() throws RecognitionException {
		CreateUserStmtContext _localctx = new CreateUserStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_createUserStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(K_CREATE);
			setState(143);
			match(K_USER);
			setState(144);
			userName();
			setState(145);
			match(K_IDENTIFIED);
			setState(146);
			match(K_BY);
			setState(147);
			password();
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

	public static class DropUserStmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_USER() { return getToken(SQLParser.K_USER, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public DropUserStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropUserStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDropUserStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDropUserStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDropUserStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropUserStmtContext dropUserStmt() throws RecognitionException {
		DropUserStmtContext _localctx = new DropUserStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dropUserStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			match(K_DROP);
			setState(150);
			match(K_USER);
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(151);
				match(K_IF);
				setState(152);
				match(K_EXISTS);
				}
			}

			setState(155);
			userName();
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

	public static class CreateTableStmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLParser.K_TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<ColumnDefContext> columnDef() {
			return getRuleContexts(ColumnDefContext.class);
		}
		public ColumnDefContext columnDef(int i) {
			return getRuleContext(ColumnDefContext.class,i);
		}
		public TableConstraintContext tableConstraint() {
			return getRuleContext(TableConstraintContext.class,0);
		}
		public CreateTableStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTableStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateTableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateTableStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreateTableStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateTableStmtContext createTableStmt() throws RecognitionException {
		CreateTableStmtContext _localctx = new CreateTableStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_createTableStmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(K_CREATE);
			setState(158);
			match(K_TABLE);
			setState(159);
			tableName();
			setState(160);
			match(T__1);
			setState(161);
			columnDef();
			setState(166);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(162);
					match(T__2);
					setState(163);
					columnDef();
					}
					} 
				}
				setState(168);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(169);
				match(T__2);
				setState(170);
				tableConstraint();
				}
			}

			setState(173);
			match(T__3);
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

	public static class GrantStmtContext extends ParserRuleContext {
		public TerminalNode K_GRANT() { return getToken(SQLParser.K_GRANT, 0); }
		public List<AuthLevelContext> authLevel() {
			return getRuleContexts(AuthLevelContext.class);
		}
		public AuthLevelContext authLevel(int i) {
			return getRuleContext(AuthLevelContext.class,i);
		}
		public TerminalNode K_ON() { return getToken(SQLParser.K_ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_TO() { return getToken(SQLParser.K_TO, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public GrantStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grantStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterGrantStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitGrantStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitGrantStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GrantStmtContext grantStmt() throws RecognitionException {
		GrantStmtContext _localctx = new GrantStmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_grantStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(K_GRANT);
			setState(176);
			authLevel();
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(177);
				match(T__2);
				setState(178);
				authLevel();
				}
				}
				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(184);
			match(K_ON);
			setState(185);
			tableName();
			setState(186);
			match(K_TO);
			setState(187);
			userName();
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

	public static class RevokeStmtContext extends ParserRuleContext {
		public TerminalNode K_REVOKE() { return getToken(SQLParser.K_REVOKE, 0); }
		public List<AuthLevelContext> authLevel() {
			return getRuleContexts(AuthLevelContext.class);
		}
		public AuthLevelContext authLevel(int i) {
			return getRuleContext(AuthLevelContext.class,i);
		}
		public TerminalNode K_ON() { return getToken(SQLParser.K_ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_FROM() { return getToken(SQLParser.K_FROM, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public RevokeStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_revokeStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterRevokeStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitRevokeStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitRevokeStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RevokeStmtContext revokeStmt() throws RecognitionException {
		RevokeStmtContext _localctx = new RevokeStmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_revokeStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(K_REVOKE);
			setState(190);
			authLevel();
			setState(195);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(191);
				match(T__2);
				setState(192);
				authLevel();
				}
				}
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(198);
			match(K_ON);
			setState(199);
			tableName();
			setState(200);
			match(K_FROM);
			setState(201);
			userName();
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

	public static class UseDbStmtContext extends ParserRuleContext {
		public TerminalNode K_USE() { return getToken(SQLParser.K_USE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public UseDbStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useDbStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUseDbStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUseDbStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitUseDbStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseDbStmtContext useDbStmt() throws RecognitionException {
		UseDbStmtContext _localctx = new UseDbStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_useDbStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(K_USE);
			setState(204);
			databaseName();
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

	public static class DeleteStmtContext extends ParserRuleContext {
		public TerminalNode K_DELETE() { return getToken(SQLParser.K_DELETE, 0); }
		public TerminalNode K_FROM() { return getToken(SQLParser.K_FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(SQLParser.K_WHERE, 0); }
		public MultipleConditionContext multipleCondition() {
			return getRuleContext(MultipleConditionContext.class,0);
		}
		public DeleteStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDeleteStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDeleteStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDeleteStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteStmtContext deleteStmt() throws RecognitionException {
		DeleteStmtContext _localctx = new DeleteStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_deleteStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(K_DELETE);
			setState(207);
			match(K_FROM);
			setState(208);
			tableName();
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(209);
				match(K_WHERE);
				setState(210);
				multipleCondition(0);
				}
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

	public static class DropTableStmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLParser.K_TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public DropTableStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropTableStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDropTableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDropTableStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDropTableStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropTableStmtContext dropTableStmt() throws RecognitionException {
		DropTableStmtContext _localctx = new DropTableStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_dropTableStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			match(K_DROP);
			setState(214);
			match(K_TABLE);
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(215);
				match(K_IF);
				setState(216);
				match(K_EXISTS);
				}
			}

			setState(219);
			tableName();
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

	public static class ShowDbStmtContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(SQLParser.K_SHOW, 0); }
		public TerminalNode K_DATABASES() { return getToken(SQLParser.K_DATABASES, 0); }
		public ShowDbStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showDbStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterShowDbStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitShowDbStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitShowDbStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowDbStmtContext showDbStmt() throws RecognitionException {
		ShowDbStmtContext _localctx = new ShowDbStmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_showDbStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(K_SHOW);
			setState(222);
			match(K_DATABASES);
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

	public static class QuitStmtContext extends ParserRuleContext {
		public TerminalNode K_QUIT() { return getToken(SQLParser.K_QUIT, 0); }
		public QuitStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quitStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterQuitStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitQuitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitQuitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuitStmtContext quitStmt() throws RecognitionException {
		QuitStmtContext _localctx = new QuitStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_quitStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(K_QUIT);
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

	public static class ShowTableStmtContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(SQLParser.K_SHOW, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLParser.K_TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ShowTableStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showTableStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterShowTableStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitShowTableStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitShowTableStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowTableStmtContext showTableStmt() throws RecognitionException {
		ShowTableStmtContext _localctx = new ShowTableStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_showTableStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(K_SHOW);
			setState(227);
			match(K_TABLE);
			setState(228);
			tableName();
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

	public static class InsertStmtContext extends ParserRuleContext {
		public TerminalNode K_INSERT() { return getToken(SQLParser.K_INSERT, 0); }
		public TerminalNode K_INTO() { return getToken(SQLParser.K_INTO, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_VALUES() { return getToken(SQLParser.K_VALUES, 0); }
		public List<ValueEntryContext> valueEntry() {
			return getRuleContexts(ValueEntryContext.class);
		}
		public ValueEntryContext valueEntry(int i) {
			return getRuleContext(ValueEntryContext.class,i);
		}
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public InsertStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterInsertStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitInsertStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitInsertStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertStmtContext insertStmt() throws RecognitionException {
		InsertStmtContext _localctx = new InsertStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_insertStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(K_INSERT);
			setState(231);
			match(K_INTO);
			setState(232);
			tableName();
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(233);
				match(T__1);
				setState(234);
				columnName();
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(235);
					match(T__2);
					setState(236);
					columnName();
					}
					}
					setState(241);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(242);
				match(T__3);
				}
			}

			setState(246);
			match(K_VALUES);
			setState(247);
			valueEntry();
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(248);
				match(T__2);
				setState(249);
				valueEntry();
				}
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ValueEntryContext extends ParserRuleContext {
		public List<LiteralValueContext> literalValue() {
			return getRuleContexts(LiteralValueContext.class);
		}
		public LiteralValueContext literalValue(int i) {
			return getRuleContext(LiteralValueContext.class,i);
		}
		public ValueEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterValueEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitValueEntry(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitValueEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueEntryContext valueEntry() throws RecognitionException {
		ValueEntryContext _localctx = new ValueEntryContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_valueEntry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(T__1);
			setState(256);
			literalValue();
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(257);
				match(T__2);
				setState(258);
				literalValue();
				}
				}
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(264);
			match(T__3);
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

	public static class SelectStmtContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(SQLParser.K_SELECT, 0); }
		public List<ResultColumnContext> resultColumn() {
			return getRuleContexts(ResultColumnContext.class);
		}
		public ResultColumnContext resultColumn(int i) {
			return getRuleContext(ResultColumnContext.class,i);
		}
		public TerminalNode K_FROM() { return getToken(SQLParser.K_FROM, 0); }
		public List<TableQueryContext> tableQuery() {
			return getRuleContexts(TableQueryContext.class);
		}
		public TableQueryContext tableQuery(int i) {
			return getRuleContext(TableQueryContext.class,i);
		}
		public TerminalNode K_WHERE() { return getToken(SQLParser.K_WHERE, 0); }
		public MultipleConditionContext multipleCondition() {
			return getRuleContext(MultipleConditionContext.class,0);
		}
		public TerminalNode K_DISTINCT() { return getToken(SQLParser.K_DISTINCT, 0); }
		public TerminalNode K_ALL() { return getToken(SQLParser.K_ALL, 0); }
		public SelectStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSelectStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSelectStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitSelectStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStmtContext selectStmt() throws RecognitionException {
		SelectStmtContext _localctx = new SelectStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_selectStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(K_SELECT);
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_ALL || _la==K_DISTINCT) {
				{
				setState(267);
				_la = _input.LA(1);
				if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(270);
			resultColumn();
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(271);
				match(T__2);
				setState(272);
				resultColumn();
				}
				}
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(278);
			match(K_FROM);
			setState(279);
			tableQuery();
			setState(284);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(280);
				match(T__2);
				setState(281);
				tableQuery();
				}
				}
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(287);
				match(K_WHERE);
				setState(288);
				multipleCondition(0);
				}
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

	public static class CreateViewStmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_VIEW() { return getToken(SQLParser.K_VIEW, 0); }
		public ViewNameContext viewName() {
			return getRuleContext(ViewNameContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(SQLParser.K_AS, 0); }
		public SelectStmtContext selectStmt() {
			return getRuleContext(SelectStmtContext.class,0);
		}
		public CreateViewStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createViewStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreateViewStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreateViewStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreateViewStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateViewStmtContext createViewStmt() throws RecognitionException {
		CreateViewStmtContext _localctx = new CreateViewStmtContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_createViewStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(K_CREATE);
			setState(292);
			match(K_VIEW);
			setState(293);
			viewName();
			setState(294);
			match(K_AS);
			setState(295);
			selectStmt();
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

	public static class DropViewStmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_VIEW() { return getToken(SQLParser.K_VIEW, 0); }
		public ViewNameContext viewName() {
			return getRuleContext(ViewNameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public DropViewStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropViewStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDropViewStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDropViewStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDropViewStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropViewStmtContext dropViewStmt() throws RecognitionException {
		DropViewStmtContext _localctx = new DropViewStmtContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_dropViewStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(K_DROP);
			setState(298);
			match(K_VIEW);
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(299);
				match(K_IF);
				setState(300);
				match(K_EXISTS);
				}
			}

			setState(303);
			viewName();
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

	public static class UpdateStmtContext extends ParserRuleContext {
		public TerminalNode K_UPDATE() { return getToken(SQLParser.K_UPDATE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode K_SET() { return getToken(SQLParser.K_SET, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TerminalNode EQ() { return getToken(SQLParser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(SQLParser.K_WHERE, 0); }
		public MultipleConditionContext multipleCondition() {
			return getRuleContext(MultipleConditionContext.class,0);
		}
		public UpdateStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUpdateStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUpdateStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitUpdateStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateStmtContext updateStmt() throws RecognitionException {
		UpdateStmtContext _localctx = new UpdateStmtContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_updateStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(K_UPDATE);
			setState(306);
			tableName();
			setState(307);
			match(K_SET);
			setState(308);
			columnName();
			setState(309);
			match(EQ);
			setState(310);
			expression(0);
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(311);
				match(K_WHERE);
				setState(312);
				multipleCondition(0);
				}
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

	public static class ColumnDefContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<ColumnConstraintContext> columnConstraint() {
			return getRuleContexts(ColumnConstraintContext.class);
		}
		public ColumnConstraintContext columnConstraint(int i) {
			return getRuleContext(ColumnConstraintContext.class,i);
		}
		public ColumnDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumnDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnDefContext columnDef() throws RecognitionException {
		ColumnDefContext _localctx = new ColumnDefContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_columnDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			columnName();
			setState(316);
			typeName();
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_NOT || _la==K_PRIMARY) {
				{
				{
				setState(317);
				columnConstraint();
				}
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode T_INT() { return getToken(SQLParser.T_INT, 0); }
		public TerminalNode T_LONG() { return getToken(SQLParser.T_LONG, 0); }
		public TerminalNode T_FLOAT() { return getToken(SQLParser.T_FLOAT, 0); }
		public TerminalNode T_DOUBLE() { return getToken(SQLParser.T_DOUBLE, 0); }
		public TerminalNode T_STRING() { return getToken(SQLParser.T_STRING, 0); }
		public TerminalNode NUMERIC_LITERAL() { return getToken(SQLParser.NUMERIC_LITERAL, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_typeName);
		try {
			setState(331);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(323);
				match(T_INT);
				}
				break;
			case T_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(324);
				match(T_LONG);
				}
				break;
			case T_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(325);
				match(T_FLOAT);
				}
				break;
			case T_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(326);
				match(T_DOUBLE);
				}
				break;
			case T_STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(327);
				match(T_STRING);
				setState(328);
				match(T__1);
				setState(329);
				match(NUMERIC_LITERAL);
				setState(330);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ColumnConstraintContext extends ParserRuleContext {
		public TerminalNode K_PRIMARY() { return getToken(SQLParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(SQLParser.K_KEY, 0); }
		public TerminalNode K_NOT() { return getToken(SQLParser.K_NOT, 0); }
		public TerminalNode K_NULL() { return getToken(SQLParser.K_NULL, 0); }
		public ColumnConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumnConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnConstraintContext columnConstraint() throws RecognitionException {
		ColumnConstraintContext _localctx = new ColumnConstraintContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_columnConstraint);
		try {
			setState(337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				enterOuterAlt(_localctx, 1);
				{
				setState(333);
				match(K_PRIMARY);
				setState(334);
				match(K_KEY);
				}
				break;
			case K_NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(335);
				match(K_NOT);
				setState(336);
				match(K_NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class MultipleConditionContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<MultipleConditionContext> multipleCondition() {
			return getRuleContexts(MultipleConditionContext.class);
		}
		public MultipleConditionContext multipleCondition(int i) {
			return getRuleContext(MultipleConditionContext.class,i);
		}
		public TerminalNode AND() { return getToken(SQLParser.AND, 0); }
		public TerminalNode OR() { return getToken(SQLParser.OR, 0); }
		public MultipleConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterMultipleCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitMultipleCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitMultipleCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleConditionContext multipleCondition() throws RecognitionException {
		return multipleCondition(0);
	}

	private MultipleConditionContext multipleCondition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultipleConditionContext _localctx = new MultipleConditionContext(_ctx, _parentState);
		MultipleConditionContext _prevctx = _localctx;
		int _startState = 50;
		enterRecursionRule(_localctx, 50, RULE_multipleCondition, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(340);
			condition();
			}
			_ctx.stop = _input.LT(-1);
			setState(350);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(348);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new MultipleConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multipleCondition);
						setState(342);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(343);
						match(AND);
						setState(344);
						multipleCondition(3);
						}
						break;
					case 2:
						{
						_localctx = new MultipleConditionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_multipleCondition);
						setState(345);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(346);
						match(OR);
						setState(347);
						multipleCondition(2);
						}
						break;
					}
					} 
				}
				setState(352);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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

	public static class ConditionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			expression(0);
			setState(354);
			comparator();
			setState(355);
			expression(0);
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

	public static class ComparerContext extends ParserRuleContext {
		public ColumnFullNameContext columnFullName() {
			return getRuleContext(ColumnFullNameContext.class,0);
		}
		public LiteralValueContext literalValue() {
			return getRuleContext(LiteralValueContext.class,0);
		}
		public ComparerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterComparer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitComparer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitComparer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparerContext comparer() throws RecognitionException {
		ComparerContext _localctx = new ComparerContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_comparer);
		try {
			setState(359);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				columnFullName();
				}
				break;
			case K_NULL:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(358);
				literalValue();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ComparatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(SQLParser.EQ, 0); }
		public TerminalNode NE() { return getToken(SQLParser.NE, 0); }
		public TerminalNode LE() { return getToken(SQLParser.LE, 0); }
		public TerminalNode GE() { return getToken(SQLParser.GE, 0); }
		public TerminalNode LT() { return getToken(SQLParser.LT, 0); }
		public TerminalNode GT() { return getToken(SQLParser.GT, 0); }
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitComparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NE) | (1L << LT) | (1L << GT) | (1L << LE) | (1L << GE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ExpressionContext extends ParserRuleContext {
		public ComparerContext comparer() {
			return getRuleContext(ComparerContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MUL() { return getToken(SQLParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(SQLParser.DIV, 0); }
		public TerminalNode ADD() { return getToken(SQLParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(SQLParser.SUB, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
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
		int _startState = 58;
		enterRecursionRule(_localctx, 58, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_NULL:
			case IDENTIFIER:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				{
				setState(364);
				comparer();
				}
				break;
			case T__1:
				{
				setState(365);
				match(T__1);
				setState(366);
				expression(0);
				setState(367);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(379);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(377);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(371);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(372);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(373);
						expression(4);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(374);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(375);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(376);
						expression(3);
						}
						break;
					}
					} 
				}
				setState(381);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,31,_ctx);
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

	public static class TableConstraintContext extends ParserRuleContext {
		public TerminalNode K_PRIMARY() { return getToken(SQLParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(SQLParser.K_KEY, 0); }
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public TableConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTableConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTableConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTableConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableConstraintContext tableConstraint() throws RecognitionException {
		TableConstraintContext _localctx = new TableConstraintContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_tableConstraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(K_PRIMARY);
			setState(383);
			match(K_KEY);
			setState(384);
			match(T__1);
			setState(385);
			columnName();
			setState(390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(386);
				match(T__2);
				setState(387);
				columnName();
				}
				}
				setState(392);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(393);
			match(T__3);
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

	public static class ResultColumnContext extends ParserRuleContext {
		public TerminalNode MUL() { return getToken(SQLParser.MUL, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ColumnFullNameContext columnFullName() {
			return getRuleContext(ColumnFullNameContext.class,0);
		}
		public ResultColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resultColumn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterResultColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitResultColumn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitResultColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ResultColumnContext resultColumn() throws RecognitionException {
		ResultColumnContext _localctx = new ResultColumnContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_resultColumn);
		try {
			setState(401);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(395);
				match(MUL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(396);
				tableName();
				setState(397);
				match(T__4);
				setState(398);
				match(MUL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(400);
				columnFullName();
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

	public static class TableQueryContext extends ParserRuleContext {
		public List<TableNameContext> tableName() {
			return getRuleContexts(TableNameContext.class);
		}
		public TableNameContext tableName(int i) {
			return getRuleContext(TableNameContext.class,i);
		}
		public TerminalNode K_ON() { return getToken(SQLParser.K_ON, 0); }
		public MultipleConditionContext multipleCondition() {
			return getRuleContext(MultipleConditionContext.class,0);
		}
		public List<TerminalNode> K_JOIN() { return getTokens(SQLParser.K_JOIN); }
		public TerminalNode K_JOIN(int i) {
			return getToken(SQLParser.K_JOIN, i);
		}
		public TableQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTableQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTableQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTableQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableQueryContext tableQuery() throws RecognitionException {
		TableQueryContext _localctx = new TableQueryContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_tableQuery);
		int _la;
		try {
			setState(414);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(403);
				tableName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
				tableName();
				setState(407); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(405);
					match(K_JOIN);
					setState(406);
					tableName();
					}
					}
					setState(409); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==K_JOIN );
				setState(411);
				match(K_ON);
				setState(412);
				multipleCondition(0);
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

	public static class AuthLevelContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(SQLParser.K_SELECT, 0); }
		public TerminalNode K_INSERT() { return getToken(SQLParser.K_INSERT, 0); }
		public TerminalNode K_UPDATE() { return getToken(SQLParser.K_UPDATE, 0); }
		public TerminalNode K_DELETE() { return getToken(SQLParser.K_DELETE, 0); }
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public AuthLevelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_authLevel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterAuthLevel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitAuthLevel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitAuthLevel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AuthLevelContext authLevel() throws RecognitionException {
		AuthLevelContext _localctx = new AuthLevelContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_authLevel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(416);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_DELETE) | (1L << K_DROP) | (1L << K_INSERT) | (1L << K_SELECT) | (1L << K_UPDATE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class LiteralValueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(SQLParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(SQLParser.STRING_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(SQLParser.K_NULL, 0); }
		public LiteralValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterLiteralValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitLiteralValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitLiteralValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralValueContext literalValue() throws RecognitionException {
		LiteralValueContext _localctx = new LiteralValueContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_literalValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_NULL) | (1L << NUMERIC_LITERAL) | (1L << STRING_LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class ColumnFullNameContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public ColumnFullNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnFullName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnFullName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnFullName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumnFullName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnFullNameContext columnFullName() throws RecognitionException {
		ColumnFullNameContext _localctx = new ColumnFullNameContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_columnFullName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(420);
				tableName();
				setState(421);
				match(T__4);
				}
				break;
			}
			setState(425);
			columnName();
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

	public static class DatabaseNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public DatabaseNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_databaseName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDatabaseName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDatabaseName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDatabaseName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DatabaseNameContext databaseName() throws RecognitionException {
		DatabaseNameContext _localctx = new DatabaseNameContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_databaseName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			match(IDENTIFIER);
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

	public static class TableNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTableName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			match(IDENTIFIER);
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

	public static class UserNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public UserNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUserName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUserName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitUserName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserNameContext userName() throws RecognitionException {
		UserNameContext _localctx = new UserNameContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_userName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(431);
			match(IDENTIFIER);
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

	public static class ColumnNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public ColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			match(IDENTIFIER);
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

	public static class ViewNameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public ViewNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_viewName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterViewName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitViewName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitViewName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ViewNameContext viewName() throws RecognitionException {
		ViewNameContext _localctx = new ViewNameContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_viewName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(435);
			match(IDENTIFIER);
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

	public static class PasswordContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(SQLParser.STRING_LITERAL, 0); }
		public PasswordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_password; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterPassword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitPassword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitPassword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PasswordContext password() throws RecognitionException {
		PasswordContext _localctx = new PasswordContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_password);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			match(STRING_LITERAL);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 25:
			return multipleCondition_sempred((MultipleConditionContext)_localctx, predIndex);
		case 29:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean multipleCondition_sempred(MultipleConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 3);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3D\u01ba\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\3\7\3Z\n\3\f\3\16\3]\13\3\3\3\3\3\6\3a\n\3\r\3\16\3b\3\3\7\3"+
		"f\n\3\f\3\16\3i\13\3\3\3\7\3l\n\3\f\3\16\3o\13\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u0083\n\4\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6\u008d\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\b\3\b\3\b\3\b\5\b\u009c\n\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\7\t\u00a7\n\t\f\t\16\t\u00aa\13\t\3\t\3\t\5\t\u00ae\n\t\3\t\3\t\3\n"+
		"\3\n\3\n\3\n\7\n\u00b6\n\n\f\n\16\n\u00b9\13\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\7\13\u00c4\n\13\f\13\16\13\u00c7\13\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u00d6\n\r\3\16\3\16\3\16"+
		"\3\16\5\16\u00dc\n\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u00f0\n\22\f\22\16\22\u00f3"+
		"\13\22\3\22\3\22\5\22\u00f7\n\22\3\22\3\22\3\22\3\22\7\22\u00fd\n\22\f"+
		"\22\16\22\u0100\13\22\3\23\3\23\3\23\3\23\7\23\u0106\n\23\f\23\16\23\u0109"+
		"\13\23\3\23\3\23\3\24\3\24\5\24\u010f\n\24\3\24\3\24\3\24\7\24\u0114\n"+
		"\24\f\24\16\24\u0117\13\24\3\24\3\24\3\24\3\24\7\24\u011d\n\24\f\24\16"+
		"\24\u0120\13\24\3\24\3\24\5\24\u0124\n\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\26\3\26\3\26\3\26\5\26\u0130\n\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\5\27\u013c\n\27\3\30\3\30\3\30\7\30\u0141\n\30\f\30\16"+
		"\30\u0144\13\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u014e\n\31"+
		"\3\32\3\32\3\32\3\32\5\32\u0154\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\7\33\u015f\n\33\f\33\16\33\u0162\13\33\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\5\35\u016a\n\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\5\37"+
		"\u0174\n\37\3\37\3\37\3\37\3\37\3\37\3\37\7\37\u017c\n\37\f\37\16\37\u017f"+
		"\13\37\3 \3 \3 \3 \3 \3 \7 \u0187\n \f \16 \u018a\13 \3 \3 \3!\3!\3!\3"+
		"!\3!\3!\5!\u0194\n!\3\"\3\"\3\"\3\"\6\"\u019a\n\"\r\"\16\"\u019b\3\"\3"+
		"\"\3\"\5\"\u01a1\n\"\3#\3#\3$\3$\3%\3%\3%\5%\u01aa\n%\3%\3%\3&\3&\3\'"+
		"\3\'\3(\3(\3)\3)\3*\3*\3+\3+\3+\2\4\64<,\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRT\2\b\4\2\32\32\"\"\3\2\b"+
		"\r\3\2\20\21\3\2\16\17\7\2!!##))\63\6388\5\2..??AA\2\u01c8\2V\3\2\2\2"+
		"\4[\3\2\2\2\6\u0082\3\2\2\2\b\u0084\3\2\2\2\n\u0088\3\2\2\2\f\u0090\3"+
		"\2\2\2\16\u0097\3\2\2\2\20\u009f\3\2\2\2\22\u00b1\3\2\2\2\24\u00bf\3\2"+
		"\2\2\26\u00cd\3\2\2\2\30\u00d0\3\2\2\2\32\u00d7\3\2\2\2\34\u00df\3\2\2"+
		"\2\36\u00e2\3\2\2\2 \u00e4\3\2\2\2\"\u00e8\3\2\2\2$\u0101\3\2\2\2&\u010c"+
		"\3\2\2\2(\u0125\3\2\2\2*\u012b\3\2\2\2,\u0133\3\2\2\2.\u013d\3\2\2\2\60"+
		"\u014d\3\2\2\2\62\u0153\3\2\2\2\64\u0155\3\2\2\2\66\u0163\3\2\2\28\u0169"+
		"\3\2\2\2:\u016b\3\2\2\2<\u0173\3\2\2\2>\u0180\3\2\2\2@\u0193\3\2\2\2B"+
		"\u01a0\3\2\2\2D\u01a2\3\2\2\2F\u01a4\3\2\2\2H\u01a9\3\2\2\2J\u01ad\3\2"+
		"\2\2L\u01af\3\2\2\2N\u01b1\3\2\2\2P\u01b3\3\2\2\2R\u01b5\3\2\2\2T\u01b7"+
		"\3\2\2\2VW\5\4\3\2W\3\3\2\2\2XZ\7\3\2\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2"+
		"[\\\3\2\2\2\\^\3\2\2\2][\3\2\2\2^g\5\6\4\2_a\7\3\2\2`_\3\2\2\2ab\3\2\2"+
		"\2b`\3\2\2\2bc\3\2\2\2cd\3\2\2\2df\5\6\4\2e`\3\2\2\2fi\3\2\2\2ge\3\2\2"+
		"\2gh\3\2\2\2hm\3\2\2\2ig\3\2\2\2jl\7\3\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2"+
		"\2mn\3\2\2\2n\5\3\2\2\2om\3\2\2\2p\u0083\5\20\t\2q\u0083\5\b\5\2r\u0083"+
		"\5\f\7\2s\u0083\5\n\6\2t\u0083\5\16\b\2u\u0083\5\30\r\2v\u0083\5\32\16"+
		"\2w\u0083\5\"\22\2x\u0083\5&\24\2y\u0083\5(\25\2z\u0083\5*\26\2{\u0083"+
		"\5\22\n\2|\u0083\5\24\13\2}\u0083\5\26\f\2~\u0083\5\34\17\2\177\u0083"+
		"\5 \21\2\u0080\u0083\5\36\20\2\u0081\u0083\5,\27\2\u0082p\3\2\2\2\u0082"+
		"q\3\2\2\2\u0082r\3\2\2\2\u0082s\3\2\2\2\u0082t\3\2\2\2\u0082u\3\2\2\2"+
		"\u0082v\3\2\2\2\u0082w\3\2\2\2\u0082x\3\2\2\2\u0082y\3\2\2\2\u0082z\3"+
		"\2\2\2\u0082{\3\2\2\2\u0082|\3\2\2\2\u0082}\3\2\2\2\u0082~\3\2\2\2\u0082"+
		"\177\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0081\3\2\2\2\u0083\7\3\2\2\2\u0084"+
		"\u0085\7\36\2\2\u0085\u0086\7\37\2\2\u0086\u0087\5J&\2\u0087\t\3\2\2\2"+
		"\u0088\u0089\7#\2\2\u0089\u008c\7\37\2\2\u008a\u008b\7\'\2\2\u008b\u008d"+
		"\7$\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008f\5J&\2\u008f\13\3\2\2\2\u0090\u0091\7\36\2\2\u0091\u0092\7:\2\2"+
		"\u0092\u0093\5N(\2\u0093\u0094\7(\2\2\u0094\u0095\7\34\2\2\u0095\u0096"+
		"\5T+\2\u0096\r\3\2\2\2\u0097\u0098\7#\2\2\u0098\u009b\7:\2\2\u0099\u009a"+
		"\7\'\2\2\u009a\u009c\7$\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u009e\5N(\2\u009e\17\3\2\2\2\u009f\u00a0\7\36\2\2"+
		"\u00a0\u00a1\7\66\2\2\u00a1\u00a2\5L\'\2\u00a2\u00a3\7\4\2\2\u00a3\u00a8"+
		"\5.\30\2\u00a4\u00a5\7\5\2\2\u00a5\u00a7\5.\30\2\u00a6\u00a4\3\2\2\2\u00a7"+
		"\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ad\3\2"+
		"\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\7\5\2\2\u00ac\u00ae\5> \2\u00ad\u00ab"+
		"\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\7\6\2\2\u00b0"+
		"\21\3\2\2\2\u00b1\u00b2\7&\2\2\u00b2\u00b7\5D#\2\u00b3\u00b4\7\5\2\2\u00b4"+
		"\u00b6\5D#\2\u00b5\u00b3\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2"+
		"\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb"+
		"\7/\2\2\u00bb\u00bc\5L\'\2\u00bc\u00bd\7\67\2\2\u00bd\u00be\5N(\2\u00be"+
		"\23\3\2\2\2\u00bf\u00c0\7\62\2\2\u00c0\u00c5\5D#\2\u00c1\u00c2\7\5\2\2"+
		"\u00c2\u00c4\5D#\2\u00c3\u00c1\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3"+
		"\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8"+
		"\u00c9\7/\2\2\u00c9\u00ca\5L\'\2\u00ca\u00cb\7%\2\2\u00cb\u00cc\5N(\2"+
		"\u00cc\25\3\2\2\2\u00cd\u00ce\79\2\2\u00ce\u00cf\5J&\2\u00cf\27\3\2\2"+
		"\2\u00d0\u00d1\7!\2\2\u00d1\u00d2\7%\2\2\u00d2\u00d5\5L\'\2\u00d3\u00d4"+
		"\7=\2\2\u00d4\u00d6\5\64\33\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2"+
		"\u00d6\31\3\2\2\2\u00d7\u00d8\7#\2\2\u00d8\u00db\7\66\2\2\u00d9\u00da"+
		"\7\'\2\2\u00da\u00dc\7$\2\2\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc"+
		"\u00dd\3\2\2\2\u00dd\u00de\5L\'\2\u00de\33\3\2\2\2\u00df\u00e0\7\65\2"+
		"\2\u00e0\u00e1\7 \2\2\u00e1\35\3\2\2\2\u00e2\u00e3\7\61\2\2\u00e3\37\3"+
		"\2\2\2\u00e4\u00e5\7\65\2\2\u00e5\u00e6\7\66\2\2\u00e6\u00e7\5L\'\2\u00e7"+
		"!\3\2\2\2\u00e8\u00e9\7)\2\2\u00e9\u00ea\7*\2\2\u00ea\u00f6\5L\'\2\u00eb"+
		"\u00ec\7\4\2\2\u00ec\u00f1\5P)\2\u00ed\u00ee\7\5\2\2\u00ee\u00f0\5P)\2"+
		"\u00ef\u00ed\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2"+
		"\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f5\7\6\2\2\u00f5"+
		"\u00f7\3\2\2\2\u00f6\u00eb\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2"+
		"\2\2\u00f8\u00f9\7;\2\2\u00f9\u00fe\5$\23\2\u00fa\u00fb\7\5\2\2\u00fb"+
		"\u00fd\5$\23\2\u00fc\u00fa\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2"+
		"\2\2\u00fe\u00ff\3\2\2\2\u00ff#\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0102"+
		"\7\4\2\2\u0102\u0107\5F$\2\u0103\u0104\7\5\2\2\u0104\u0106\5F$\2\u0105"+
		"\u0103\3\2\2\2\u0106\u0109\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2"+
		"\2\2\u0108\u010a\3\2\2\2\u0109\u0107\3\2\2\2\u010a\u010b\7\6\2\2\u010b"+
		"%\3\2\2\2\u010c\u010e\7\63\2\2\u010d\u010f\t\2\2\2\u010e\u010d\3\2\2\2"+
		"\u010e\u010f\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0115\5@!\2\u0111\u0112"+
		"\7\5\2\2\u0112\u0114\5@!\2\u0113\u0111\3\2\2\2\u0114\u0117\3\2\2\2\u0115"+
		"\u0113\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117\u0115\3\2"+
		"\2\2\u0118\u0119\7%\2\2\u0119\u011e\5B\"\2\u011a\u011b\7\5\2\2\u011b\u011d"+
		"\5B\"\2\u011c\u011a\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e"+
		"\u011f\3\2\2\2\u011f\u0123\3\2\2\2\u0120\u011e\3\2\2\2\u0121\u0122\7="+
		"\2\2\u0122\u0124\5\64\33\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124"+
		"\'\3\2\2\2\u0125\u0126\7\36\2\2\u0126\u0127\7<\2\2\u0127\u0128\5R*\2\u0128"+
		"\u0129\7\33\2\2\u0129\u012a\5&\24\2\u012a)\3\2\2\2\u012b\u012c\7#\2\2"+
		"\u012c\u012f\7<\2\2\u012d\u012e\7\'\2\2\u012e\u0130\7$\2\2\u012f\u012d"+
		"\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u0132\5R*\2\u0132"+
		"+\3\2\2\2\u0133\u0134\78\2\2\u0134\u0135\5L\'\2\u0135\u0136\7\64\2\2\u0136"+
		"\u0137\5P)\2\u0137\u0138\7\b\2\2\u0138\u013b\5<\37\2\u0139\u013a\7=\2"+
		"\2\u013a\u013c\5\64\33\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"-\3\2\2\2\u013d\u013e\5P)\2\u013e\u0142\5\60\31\2\u013f\u0141\5\62\32"+
		"\2\u0140\u013f\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142\u0143"+
		"\3\2\2\2\u0143/\3\2\2\2\u0144\u0142\3\2\2\2\u0145\u014e\7\24\2\2\u0146"+
		"\u014e\7\25\2\2\u0147\u014e\7\26\2\2\u0148\u014e\7\27\2\2\u0149\u014a"+
		"\7\30\2\2\u014a\u014b\7\4\2\2\u014b\u014c\7?\2\2\u014c\u014e\7\6\2\2\u014d"+
		"\u0145\3\2\2\2\u014d\u0146\3\2\2\2\u014d\u0147\3\2\2\2\u014d\u0148\3\2"+
		"\2\2\u014d\u0149\3\2\2\2\u014e\61\3\2\2\2\u014f\u0150\7\60\2\2\u0150\u0154"+
		"\7,\2\2\u0151\u0152\7-\2\2\u0152\u0154\7.\2\2\u0153\u014f\3\2\2\2\u0153"+
		"\u0151\3\2\2\2\u0154\63\3\2\2\2\u0155\u0156\b\33\1\2\u0156\u0157\5\66"+
		"\34\2\u0157\u0160\3\2\2\2\u0158\u0159\f\4\2\2\u0159\u015a\7\22\2\2\u015a"+
		"\u015f\5\64\33\5\u015b\u015c\f\3\2\2\u015c\u015d\7\23\2\2\u015d\u015f"+
		"\5\64\33\4\u015e\u0158\3\2\2\2\u015e\u015b\3\2\2\2\u015f\u0162\3\2\2\2"+
		"\u0160\u015e\3\2\2\2\u0160\u0161\3\2\2\2\u0161\65\3\2\2\2\u0162\u0160"+
		"\3\2\2\2\u0163\u0164\5<\37\2\u0164\u0165\5:\36\2\u0165\u0166\5<\37\2\u0166"+
		"\67\3\2\2\2\u0167\u016a\5H%\2\u0168\u016a\5F$\2\u0169\u0167\3\2\2\2\u0169"+
		"\u0168\3\2\2\2\u016a9\3\2\2\2\u016b\u016c\t\3\2\2\u016c;\3\2\2\2\u016d"+
		"\u016e\b\37\1\2\u016e\u0174\58\35\2\u016f\u0170\7\4\2\2\u0170\u0171\5"+
		"<\37\2\u0171\u0172\7\6\2\2\u0172\u0174\3\2\2\2\u0173\u016d\3\2\2\2\u0173"+
		"\u016f\3\2\2\2\u0174\u017d\3\2\2\2\u0175\u0176\f\5\2\2\u0176\u0177\t\4"+
		"\2\2\u0177\u017c\5<\37\6\u0178\u0179\f\4\2\2\u0179\u017a\t\5\2\2\u017a"+
		"\u017c\5<\37\5\u017b\u0175\3\2\2\2\u017b\u0178\3\2\2\2\u017c\u017f\3\2"+
		"\2\2\u017d\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e=\3\2\2\2\u017f\u017d"+
		"\3\2\2\2\u0180\u0181\7\60\2\2\u0181\u0182\7,\2\2\u0182\u0183\7\4\2\2\u0183"+
		"\u0188\5P)\2\u0184\u0185\7\5\2\2\u0185\u0187\5P)\2\u0186\u0184\3\2\2\2"+
		"\u0187\u018a\3\2\2\2\u0188\u0186\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018b"+
		"\3\2\2\2\u018a\u0188\3\2\2\2\u018b\u018c\7\6\2\2\u018c?\3\2\2\2\u018d"+
		"\u0194\7\20\2\2\u018e\u018f\5L\'\2\u018f\u0190\7\7\2\2\u0190\u0191\7\20"+
		"\2\2\u0191\u0194\3\2\2\2\u0192\u0194\5H%\2\u0193\u018d\3\2\2\2\u0193\u018e"+
		"\3\2\2\2\u0193\u0192\3\2\2\2\u0194A\3\2\2\2\u0195\u01a1\5L\'\2\u0196\u0199"+
		"\5L\'\2\u0197\u0198\7+\2\2\u0198\u019a\5L\'\2\u0199\u0197\3\2\2\2\u019a"+
		"\u019b\3\2\2\2\u019b\u0199\3\2\2\2\u019b\u019c\3\2\2\2\u019c\u019d\3\2"+
		"\2\2\u019d\u019e\7/\2\2\u019e\u019f\5\64\33\2\u019f\u01a1\3\2\2\2\u01a0"+
		"\u0195\3\2\2\2\u01a0\u0196\3\2\2\2\u01a1C\3\2\2\2\u01a2\u01a3\t\6\2\2"+
		"\u01a3E\3\2\2\2\u01a4\u01a5\t\7\2\2\u01a5G\3\2\2\2\u01a6\u01a7\5L\'\2"+
		"\u01a7\u01a8\7\7\2\2\u01a8\u01aa\3\2\2\2\u01a9\u01a6\3\2\2\2\u01a9\u01aa"+
		"\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ac\5P)\2\u01acI\3\2\2\2\u01ad\u01ae"+
		"\7>\2\2\u01aeK\3\2\2\2\u01af\u01b0\7>\2\2\u01b0M\3\2\2\2\u01b1\u01b2\7"+
		">\2\2\u01b2O\3\2\2\2\u01b3\u01b4\7>\2\2\u01b4Q\3\2\2\2\u01b5\u01b6\7>"+
		"\2\2\u01b6S\3\2\2\2\u01b7\u01b8\7A\2\2\u01b8U\3\2\2\2\'[bgm\u0082\u008c"+
		"\u009b\u00a8\u00ad\u00b7\u00c5\u00d5\u00db\u00f1\u00f6\u00fe\u0107\u010e"+
		"\u0115\u011e\u0123\u012f\u013b\u0142\u014d\u0153\u015e\u0160\u0169\u0173"+
		"\u017b\u017d\u0188\u0193\u019b\u01a0\u01a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}