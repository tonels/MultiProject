package tonels.extra.ssh;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ssh.JschRuntimeException;
import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import org.junit.Ignore;
import org.junit.Test;

import com.jcraft.jsch.Session;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;

import java.util.List;

/**
 * Jsch工具类单元测试
 * 
 * @author looly
 *
 */
public class JschUtilTest {
	
	@Test
	@Ignore
	public void bindPortTest() {
		//新建会话，此会话用于ssh连接到跳板机（堡垒机），此处为10.1.1.1:22
		Session session = JschUtil.getSession("192.168.1.74", 22, "root", "wondertek");
		// 将堡垒机保护的内网8080端口映射到localhost，我们就可以通过访问http://localhost:8080/访问内网服务了
//		JschUtil.bindPort(session, "172.20.12.123", 8080, 8080);
		System.out.println(session.getUserName());
		System.out.println(session.getPort());
	}
	
	@Test
	@Ignore
	public void sftpTest() {
		Session session = JschUtil.getSession("192.168.1.74", 22, "root", "wondertek");
		Sftp sftp = JschUtil.createSftp(session);
		List<String> ls = sftp.ls("/opt");
		ls.forEach(System.out::println);
	}

	@Test
	@Ignore
	public void sftpTest2() {
		Session session = JschUtil.getSession("192.168.1.74", 22, "root", "wondertek");
		Sftp sftp = JschUtil.createSftp(session);
		boolean b = sftp.delDir("/opt/test/test");
		System.out.println(b);
	}

	@Test
	@Ignore
	public void sftpTest3() {
		Session session = JschUtil.getSession("192.168.1.74", 22, "root", "wondertek");
		Sftp sftp = JschUtil.createSftp(session);
		boolean b = sftp.mkdir("/opt/test/test2");
		System.out.println(b);
	}

	@Test
	@Ignore
	public void sftpTest4() {
		Session session = JschUtil.getSession("192.168.1.74", 22, "root", "wondertek");
		Sftp sftp = JschUtil.createSftp(session);
		Sftp put = sftp.put("D:\\tmp\\a.txt", "/opt/test/as.txt");
	}

	@Test
	@Ignore
	public void sftpTest5() {
		Session session = JschUtil.getSession("192.168.1.74", 22, "root", "wondertek");
		Sftp sftp = JschUtil.createSftp(session);
sftp.download("/opt/test/as.txt", FileUtil.file("D:\\tmp\\b.txt"));
	}




	
	@Test
	@Ignore
	public void reconnectIfTimeoutTest() throws InterruptedException {
		Session session = JschUtil.getSession("192.168.1.74", 22,"root","wondertek");
		Sftp sftp = JschUtil.createSftp(session);

		Console.log("打印pwd: " + sftp.pwd());
		Console.log("cd / : " + sftp.cd("/"));
		Console.log("休眠一段时间，查看是否超时");
		Thread.sleep(30 * 1000);

		try{
			// 当连接超时时，isConnected()仍然返回true，pwd命令也能正常返回，因此，利用发送cd命令的返回结果，来判断是否连接超时
			Console.log("isConnected " + sftp.getClient().isConnected());
			Console.log("打印pwd: " + sftp.pwd());
			Console.log("cd / : " + sftp.cd("/"));
		}catch (JschRuntimeException e) {
			e.printStackTrace();
		}

		Console.log("调用reconnectIfTimeout方法，判断是否超时并重连");
		sftp.reconnectIfTimeout();

		Console.log("打印pwd: " + sftp.pwd());

		IoUtil.close(sftp);
	}
}
