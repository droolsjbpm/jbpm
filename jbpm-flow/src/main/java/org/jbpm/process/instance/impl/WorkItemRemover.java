package org.jbpm.process.instance.impl;

public interface WorkItemRemover {

    public void removeWorkItem(long workItemId);
    public void removeWorkItem(Object workItemInfo, long workItemId);
}