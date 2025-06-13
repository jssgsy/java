package com.univ.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.junit.Test;

import java.util.List;

/**
 * @author univ
 * date 2025/4/22
 */
public class ActivitiTest {
    ProcessEngine processEngine = ActivitiEngineUtil.getProcessEngine();

    @Test
    public void deploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/First.bpmn20.xml") // 类路径下
                .deploy();
        System.out.println("deployment.getId(): " + deployment.getId());
        System.out.println("deployment.getDeploymentTime(): " + deployment.getDeploymentTime());
        System.out.println("deployment.getName(): " + deployment.getName());
        System.out.println("deployment.getCategory(): " + deployment.getCategory());
    }

    @Test
    public void queryDeployInfo() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        // 用id查肯定只有一条
        deploymentQuery.deploymentId("2501");
        // 最终的查询结果也是由DeploymentQuery发起
        List<Deployment> list = deploymentQuery.list();
        // [DeploymentEntity[id=2501, name=null]]
        System.out.println(list);
    }

    @Test
    public void queryProcessDefinitionInfo() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.deploymentId("2501");
        List<ProcessDefinition> list = processDefinitionQuery.list();
        // [ProcessDefinitionEntity[First:2:2503]]
        System.out.println(list);
    }

    @Test
    public void startInstance() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String processDefinitionId = "First:2:2503";
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId);
        System.out.println(processInstance);
    }

    @Test
    public void queryInstance() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        String processInstanceId = "7501";
        ProcessInstanceQuery processInstanceQuery1 = processInstanceQuery.processInstanceId(processInstanceId);
        System.out.println(processInstanceQuery1);
    }

}
