package org.jbpm.workflow.instance.impl.queue;

import org.jbpm.workflow.instance.NodeInstance;
import org.jbpm.workflow.instance.impl.NodeInstanceImpl;

public class NodeInstanceTriggerAction implements ProcessInstanceAction {

    private final String type;
    private final NodeInstance nodeInstance;
    private final NodeInstance from;

    public NodeInstanceTriggerAction(NodeInstance nodeInstance, NodeInstance from, String toType) {
        this.nodeInstance = nodeInstance;
        this.from = from;
        this.type = toType;
    }

    public NodeInstanceTriggerAction(NodeInstance nodeInstance, String toType) {
        this( nodeInstance, null, toType); // because "this" is used
    }

    public NodeInstance getNodeInstance() {
        return nodeInstance;
    }

    public String getType() {
        return type;
    }

    @Override
    public void trigger() {
        nodeInstance.trigger(from, type);
    }

    @Override
    public String getUniqueInstanceId() {
        // OCRAM getUniqueInstanceId => equals(NodeInstance)
       return ((NodeInstanceImpl) nodeInstance).getUniqueId();
    }

    @Override
    public String toString() {
        String uniqueId = (String) ((NodeInstanceImpl) nodeInstance).getMetaData().get("UniqueId");
        String fromStr = from == null ? "null" : from.getClass().getSimpleName();
        return nodeInstance.getClass().getSimpleName() + "[" + uniqueId + "].trigger(" + fromStr + ", " + type + ")";
    }



}