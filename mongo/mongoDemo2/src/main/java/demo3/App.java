package demo3;

import demo3.config.AppConfig;
import demo3.hosting.bo.HostingBo;
import demo3.seq.exception.SequenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App {

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		HostingBo hostingBo = (HostingBo) ctx.getBean("hostingBoImpl");
		try {
			hostingBo.save("cloud.google.com");
			hostingBo.save("heroku.com");
			hostingBo.save("cloudbees.com");
		} catch (SequenceException e) {
			System.out.println(e.getErrMsg());
		}
	}
}