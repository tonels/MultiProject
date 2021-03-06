package tonels.extra.tokenizer;

import java.util.Iterator;

import cn.hutool.extra.tokenizer.Result;
import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import cn.hutool.extra.tokenizer.Word;
import org.junit.Assert;
import org.junit.Test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.tokenizer.engine.analysis.SmartcnEngine;
import cn.hutool.extra.tokenizer.engine.hanlp.HanLPEngine;
import cn.hutool.extra.tokenizer.engine.ikanalyzer.IKAnalyzerEngine;
import cn.hutool.extra.tokenizer.engine.jcseg.JcsegEngine;
import cn.hutool.extra.tokenizer.engine.jieba.JiebaEngine;
import cn.hutool.extra.tokenizer.engine.mmseg.MmsegEngine;
import cn.hutool.extra.tokenizer.engine.word.WordEngine;

/**
 * 模板引擎单元测试
 * 
 * @author looly
 *
 */
public class TokenizerUtilTest {
	
	String text = "这两个方法的区别在于返回值";

	@Test
	public void createEngineTest() {
		// 默认分词引擎，此处为Ansj
		TokenizerEngine engine = TokenizerUtil.createEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这 两个 方法 的 区别 在于 返回 值", resultStr);
		System.out.println(resultStr);

	}
	
	@Test
	public void hanlpTest() {
		TokenizerEngine engine = new HanLPEngine();
		Result result = engine.parse(text);
		result.forEach(System.out::println);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");

		Assert.assertEquals("这 两 个 方法 的 区别 在于 返回 值", resultStr);
	}
	
	@Test
	public void ikAnalyzerTest() {
		TokenizerEngine engine = new IKAnalyzerEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		System.out.println(resultStr);
		Assert.assertEquals("这两个 方法 的 区别 在于 返回值", resultStr);
	}
	
	@Test
	public void jcsegTest() {
		TokenizerEngine engine = new JcsegEngine();
		Result result = engine.parse(text);
		checkResult(result);
	}
	
	@Test
	public void jiebaTest() {
		TokenizerEngine engine = new JiebaEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这 两个 方法 的 区别 在于 返回值", resultStr);
	}
	
	@Test
	public void mmsegTest() {
		TokenizerEngine engine = new MmsegEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");

		Assert.assertEquals("这 两个 方法 的 区别 在于 返回 值", resultStr);
	}
	
	@Test
	public void smartcnTest() {
		TokenizerEngine engine = new SmartcnEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这 两 个 方法 的 区别 在于 返回 值", resultStr);
	}
	
	@Test
	public void wordTest() {
		TokenizerEngine engine = new WordEngine();
		Result result = engine.parse(text);
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这两个 方法 的 区别 在于 返回值", resultStr);
	}
	
	private void checkResult(Result result) {
		String resultStr = CollUtil.join((Iterator<Word>)result, " ");
		Assert.assertEquals("这 两个 方法 的 区别 在于 返回 值", resultStr);
	}
}
