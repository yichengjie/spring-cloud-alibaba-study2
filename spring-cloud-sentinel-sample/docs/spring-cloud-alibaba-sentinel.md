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
#### 手动配置
1. 手动配置流控规则
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
2. ~~利用SPI扩展点机制加载流控规则(经测试发现不生效)~~
    ```text
    4.1 在/resource/META-INF/创建com.alibaba.csp.sentinel.init.InitFunc文件~~
    4.2 文件内容为com.yicj.study.sentinel.init.FlowRuleInitFunc~~
    ```
3. 因为SPI扩展点不生效，这里我们可以使用ApplicationRunner手动加载
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
#### 整合dashboard配置
1. 添加配置
    ```text
    spring.cloud.sentinel.transport.port=8719
    spring.cloud.sentinel.transport.dashboard=192.168.221.128:8080
    ```
2. 通过dashboard配置即可
#### 自定义url限流异常
1. 通过实现特定接口返回自定义信息
    ```text
    @Service
    public class CustomUrlBlockHandler implements BlockExceptionHandler {
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
            response.setHeader("Content-Type","application/json;charset=UTF-8");
            // {"code":999, "msg":"访问人数过多"}
            String msg = "{\"code\":999, \"msg\":\"访问人数过多\"}" ;
            response.getWriter().write(msg);
        }
    }
    ```
2. 限流触发跳转自定义页面
    ```text
    spring.cloud.sentinel.block-page={url}
    ```
#### 资源清晰
1. 流控资源
    ```text
    @GetMapping("/clean/{id}")
    public String clean(@PathVariable("id") int id){
        return "Hello clean" ;
    }
    ```
2. 清洗组件
    ```text
    @Component
    public class CustomerUrlCleaner implements UrlCleaner {
        @Override
        public String clean(String originUrl) {
            if (StringUtils.isEmpty(originUrl)){
                return originUrl;
            }
            if (originUrl.startsWith("/clean/")){
                return "/clean/*" ;
            }
            return originUrl;
        }
    }
    ```