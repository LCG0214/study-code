## maven-jsonassert
**作用**：用来比较两个json数据是否存在差异，可以把具体的差异结果获取出来

**JSONAssert中有四种模式**： 
- LENIENT：宽容模式，即实际JSON包含扩展字段，数组顺序不一致也可以通过测试
- STRICT：严格模式，即实际JSON不可扩展，数组严格排序才可以通过测试
- NON_EXTENSIBLE：非扩展模式，即实际JSON不可扩展，数组顺序不一致也可以通过测试
- STRICT_ORDER：严格排序模式，即实际JSON可扩展，但数组严格排序才可以通过测试

具体的实际用法参考：`src/main/java/cn/lcgui/MainTest.java`中的内容
