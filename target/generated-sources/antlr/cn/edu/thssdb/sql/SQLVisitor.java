// Generated from SQL.g4 by ANTLR 4.8

package cn.edu.thssdb.sql;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SQLParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(SQLParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#sqlStmtList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStmtList(SQLParser.SqlStmtListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#sqlStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStmt(SQLParser.SqlStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#createDbStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDbStmt(SQLParser.CreateDbStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#dropDbStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropDbStmt(SQLParser.DropDbStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#createUserStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateUserStmt(SQLParser.CreateUserStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#dropUserStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropUserStmt(SQLParser.DropUserStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#createTableStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTableStmt(SQLParser.CreateTableStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#grantStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrantStmt(SQLParser.GrantStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#revokeStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRevokeStmt(SQLParser.RevokeStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#useDbStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseDbStmt(SQLParser.UseDbStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#deleteStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteStmt(SQLParser.DeleteStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#dropTableStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTableStmt(SQLParser.DropTableStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#showDbStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowDbStmt(SQLParser.ShowDbStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#quitStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuitStmt(SQLParser.QuitStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#showTableStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowTableStmt(SQLParser.ShowTableStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#insertStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStmt(SQLParser.InsertStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#valueEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValueEntry(SQLParser.ValueEntryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#selectStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStmt(SQLParser.SelectStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#createViewStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateViewStmt(SQLParser.CreateViewStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#dropViewStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropViewStmt(SQLParser.DropViewStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#updateStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateStmt(SQLParser.UpdateStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#columnDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDef(SQLParser.ColumnDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#typeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeName(SQLParser.TypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnConstraint(SQLParser.ColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#multipleCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleCondition(SQLParser.MultipleConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(SQLParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#comparer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparer(SQLParser.ComparerContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#comparator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparator(SQLParser.ComparatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SQLParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableConstraint(SQLParser.TableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#resultColumn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResultColumn(SQLParser.ResultColumnContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#tableQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableQuery(SQLParser.TableQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#authLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAuthLevel(SQLParser.AuthLevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#literalValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralValue(SQLParser.LiteralValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#columnFullName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnFullName(SQLParser.ColumnFullNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#databaseName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatabaseName(SQLParser.DatabaseNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(SQLParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#userName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserName(SQLParser.UserNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#columnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(SQLParser.ColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#viewName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewName(SQLParser.ViewNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SQLParser#password}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassword(SQLParser.PasswordContext ctx);
}