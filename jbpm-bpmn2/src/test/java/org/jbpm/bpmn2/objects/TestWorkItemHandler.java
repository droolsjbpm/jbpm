/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.bpmn2.objects;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class TestWorkItemHandler implements WorkItemHandler {

    private List<WorkItem> workItems = new ArrayList<>();
    private List<WorkItem> abortedWorkItems = new ArrayList<>();

    public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
        workItems.add(workItem);
    }

    public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
        abortedWorkItems.add(workItem);
    }

    public WorkItem getWorkItem() {
        if (workItems.size() == 0) {
            return null;
        }
        if (workItems.size() == 1) {
            WorkItem result = workItems.get(0);
            this.workItems.clear();
            return result;
        } else {
            throw new IllegalArgumentException("More than one work item active");
        }
    }

    public List<WorkItem> getAbortedWorkItems() {
        List<WorkItem> result = new ArrayList<>(abortedWorkItems);
        abortedWorkItems.clear();
        return result;
    }

    public List<WorkItem> getWorkItems() {
        List<WorkItem> result = new ArrayList<>(workItems);
        workItems.clear();
        return result;
    }

}
