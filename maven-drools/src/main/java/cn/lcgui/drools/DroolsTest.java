package cn.lcgui.drools;

import cn.lcgui.drools.entity.MileageOrder;
import cn.lcgui.drools.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Drools测试类
 */
public class DroolsTest {

    /**
     * 测试图书订单规则
     */
    @Test
    public void orderRuleTest(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互
        KieSession kieSession = kieClasspathContainer.newKieSession();
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        Order order = new Order();
        order.setOriginalPrice(210D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
    }

    /**
     * 测试里程规则1
     */
    @Test
    public void mileageRuleTest(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互, 指定Session
        KieSession kieSession = kieClasspathContainer.newKieSession("mileage-rule");
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        MileageOrder order = new MileageOrder();
        order.setMileage(1000);
        order.setOriginalPrice(100D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
    }

    /**
     * 测试里程规则2和1多个规则
     */
    @Test
    public void mileageRuleTest2(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互, 指定Session
        KieSession kieSession = kieClasspathContainer.newKieSession("mileage-rule");
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        MileageOrder order = new MileageOrder();
        order.setMileage(200);
        order.setOriginalPrice(100D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
        /**
         * 经过测试发现输出结果为：
         * 成功匹配到里程规则二规则二：里程在100~500公里之间，9折
         * 成功匹配到规则一：里程小于1000公里，无折扣
         * 优惠前原始价格：100.0，优惠后价格：100.0
         *
         * 分明匹配了 成功匹配到里程规则二规则二：里程在100~500公里之间，9折  但是结果还是原来的价格 100.0
         *
         * 原因：
         * 在 Drools 中，规则的执行顺序默认由 规则声明顺序 和 salience（优先级） 决定。
         * 如果两个规则都触发，后执行的规则会覆盖前一个规则对字段的修改。
         * 规则1的规则一（mileage_discount_1）可能在 规则2的规则二（mileage_discount_2_2）之后执行，导致 realPrice 被重置为原价。
         */
    }

    /**
     * 测试Drools属性：salience属性
     * salience属性用于设置规则的优先级，默认值为0，数字越大优先级越高。
     */
    @Test
    public void mileageRuleTest3(){
        // 这里的代码和测试2相同
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互, 指定Session
        KieSession kieSession = kieClasspathContainer.newKieSession("mileage-rule");
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        MileageOrder order = new MileageOrder();
        order.setMileage(200);
        order.setOriginalPrice(100D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
        /**
         * 结果：
         * 成功匹配到规则一：里程小于1000公里，无折扣
         * 成功匹配到里程规则二规则二：里程在100~500公里之间，9折
         * 优惠前原始价格：100.0，优惠后价格：90.0
         */
    }

    /**
     * 测试Drools内置方法：update方法
     * update方法的作用是更新工作内存中的数据，并让相关的规则重新匹配。(注意不要进入死循环)
     */
    @Test
    public void mileageRuleTest4(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互, 指定Session
        KieSession kieSession = kieClasspathContainer.newKieSession("mileage-rule-update");
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        MileageOrder order = new MileageOrder();
        order.setMileage(200);
        order.setOriginalPrice(100D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
        /**
         * 结果：
         * 成功匹配到里程规则二规则二：里程在100~500公里之间，9折
         * 成功匹配到里程规则二规则三：里程大于等于500公里，8折
         * 优惠前原始价格：100.0，优惠后价格：80.0
         */
    }

    /**
     * 测试Drools内置方法：insert方法
     * insert方法作用是向工作内存中插入数据，并让相关的规则重新匹配。
     */
    @Test
    public void mileageRuleTest5(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互, 指定Session
        KieSession kieSession = kieClasspathContainer.newKieSession("mileage-rule-insert");
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        MileageOrder order = new MileageOrder();
        order.setMileage(200);
        order.setOriginalPrice(100D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
        /**
         * 结果：
         * 成功匹配到里程规则二规则二：里程在100~500公里之间，9折
         * 成功匹配到里程规则二规则一：里程小于100公里，无折扣
         * 优惠前原始价格：100.0，优惠后价格：90.0
         */
    }

    /**
     * 测试Drools内置方法：retract方法
     * retract方法的作用是删除工作内存中的数据，并让相关的规则重新匹配。
     */
    @Test
    public void mileageRuleTest6(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互, 指定Session
        KieSession kieSession = kieClasspathContainer.newKieSession("mileage-rule-retract");
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        MileageOrder order = new MileageOrder();
        order.setMileage(200);
        order.setOriginalPrice(100D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
        /**
         * 结果：
         * 成功匹配到里程规则二规则二：里程在100~500公里之间，9折
         * 优惠前原始价格：100.0，优惠后价格：null
         */
    }

    /**
     * 测试Drools属性：no-loop属性
     * no-loop属性用于防止死循环，当规则通过update之类的函数修改了Fact对象时，可能使当前规则再次被激活从而导致死循环。取值类型为Boolean，默认值为false
     */
    @Test
    public void mileageRuleTest7(){
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        //会话对象，用于和规则引擎交互, 指定Session
        KieSession kieSession = kieClasspathContainer.newKieSession("mileage-rule-noloop");
        //构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格
        MileageOrder order = new MileageOrder();
        order.setMileage(200);
        order.setOriginalPrice(100D);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
        /**
         * 结果：
         * 成功匹配到里程规则二规则二：里程在100~500公里之间，9折
         * 优惠前原始价格：100.0，优惠后价格：90.0
         */
    }
}
