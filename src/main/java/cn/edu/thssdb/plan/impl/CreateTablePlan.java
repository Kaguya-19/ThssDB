package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;
import cn.edu.thssdb.schema.Column;

import java.util.ArrayList;

public class CreateTablePlan extends LogicalPlan {
    private String tableName;
    private ArrayList<Column> columns;

    public CreateTablePlan(String tableName, ArrayList<Column> columns) {
        super(LogicalPlanType.CREATE_TABLE);
        this.tableName = tableName;
        this.columns = new ArrayList<>(columns);
    }

    @Override
    public String toString() {
        String log = "CreateTablePlan{ " + "\ntableName='" + tableName + '\'';
        for (Column c: columns) {
            log = log + "\n\tcolumn: " + c.toString();
        }
        return log + "\n}";
    }

    public String getTableName() { return tableName; }

    public ArrayList<Column> getColumns() { return columns; }
}
