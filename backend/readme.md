# 注意事项
`com.backend.utils.config.SecurityConfig`类配置了`doFiter`方法的拦截逻辑，
业务上公共URL只有`/user/login`，即登录页面，登录后，注意传递JWT-token进行其他页面的访问，
定义请求头字段`Authorization`，并以`Bearer `开头(注意空格)，再附上JWT-token码，
以上事宜未尽，会出现403错误。

# 组件
- knife4j: 访问`localhost:3000/doc.html`查看、在线调试接口
- 全局异常处理器：抛出异常会根据异常类型匹配`GlobalExceptionHandler`类的方法中，由方法返回对应的信息