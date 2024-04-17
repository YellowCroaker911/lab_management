# 注意事项
- 认证访问：`com.backend.utils.config.SecurityConfig`类配置了`doFiter`方法的拦截逻辑，
业务上公共URL只有`/user/login`，即登录页面，登录后，注意传递JWT-token进行其他页面的访问，
定义请求头字段`Authorization`，并以`Bearer `开头(注意空格)，再附上JWT-token码，
以上事宜未尽，会出现403错误。
- 更新操作：更新操作(涉及到DTO)确保对不更新的字段传入原有的值，若不传值则会将字段更新为空
- JSON返回：返回的JSON统一为`ResponseData`类实例

# 组件
- knife4j: 访问`localhost:3000/doc.html`查看、在线调试接口

# 笔记
- Validated注解：控制器上注解`Validated`，`RequestParam`才会进行数据校验(直接在参数上注解`Validated`也没用)， 
对于`RequestBody`还要再注解`Validated`。
- 全局异常处理：全局异常处理器`GlobalExceptionHandler`会优先匹配子异常处理方法(带`ExceptionHandler`注解)，再匹配父异常处理方法，
调试过程中可以把常出现的异常拎出来定义一个异常处理方法（对应一个`ReturnCode`），把异常信息返回给前端。
- SQL语句包裹：mapper xml内的sql语句最好用`<![CDATA[]]>`包裹以避免出现XML的特殊字符而造成编译错误