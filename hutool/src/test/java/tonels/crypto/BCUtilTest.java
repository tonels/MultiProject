package tonels.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Assert;
import org.junit.Test;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

public class BCUtilTest {
	
	@Test
	public void readPrivateKeyTest() {
		PrivateKey privateKey = BCUtil.readPrivateKey(ResourceUtil.getStream("crypto/test_private_key.pem"));
		System.out.println(privateKey);
	}
	
	@Test
	public void readPublicKeyTest() {
		PublicKey publicKey = BCUtil.readPublicKey(ResourceUtil.getStream("crypto/test_public_key.csr"));
		System.out.println(publicKey);
	}
	
	@Test
	public void validateKey() {
		PrivateKey privateKey = BCUtil.readPrivateKey(ResourceUtil.getStream("crypto/test_private_key.pem"));
		PublicKey publicKey = BCUtil.readPublicKey(ResourceUtil.getStream("crypto/test_public_key.csr"));
		
		RSA rsa = new RSA(privateKey, publicKey);
		String str = "你好，Hutool";//测试字符串
		
		String encryptStr = rsa.encryptBase64(str, KeyType.PublicKey);
		String decryptStr = rsa.decryptStr(encryptStr, KeyType.PrivateKey);

		System.out.println(encryptStr);
		System.out.println(decryptStr);
		Assert.assertEquals(str, decryptStr);
	}
}
