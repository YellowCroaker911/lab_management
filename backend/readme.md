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
- 数据库备份：在knife4j调用DatabaseController的backupDatabase接口进行数据库备份 ，备份文件会放到./backup目录中
- 数据库恢复：`
mysql -h 127.0.0.1 -P 3306 -u{用户名} -p{密码}
use club
source {sql文件路径}
`
# 关于测试
- 设计一套符合数据库约束，（包括每个字段comment中的约定）的数据，对每一个接口做一次简单的测试，
如果遇到报错，先记录下来，将测试的API和数据以及报错信息记录到文档里（最好适当截图）。
测试尽量规范一点，届时可以直接写入报告。

- 当得到理想的测试数据并导入数据库后，可以直接将数据库备份成sql文件，之后测试直接把备份的sql文件重新加载到数据库中即可。

- 测试可通过knife4j进行，主要需要传递登录用户的jwt-token后才可以访问非公共URL接口。