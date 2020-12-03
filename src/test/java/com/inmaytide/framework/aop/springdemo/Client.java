/**
 * 
 */
package com.inmaytide.framework.aop.springdemo;

//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.inmaytide.framework.aop.Greeting;
//import com.inmaytide.framework.aop.GreetingImpl;

/**
 * @author Administrator
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ProxyFactory proxyFactory = new ProxyFactory();
//		proxyFactory.setTarget(new GreetingImpl());
//		String name = "Jack";
//   	前置增强和后置增强
//		proxyFactory.addAdvice(new GreetingAfterAdvice());
//		proxyFactory.addAdvice(new GreetingBeforeAdvice());
		
		//前置增强和后置增强放在同意个类里面
//		proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());
//		name = "Rose";
		
		//环绕增强
//		proxyFactory.addAdvice(new GreetingAroundAdvice());
//		name = "Chris";
		
//		@SuppressWarnings("resource")
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//		
////		Arrays.stream(context.getBeanDefinitionNames()).forEach(s -> System.out.println(s));
//		GreetingImpl inst = (GreetingImpl) context.getBean("greetingImpl");
//		inst.say("Moss");
//		
//		inst.goodMorning("Moss");
//		inst.goodNight("Moss");
		
		
//		Apology apology = (Apology) inst;
//		apology.saySorry("Mars");
	}

}
