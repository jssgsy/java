package com.univ.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * @author univ
 * date 2025/4/22
 */
public class ActivitiEngineUtil {

    private static ProcessEngine processEngine;

    public static ProcessEngine getProcessEngine() {
        if (processEngine == null) {
            synchronized (ActivitiEngineUtil.class) {
                if (processEngine == null) {
                    processEngine = buildProcessEngine();
                }
            }
        }
        return processEngine;
    }

    private static ProcessEngine buildProcessEngine() {
        StandaloneProcessEngineConfiguration configuration =
                (StandaloneProcessEngineConfiguration) ProcessEngineConfiguration
                        .createStandaloneProcessEngineConfiguration();

        // 数据库配置
        configuration.setJdbcUrl("jdbc:mysql://localhost:3316/activiti_db?useUnicode=true&characterEncoding=UTF-8");
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("root");

        // 数据库策略
        // false: 检查数据库表结构，如果不匹配则抛出异常
        // true: 构建流程引擎时，检查数据库表结构，如果不匹配则更新
        // create-drop: 构建流程引擎时创建数据库表，关闭时删除
        // drop-create: 先删除表再创建表
        configuration.setDatabaseSchemaUpdate("false");

        // 不使用Activiti自带的用户权限管理
        // configuration.setid(false);

        // 此时不会执行对应的history.sql文件
        configuration.setDbHistoryUsed(true);

        // 异步执行器配置（可选）
        configuration.setAsyncExecutorActivate(false);

        return configuration.buildProcessEngine();
    }
}
