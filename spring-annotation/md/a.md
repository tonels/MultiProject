Spring - @DependsOn Annotation Example
posted by Ramesh Fadatare on October 03, 2018

In this article, we will discuss how to use @DependsOn annotation in Spring Applications with an example.
The @DependsOn annotation can force Spring IoC container to initialize one or more beans before the bean which is annotated by @DependsOn annotation.
The @DependsOn annotation may be used on any class directly or indirectly annotated with @Component or on methods annotated with @Bean.
The following example shows how to use @DependsOn annotation in a spring application.
Spring @DependsOn annotation example
Let's create an example to demonstrates usage of use @DependsOn annotation in a spring application.
Tools and technologies used
Spring Framework - 5.1.0.RELEASE
JDK - 8 or later
Maven - 3.2+
IDE - Eclipse Mars/STS
Create a Simple Maven Project
Create a simple maven project using your favorite IDE and refer below section for packaging structure. If you are new to maven then read this article How to Create a Simple Maven Project.
Project Structure
Below diagram shows a project structure for your reference -

The pom.xml File
<project xmlns="http://maven.apache.org/POM/4.0.0"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>net.javaguides.spring</groupId>
    <artifactId>spring-dependson-annotation</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <name>spring-scope-example</name>
    <url>http://maven.apache.org</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
           <groupId>org.springframework</groupId>
           <artifactId>spring-context</artifactId>
           <version>5.1.0.RELEASE</version>
        </dependency>
    </dependencies>
    <build>
     <sourceDirectory>src/main/java</sourceDirectory>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.5.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
         </plugins>
    </build>
</project>
Create Spring Beans - FirstBean, SecondBean, and ThirdBean
FirstBean.java
package net.javaguides.spring.dependson;

import org.springframework.beans.factory.annotation.Autowired;

public class FirstBean {

    @Autowired
    private SecondBean secondBean;

    @Autowired
    private ThirdBean thirdBean;

    public FirstBean() {
        System.out.println("FirstBean Initialized via Constuctor");
    }

    public void populateBeans() {
        secondBean.display();
        thirdBean.display();
    }
}
SecondBean.java
package net.javaguides.spring.dependson;

public class SecondBean {
    public SecondBean() {
        System.out.println("SecondBean Initialized via Constuctor");
    }

    public void display() {
        System.out.println("SecondBean method called");
    }
}
ThirdBean.java
package net.javaguides.spring.dependson;

public class ThirdBean {
    public ThirdBean() {
        System.out.println("ThirdBean Initialized via Constuctor");
    }

    public void display() {
        System.out.println("ThirdBean method called");
    }
}
Java Based Configuration - AppConfig.java
Declare the above beans in java based configuration class.
AppConfig.java
package net.javaguides.spring.dependson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class AppConfig {

    @Bean("firstBean")
    @DependsOn(value = {
        "secondBean",
        "thirdBean"
    })
    public FirstBean firstBean() {
        return new FirstBean();
    }

    @Bean("secondBean")
    public SecondBean secondBean() {
        return new SecondBean();
    }

    @Bean("thirdBean")
    public ThirdBean thirdBean() {
        return new ThirdBean();
    }
}
Running Spring Application - Application.java
Let's create a main class and run an application.
package net.javaguides.spring.dependson;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        FirstBean bean = context.getBean(FirstBean.class);
        bean.populateBeans();
        context.close();
    }
}
Output
SecondBean Initialized via Constuctor
ThirdBean Initialized via Constuctor
FirstBean Initialized via Constuctor
SecondBean method called
ThirdBean method called
As you can see in the above output, the beans SecondBean and ThirdBean are initialized before bean FirstBean.
If you remove the @DependsOn annotation from firstBean() method of AppConfig class, the output (i.e. an order of initialization of beans) of the main class will be different on each run like:
FirstBean Initialized via Constuctor
SecondBean Initialized via Constuctor
ThirdBean Initialized via Constuctor
SecondBean method called
ThirdBean method called