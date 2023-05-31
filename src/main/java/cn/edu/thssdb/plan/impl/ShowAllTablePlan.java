package cn.edu.thssdb.plan.impl;

import cn.edu.thssdb.plan.LogicalPlan;

public class ShowAllTablePlan extends LogicalPlan{

    public ShowAllTablePlan() {
        super(LogicalPlanType.SHOW_ALL_TABLE);
    }

    @Override
    public String toString() {
        return "ShowAllTablePlan{}";
    }

}
