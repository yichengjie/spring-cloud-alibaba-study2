1. 添加依赖
    ```text
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>
    ```
2. 创建rest接口，并通过SentinelResource配置限流保护资源
    ```text
    @GetMapping
    @SentinelResource(value = "hello", blockHandler = "blockHandlerHello")
    public String say(){
        return "hello ,yicj" ;
    }
    
    public String blockHandlerHello(BlockException e){
        return "被限流了" ;
    }
    ```
3. 手动配置流控规则
    ```text
    public class FlowRuleInitFunc implements InitFunc {
        @Override
        public void init() throws Exception {
            List<FlowRule> rules = new ArrayList<>() ;
            FlowRule rule = new FlowRule() ;
            rule.setCount(1) ;
            rule.setResource("hello") ;
            rule.setGrade(RuleConstant.FLOW_GRADE_QPS) ;
            rule.setLimitApp("default") ;
            rules.add(rule) ;
            FlowRuleManager.loadRules(rules);
        }
    }
    ```
4. ~~利用SPI扩展点机制加载流控规则(经测试发现不生效)~~
    ```text
    4.1 在/resource/META-INF/创建com.alibaba.csp.sentinel.init.InitFunc文件~~
    4.2 文件内容为com.yicj.study.sentinel.init.FlowRuleInitFunc~~
    ```
5. 因为SPI扩展点不生效，这里我们可以使用ApplicationRunner手动加载
    ```text
    @Component
    public class MyAppRunner implements ApplicationRunner {
        @Override
        public void run(ApplicationArguments args) throws Exception {
            // 加载流控规则
            new FlowRuleInitFunc().init();
        }
    }
    ```