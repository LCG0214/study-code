package cn.lcgui;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.*;

public class MainTest {

    /**
     * LENIENT：宽容模式，即实际JSON包含扩展字段，数组顺序不一致也可以通过测试
     * STRICT：严格模式，即实际JSON不可扩展，数组严格排序才可以通过测试
     * NON_EXTENSIBLE：非扩展模式，即实际JSON不可扩展，数组顺序不一致也可以通过测试
     * STRICT_ORDER：严格排序模式，即实际JSON可扩展，但数组严格排序才可以通过测试
     */

    String json1 = "{\"name\":\"lcgui\",\"age\":18,\"hobby\":[\"football\",\"basketball\"],\"address\":{\"city\":\"beijing\",\"street\":\"chaoyang\"}}";

    // 这里和json1完全一样
    String json2 = "{\"name\":\"lcgui\",\"age\":18,\"hobby\":[\"football\",\"basketball\"],\"address\":{\"city\":\"beijing\",\"street\":\"chaoyang\"}}";

    // 这里我相较于json1添加了一个sex字段
    String json3 = "{\"name\":\"lcgui\",\"age\":18,\"sex\":\"男\",\"hobby\":[\"football\",\"basketball\"],\"address\":{\"city\":\"beijing\",\"street\":\"chaoyang\"}}";

    // 相较于json1将name字段和age字段顺序颠倒
    String json4 = "{\"age\":18,\"name\":\"lcgui\",\"hobby\":[\"football\",\"basketball\"],\"address\":{\"city\":\"beijing\",\"street\":\"chaoyang\"}}";

    // 相较于json1修改age字段的值18->20
    String json5 = "{\"name\":\"lcgui\",\"age\":20,\"hobby\":[\"football\",\"basketball\"],\"address\":{\"city\":\"beijing\",\"street\":\"chaoyang\"}}";

    // 这里吧hobby数组中的数据顺序颠倒
    String json6 = "{\"name\":\"lcgui\",\"age\":18,\"hobby\":[\"basketball\",\"football\"],\"address\":{\"city\":\"beijing\",\"street\":\"chaoyang\"}}";

    // 这里我相较于json1将hobby数组中的football替换为pingpong
    String json7 = "{\"name\":\"lcgui\",\"age\":18,\"hobby\":[\"pingpong\",\"basketball\"],\"address\":{\"city\":\"beijing\",\"street\":\"chaoyang\"}}";
    /**
     * LENIENT：宽容模式，即实际JSON包含扩展字段，数组顺序不一致也可以通过测试
     */
    @Test
    public void LENIENT_Test1_2() { // json1和json2完全一样
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json2, JSONCompareMode.LENIENT);
    }

    @Test
    public void LENIENT_Test1_3(){ // json3相较于json1添加了一个sex字段
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json3, JSONCompareMode.LENIENT);
    }

    @Test
    public void LENIENT_Test1_4(){ // json4相较于json1将name字段和age字段顺序颠倒
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json4, JSONCompareMode.LENIENT);
    }

    @Test
    public void LENIENT_Test1_5(){ // json5相较于json1修改age字段的值18->20
        // 结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json5, JSONCompareMode.LENIENT);
    }

    @Test
    public void LENIENT_Test1_6(){ // json6相较于json1将hobby数组中的数据顺序颠倒
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json6, JSONCompareMode.LENIENT);
    }

    @Test
    public void LENIENT_Test1_7(){ // json7相较于json1将hobby数组中的football替换为pingpong
        // 结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json7, JSONCompareMode.LENIENT);
    }

    /**
     * STRICT：严格模式，即实际JSON不可扩展，数组严格排序才可以通过测试
     */
    @Test
    public void STRICT_Test1_2() { // json1和json2完全一样
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json2, JSONCompareMode.STRICT);
    }

    @Test
    public void STRICT_Test1_3(){ // json3相较于json1添加了一个sex字段
        //结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json3, JSONCompareMode.STRICT);
    }

    @Test
    public void STRICT_Test1_4(){ // json4相较于json1将name字段和age字段顺序颠倒
        //结果：通过（没有任何输出或者报错等信息提示） 值得注意的是 这里是 对象中的字段顺序颠倒  还是会通过的， 数组中的字段顺序颠倒  不会通过。
        JSONAssert.assertEquals(json1, json4, JSONCompareMode.STRICT);
    }

    @Test
    public void STRICT_Test1_5(){ // json5相较于json1修改age字段的值18->20
        // 结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json5, JSONCompareMode.STRICT);
    }

    @Test
    public void STRICT_Test1_6(){ // json6相较于json1将hobby数组中的数据顺序颠倒
        //结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json6, JSONCompareMode.STRICT);
    }

    @Test
    public void STRICT_Test1_7(){ // json7相较于json1将hobby数组中的football替换为pingpong
        // 结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json7, JSONCompareMode.STRICT);
    }

    /**
     * STRICT_ORDER：严格排序模式，即实际JSON可扩展，但数组严格排序才可以通过测试
     */
    @Test
    public void STRICT_ORDER_Test1_2() { // json1和json2完全一样
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json2, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void STRICT_ORDER_Test1_3(){ // json3相较于json1添加了一个sex字段
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json3, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void STRICT_ORDER_Test1_4(){ // json4相较于json1将name字段和age字段顺序颠倒
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json4, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void STRICT_ORDER_Test1_5(){ // json5相较于json1修改age字段的值18->20
        // 结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json5, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void STRICT_ORDER_Test1_6(){ // json6相较于json1将hobby数组中的数据顺序颠倒
        //结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json6, JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void STRICT_ORDER_Test1_7(){ // json7相较于json1将hobby数组中的football替换为pingpong
        // 结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json7, JSONCompareMode.STRICT_ORDER);
    }

    /**
     * NON_EXTENSIBLE：非扩展模式，即实际JSON不可扩展，数组顺序不一致也可以通过测试
     */
    @Test
    public void NON_EXTENSIBLE_Test1_2() { // json1和json2完全一样
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json2, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void NON_EXTENSIBLE_Test1_3(){ // json3相较于json1添加了一个sex字段
        // 结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json3, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void NON_EXTENSIBLE_Test1_4(){ // json4相较于json1将name字段和age字段顺序颠倒
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json4, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void NON_EXTENSIBLE_Test1_5(){ // json5相较于json1修改age字段的值18->20
        // 结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json5, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void NON_EXTENSIBLE_Test1_6(){ // json6相较于json1将hobby数组中的数据顺序颠倒
        //结果：通过（没有任何输出或者报错等信息提示）
        JSONAssert.assertEquals(json1, json6, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void NON_EXTENSIBLE_Test1_7(){ // json7相较于json1将hobby数组中的football替换为pingpong
        // 结果：不通过（产生断言失败，也会终止程序 如果后续有代码会执行不到）
        JSONAssert.assertEquals(json1, json7, JSONCompareMode.NON_EXTENSIBLE);
    }

    /**
     *---------------------------------------------上面的这些用来判断JSON之间的关系，可以直接观察到，但是没有办法拿到相关的信息，例如：不同字段的路径，不同字段的信息等，来做后续的处理
     * 所以要通过下面的方法来获取相关的信息，然后做后续的处理--------------------------------------------------------
     */


    @Test
    public void JSONCompareTest() throws JSONException { // 可以用来获取比较的结果惊醒后续的操作
        String expectedJson = "{\"name\":\"lcgui\",\"age\":18,\"hobby\":[\"basketball\",\"football\"],\"address\":{\"city\":\"beijing\",\"street\":\"chaoyang\"}}";
        // 这里修改了年龄：18 -> 20，地址：beijing -> nanjing，hobby：basketball -> pingpong
        String actualJson = "{\"name\":\"lcgui\",\"age\":20,\"hobby\":[\"pingpong\",\"football\"],\"address\":{\"city\":\"nanjing\",\"street\":\"chaoyang\"}}";
        // 使用 JSONCompare 进行比较
        JSONCompareResult result = JSONCompare.compareJSON(expectedJson, actualJson, JSONCompareMode.STRICT_ORDER);
        // 检查比较结果
        if (result.failed()) { // 失败了就代表json有差异
//            System.out.println("JSON comparison failed: " + result.getMessage());
            for (FieldComparisonFailure fieldFailure : result.getFieldFailures()) {
                System.out.println("匹配失败路径: " + fieldFailure.getField());
                System.out.println("期望值: " + fieldFailure.getExpected());
                System.out.println("实际值: " + fieldFailure.getActual());
                System.out.println("----------------------------------------");
            }
        } else {
            System.out.println("JSON比较通过。。。");
        }

        // 最终的结果：
        //匹配失败路径: address.city
        //期望值: beijing
        //实际值: nanjing
        //----------------------------------------
        //匹配失败路径: age
        //期望值: 18
        //实际值: 20
        //----------------------------------------
        //匹配失败路径: hobby[0]
        //期望值: basketball
        //实际值: pingpong
        //----------------------------------------
    }




    //----------------
    // 这个依赖也不是100%好用，也会存在误判的情况
    //例如 如果吧JSONCompareTest()方法中的模式修改为 NON_EXTENSIBLE 或者 LENIENT，那么就检测不出basketball -> pingpong这个差异
    //----------------
}