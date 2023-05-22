/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.edu.thssdb.parser;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.plan.impl.*;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.sql.SQLBaseVisitor;
import cn.edu.thssdb.sql.SQLParser;

import java.util.ArrayList;

public class ThssDBSQLVisitor extends SQLBaseVisitor {
  private Manager manager;

  @Override
  public LogicalPlan visitQuitStmt(SQLParser.QuitStmtContext ctx) {
    return new QuitPlan();
  }

  @Override
  public LogicalPlan visitCreateDbStmt(SQLParser.CreateDbStmtContext ctx) {
    return new CreateDatabasePlan(ctx.databaseName().getText());
  }

  @Override
  public LogicalPlan visitDropDbStmt(SQLParser.DropDbStmtContext ctx) {
    return new DropDatabasePlan(ctx.databaseName().getText());
  }

  @Override
  public LogicalPlan visitUseDbStmt(SQLParser.UseDbStmtContext ctx) {
    return new UseDatabasePlan(ctx.databaseName().getText());
  }

  @Override
  public LogicalPlan visitCreateTableStmt(SQLParser.CreateTableStmtContext ctx) {
    ArrayList<Column> columns = new ArrayList<>();
    ArrayList<String> columnName = new ArrayList<>();

    for (SQLParser.ColumnDefContext context : ctx.columnDef()) {
      String name = context.columnName().getChild(0).toString();
      String type = context.typeName().getChild(0).toString();

      int maxLength = 0;
      if (type.toUpperCase().equals("STRING")) {
        maxLength = Integer.parseInt(context.typeName().getChild(2).toString());
      }
      boolean notNull = context.getChildCount() > 2 ? true : false;
      columns.add(new Column(name, Column.str2ColType(type), 0, notNull, maxLength));
      columnName.add(name);
    }
    // only one primary key, so the efficiency is considerable
    if (ctx.tableConstraint() != null) {
      for (SQLParser.ColumnNameContext context : ctx.tableConstraint().columnName()) {
        String name = context.getChild(0).toString();
        columns.get(columnName.indexOf(name)).setPrimary(1);
      }
    }
    return new CreateTablePlan(ctx.tableName().getText(), columns);
  }

  @Override
  public LogicalPlan visitDropTableStmt(SQLParser.DropTableStmtContext ctx) {
    return new DropTablePlan(ctx.tableName().getText());
  }

  @Override
  public LogicalPlan visitShowTableStmt(SQLParser.ShowTableStmtContext ctx) {
    return new ShowTablePlan(ctx.tableName().getText());
  }

  @Override
  public LogicalPlan visitInsertStmt(SQLParser.InsertStmtContext ctx) {
    ArrayList<String> columnNames = new ArrayList<>();
    ArrayList<String> values = new ArrayList<>();
    for (SQLParser.ColumnNameContext context : ctx.columnName()) {
      columnNames.add(context.getText());
    }
    for (SQLParser.ValueEntryContext context : ctx.valueEntry()) {
      for (SQLParser.LiteralValueContext context1 : context.literalValue()) {
        values.add(context1.getText());
      }
    }
    return new InsertPlan(ctx.tableName().getText(), columnNames, values);
  }

  @Override
  public LogicalPlan visitDeleteStmt(SQLParser.DeleteStmtContext ctx) {

    MultipleConditions multipleConditions = (MultipleConditions) visit(ctx.multipleCondition());

    return new DeletePlan(ctx.tableName().getText(), multipleConditions);
  }

  @Override
  public LogicalPlan visitUpdateStmt(SQLParser.UpdateStmtContext ctx) {
    MultipleConditions multipleConditions = (MultipleConditions) visit(ctx.multipleCondition());
    String columnName = ctx.columnName().getText();
    String value = ctx.expression().getText(); // TODO: check expression
    return new UpdatePlan(ctx.tableName().getText(), columnName, value, multipleConditions);
  }

  //    @Override
  //    public LogicalPlan visitSelectStmt(SQLParser.SelectStmtContext ctx) {
  //
  ////      return new SelectPlan(columnFullNames, tableQueries, conditions);
  //    }
  //
  //  @Override
  //  public ArrayList<ColumnFullName> visitSelectElements(SQLParser.SelectElementsContext ctx) {
  //    ArrayList<ColumnFullName> columnFullNames = new ArrayList<>();
  //    for (SQLParser.ResultColumnContext context : ctx.resultColumn()) {
  //      columnFullNames.add((ColumnFullName) visit(context));
  //    }
  //    return columnFullNames;
  //  }
  //
  //  @Override
  //  public ColumnFullName visitResultColumn(SQLParser.ResultColumnContext ctx) {
  //    if (ctx.getChildCount() == 1) {
  //      return new ColumnFullName(ctx.getChild(0).getText());
  //    } else { // tableName '.' columnName or '*'
  //      return new ColumnFullName(ctx.getChild(2).getText(), ctx.getChild(0).getText());
  //    }
  //  }
  //
  //  @Override
  //  public ArrayList<TableQuery> visitTableQueries(SQLParser.TableQueriesContext ctx) {
  //    ArrayList<TableQuery> tableQueries = new ArrayList<>();
  //    for (SQLParser.TableQueryContext context : ctx.tableQuery()) {
  //      tableQueries.add((TableQuery) visit(context));
  //    }
  //    return tableQueries;
  //  }
  //
  //  @Override
  //  public TableQuery visitTableQuery(SQLParser.TableQueryContext ctx) {
  //    // if (ctx.getChildCount() == 1) {
  //    return new TableQuery(ctx.tableName().getText());
  //    // } else { //TODO:Join
  //    //   return new TableQuery(ctx.tableName().getText(), ctx.getChild(2).getText());
  //    // }
  //  }
  //
  @Override
  public MultipleConditions visitMultipleCondition(SQLParser.MultipleConditionContext ctx) {
    // tree from root
    MultipleConditions root = new MultipleConditions(null, null, null);
    // set by preOrderTraversal
    if (ctx.getChildCount() == 1) {
      root.setValue((Condition) visit(ctx.getChild(0)));
    } else {
      root.setLeft((MultipleConditions) visit(ctx.getChild(0)));
      root.setRight((MultipleConditions) visit(ctx.getChild(2)));
      root.setValue(ctx.getChild(1).getText());
    }
    return root;
  }
  //
  //  @Override
  //  public Object visitCondition(SQLParser.ConditionContext ctx) {
  //    return Condition(visit(ctx.comparer(0)), visit(ctx.comparer(1)), ctx.getChild(1).getText());
  //  }
  //
  //  @Override
  //  public Object visitExpression(SQLParser.ExpressionContext ctx) {
  //    return super.visitExpression(ctx);
  //    // TODO: complex expression
  //  }
  //
  //  @Override
  //  public Object visitComparer(SQLParser.ComparerContext ctx) {
  //    if (ctx.getChild(0) instanceof SQLParser.ColumnFullNameContext) {
  //      return visit(ctx.getChild(0)); // columnFullName
  //    } else {
  //      return ctx.getChild(0).getText(); // literalValue
  //    }
  //  }
  //
  //  @Override
  //  public String visitTableName(SQLParser.TableNameContext ctx) {
  //    return ctx.getChild(0).getText().toUpperCase();
  //    // IDENTIFIER
  //  }
  //
  //  @Override
  //  public String visitColumnName(SQLParser.ColumnNameContext ctx) {
  //    return ctx.getChild(0).getText().toUpperCase();
  //    // IDENTIFIER
  //  }
  //
  //  @Override
  //  public String visitLiteralValue(SQLParser.LiteralValueContext ctx) {
  //    return ctx.getChild(0).getText();
  //    // NUMERIC_LITERAL | STRING_LITERAL | K_NULL
  //  }
  //
  //  @Override
  //  public String visitDatabaseName(SQLParser.DatabaseNameContext ctx) {
  //    return ctx.getChild(0).getText().toUpperCase();
  //    // IDENTIFIER
  //  }
  //
  //  @Override
  //  public ColumnFullName visitColumnFullName(SQLParser.ColumnFullNameContext ctx) {
  //    if (ctx.getChildCount() > 1) {
  //      return new ColumnFullName(ctx.columnName().getText(), ctx.tableName().getText());
  //    } else {
  //      return new ColumnFullName(ctx.columnName().getText());
  //    }
  //  }
  // TODO: parser to more logical plan
}
