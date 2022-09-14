# DataStructureAndAlgorithm
数据结构和算法
```java
@Test
public void 启动一个流程(){
    ProcessEngine processEngine = configuration.buildProcessEngine();
    // 我们需要通过RuntimeService来启动流程实例
    RuntimeService runtimeService = processEngine.getRuntimeService();
    // 构建流程变量
    Map<String,Object> variables = new HashMap<String,Object>();
    variables.put("user1","张三") ;// 谁申请请假
    variables.put("user2","李四"); // 请几天假
    // 启动流程实例
    ProcessInstance holidayRequest = runtimeService.startProcessInstanceById("fenpeiUEL:1:87504",
            "order10002"// 绑定一个 业务订单
            ,variables);
    System.out.println("holidayRequest.getProcessDefinitionId() = " + holidayRequest.getProcessDefinitionId());
    System.out.println("holidayRequest.getActivityId() = " + holidayRequest.getActivityId());
    System.out.println("holidayRequest.getId() = " + holidayRequest.getId());
}
```
