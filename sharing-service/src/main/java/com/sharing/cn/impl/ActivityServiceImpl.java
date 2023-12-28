package com.sharing.cn.impl;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import java.util.List;

/**
 * @author ext.shikai1
 */
public class ActivityServiceImpl {

    /**
     * 获取流程引擎的工具类
     * ProcessEngines.使用默认方式获取配置文件，构造流程引擎。配置文件名字activiti.cfg. xml,放在classpath下
     * ProcessEngineConfiguration.可以自定义配置文件名
     *
     * 使用_上面2个工具类，都可以获得流程引擎
     * ProcessEngine :流程引擎。获取各种服务的接口。
     * 服务接口:用于流程的部署、执行、管理，使用这些接口就是在操作对应的数据表
     * RepositoryService 资源管理类
     * RuntimeService 运行时管理类
     * TaskService 任务管理类
     * HistoryService 历史数据管理类
     * ManagementService 流程引擎管理类
     */
    public void create() {
        // 创建
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    }
    public void deploy() {
        // 部署
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment().deploy();
        deploy.getKey();
    }

    public void startProcess(String key) {
        // 启动
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
    }

    public void findCommission(String key) {
        // 代办查询
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("")
                .list();
    }

    public void complete(String key) {
        // 完成
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("")
                .singleResult();
        taskService.complete(task.getId());
    }

    public void find() {
        ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("");
    }

}
