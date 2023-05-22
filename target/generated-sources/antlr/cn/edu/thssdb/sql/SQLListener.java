// Generated from SQL.g4 by ANTLR 4.8

package cn.edu.thssdb.sql;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SQLParser}.
 */
public interface SQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SQLParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(SQLParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(SQLParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#sqlStmtList}.
	 * @param ctx the parse tree
	 */
	void enterSqlStmtList(SQLParser.SqlStmtListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#sqlStmtList}.
	 * @param ctx the parse tree
	 */
	void exitSqlStmtList(SQLParser.SqlStmtListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#sqlStmt}.
	 * @param ctx the parse tree
	 */
	void enterSqlStmt(SQLParser.SqlStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#sqlStmt}.
	 * @param ctx the parse tree
	 */
	void exitSqlStmt(SQLParser.SqlStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#createDbStmt}.
	 * @param ctx the parse tree
	 */
	void enterCreateDbStmt(SQLParser.CreateDbStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#createDbStmt}.
	 * @param ctx the parse tree
	 */
	void exitCreateDbStmt(SQLParser.CreateDbStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#dropDbStmt}.
	 * @param ctx the parse tree
	 */
	void enterDropDbStmt(SQLParser.DropDbStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#dropDbStmt}.
	 * @param ctx the parse tree
	 */
	void exitDropDbStmt(SQLParser.DropDbStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#createUserStmt}.
	 * @param ctx the parse tree
	 */
	void enterCreateUserStmt(SQLParser.CreateUserStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#createUserStmt}.
	 * @param ctx the parse tree
	 */
	void exitCreateUserStmt(SQLParser.CreateUserStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#dropUserStmt}.
	 * @param ctx the parse tree
	 */
	void enterDropUserStmt(SQLParser.DropUserStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#dropUserStmt}.
	 * @param ctx the parse tree
	 */
	void exitDropUserStmt(SQLParser.DropUserStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#createTableStmt}.
	 * @param ctx the parse tree
	 */
	void enterCreateTableStmt(SQLParser.CreateTableStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#createTableStmt}.
	 * @param ctx the parse tree
	 */
	void exitCreateTableStmt(SQLParser.CreateTableStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#grantStmt}.
	 * @param ctx the parse tree
	 */
	void enterGrantStmt(SQLParser.GrantStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#grantStmt}.
	 * @param ctx the parse tree
	 */
	void exitGrantStmt(SQLParser.GrantStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#revokeStmt}.
	 * @param ctx the parse tree
	 */
	void enterRevokeStmt(SQLParser.RevokeStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#revokeStmt}.
	 * @param ctx the parse tree
	 */
	void exitRevokeStmt(SQLParser.RevokeStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#useDbStmt}.
	 * @param ctx the parse tree
	 */
	void enterUseDbStmt(SQLParser.UseDbStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#useDbStmt}.
	 * @param ctx the parse tree
	 */
	void exitUseDbStmt(SQLParser.UseDbStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#deleteStmt}.
	 * @param ctx the parse tree
	 */
	void enterDeleteStmt(SQLParser.DeleteStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#deleteStmt}.
	 * @param ctx the parse tree
	 */
	void exitDeleteStmt(SQLParser.DeleteStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#dropTableStmt}.
	 * @param ctx the parse tree
	 */
	void enterDropTableStmt(SQLParser.DropTableStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#dropTableStmt}.
	 * @param ctx the parse tree
	 */
	void exitDropTableStmt(SQLParser.DropTableStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#showDbStmt}.
	 * @param ctx the parse tree
	 */
	void enterShowDbStmt(SQLParser.ShowDbStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#showDbStmt}.
	 * @param ctx the parse tree
	 */
	void exitShowDbStmt(SQLParser.ShowDbStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#quitStmt}.
	 * @param ctx the parse tree
	 */
	void enterQuitStmt(SQLParser.QuitStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#quitStmt}.
	 * @param ctx the parse tree
	 */
	void exitQuitStmt(SQLParser.QuitStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#showTableStmt}.
	 * @param ctx the parse tree
	 */
	void enterShowTableStmt(SQLParser.ShowTableStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#showTableStmt}.
	 * @param ctx the parse tree
	 */
	void exitShowTableStmt(SQLParser.ShowTableStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#insertStmt}.
	 * @param ctx the parse tree
	 */
	void enterInsertStmt(SQLParser.InsertStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#insertStmt}.
	 * @param ctx the parse tree
	 */
	void exitInsertStmt(SQLParser.InsertStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#valueEntry}.
	 * @param ctx the parse tree
	 */
	void enterValueEntry(SQLParser.ValueEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#valueEntry}.
	 * @param ctx the parse tree
	 */
	void exitValueEntry(SQLParser.ValueEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#selectStmt}.
	 * @param ctx the parse tree
	 */
	void enterSelectStmt(SQLParser.SelectStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#selectStmt}.
	 * @param ctx the parse tree
	 */
	void exitSelectStmt(SQLParser.SelectStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#createViewStmt}.
	 * @param ctx the parse tree
	 */
	void enterCreateViewStmt(SQLParser.CreateViewStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#createViewStmt}.
	 * @param ctx the parse tree
	 */
	void exitCreateViewStmt(SQLParser.CreateViewStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#dropViewStmt}.
	 * @param ctx the parse tree
	 */
	void enterDropViewStmt(SQLParser.DropViewStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#dropViewStmt}.
	 * @param ctx the parse tree
	 */
	void exitDropViewStmt(SQLParser.DropViewStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#updateStmt}.
	 * @param ctx the parse tree
	 */
	void enterUpdateStmt(SQLParser.UpdateStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#updateStmt}.
	 * @param ctx the parse tree
	 */
	void exitUpdateStmt(SQLParser.UpdateStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#columnDef}.
	 * @param ctx the parse tree
	 */
	void enterColumnDef(SQLParser.ColumnDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#columnDef}.
	 * @param ctx the parse tree
	 */
	void exitColumnDef(SQLParser.ColumnDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#typeName}.
	 * @param ctx the parse tree
	 */
	void enterTypeName(SQLParser.TypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#typeName}.
	 * @param ctx the parse tree
	 */
	void exitTypeName(SQLParser.TypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterColumnConstraint(SQLParser.ColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitColumnConstraint(SQLParser.ColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#multipleCondition}.
	 * @param ctx the parse tree
	 */
	void enterMultipleCondition(SQLParser.MultipleConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#multipleCondition}.
	 * @param ctx the parse tree
	 */
	void exitMultipleCondition(SQLParser.MultipleConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(SQLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(SQLParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#comparer}.
	 * @param ctx the parse tree
	 */
	void enterComparer(SQLParser.ComparerContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#comparer}.
	 * @param ctx the parse tree
	 */
	void exitComparer(SQLParser.ComparerContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#comparator}.
	 * @param ctx the parse tree
	 */
	void enterComparator(SQLParser.ComparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#comparator}.
	 * @param ctx the parse tree
	 */
	void exitComparator(SQLParser.ComparatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SQLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SQLParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterTableConstraint(SQLParser.TableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitTableConstraint(SQLParser.TableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#resultColumn}.
	 * @param ctx the parse tree
	 */
	void enterResultColumn(SQLParser.ResultColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#resultColumn}.
	 * @param ctx the parse tree
	 */
	void exitResultColumn(SQLParser.ResultColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#tableQuery}.
	 * @param ctx the parse tree
	 */
	void enterTableQuery(SQLParser.TableQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#tableQuery}.
	 * @param ctx the parse tree
	 */
	void exitTableQuery(SQLParser.TableQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#authLevel}.
	 * @param ctx the parse tree
	 */
	void enterAuthLevel(SQLParser.AuthLevelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#authLevel}.
	 * @param ctx the parse tree
	 */
	void exitAuthLevel(SQLParser.AuthLevelContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#literalValue}.
	 * @param ctx the parse tree
	 */
	void enterLiteralValue(SQLParser.LiteralValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#literalValue}.
	 * @param ctx the parse tree
	 */
	void exitLiteralValue(SQLParser.LiteralValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#columnFullName}.
	 * @param ctx the parse tree
	 */
	void enterColumnFullName(SQLParser.ColumnFullNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#columnFullName}.
	 * @param ctx the parse tree
	 */
	void exitColumnFullName(SQLParser.ColumnFullNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void enterDatabaseName(SQLParser.DatabaseNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void exitDatabaseName(SQLParser.DatabaseNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(SQLParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(SQLParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#userName}.
	 * @param ctx the parse tree
	 */
	void enterUserName(SQLParser.UserNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#userName}.
	 * @param ctx the parse tree
	 */
	void exitUserName(SQLParser.UserNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(SQLParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(SQLParser.ColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#viewName}.
	 * @param ctx the parse tree
	 */
	void enterViewName(SQLParser.ViewNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#viewName}.
	 * @param ctx the parse tree
	 */
	void exitViewName(SQLParser.ViewNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SQLParser#password}.
	 * @param ctx the parse tree
	 */
	void enterPassword(SQLParser.PasswordContext ctx);
	/**
	 * Exit a parse tree produced by {@link SQLParser#password}.
	 * @param ctx the parse tree
	 */
	void exitPassword(SQLParser.PasswordContext ctx);
}