package tonels.extra.emoji;

import cn.hutool.extra.emoji.EmojiUtil;
import org.junit.Assert;
import org.junit.Test;

public class EmojiUtilTest {
	
	@Test
	public void toUnicodeTest() {
		String emoji = EmojiUtil.toUnicode(":smile:");
		System.out.println(emoji);
		Assert.assertEquals("😄", emoji);
	}
	
	@Test
	public void toAliasTest() {
		String alias = EmojiUtil.toAlias("😄");
		Assert.assertEquals(":smile:", alias);
	}
}
