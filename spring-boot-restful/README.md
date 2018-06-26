## Springmvc常用注解：
* **@Controller**：修饰控制器类，用来创建处理http请求的对象
* **@RestController**：Spring4之后加入的注解，原来在 **@Controller** 中返回json需要 **@ResponseBody** 来配合，如果直接用 **@RestController** 替代 **@Controller** 就不需要再配置 **@ResponseBody**，默认返回json格式。
* **@RequestMapping**：配置url映射。以下四个注解是简化版的 **@RequestMapping**
    * **GetMapping**
    * **PutMapping**
    * **PostMapping**
    * **DeleteMapping**
* **@PathVariable**：参数放置在url的路径中
* **@RequestParam**：获取请求参数