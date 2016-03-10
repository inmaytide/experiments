# framework
##架构探险--重零开始写Java Web框架书中的例子练习(框架部分)

###关于代理
1.静态代理（代码太多，自己写代码实现）<br/>
2.JDK动态代理（必须有接口， 和CGBLib动态代理相反创建比较快执行效率一般）<br/>
3.CGBLib动态代理（不一定要接口，任何类都可以。但创建代理比较慢，频繁创建对系统性能影响较大，执行效率比较好）<br/>

###关于Spring代理
1.前置增强（Before Advice）<br/>
2.后置增强(After Advice)<br/>
3.环绕增强(Around Advice 大概就是1+2混合在一起)<br/>
4.抛出增强（Throw Advice 抛出异常时执行）<br/>
5.引入增强（Introduction Advice--某类实现了A接口，但没有实现B接口，通过引入增加调用B接口的方法）<br/>
