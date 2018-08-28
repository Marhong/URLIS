import java.awt.SystemTray;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.hwadee.dao.IQuestionDao;
import com.hwadee.model.OptionEntity;
import com.hwadee.model.QuestionEntity;
import com.hwadee.util.MyBatiesUtil;

/**
 * 
 */

/**
 * 项目名称: URLIS
 * 类名称: QuestionTest
 * 创建人: wangbin
 * 创建时间: 2018年8月13日 上午10:59:41
 */
public class QuestionTest {
	
	/**
	 * @Title: TestInsert
	 * @Description: 测试插入
	 * @Time: 2018年8月13日 上午11:25:52
	 * @author: wangbin
	 */
	@Test
	public void TestInsert() {
		QuestionEntity quEntity = new QuestionEntity();
		quEntity.setQu_id(250);
		quEntity.setQu_type("常识");
		quEntity.setQu_content("人有几条腿");
		quEntity.setQu_option("a 两条; b 三条");
		quEntity.setQu_answer("a");
		quEntity.setQu_score(5);
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		questionDao.insertQuestion(quEntity);
		session.commit();
		MyBatiesUtil.closeSqlSession();
	}
	
	@Test
	public void TestDelete() {
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		int qu_id = 250;
		questionDao.deleteQuestion(qu_id);
		session.commit();
		MyBatiesUtil.closeSqlSession();
	}
	@Test
	public void TestUpdate() {
		QuestionEntity quEntity = new QuestionEntity();
		quEntity.setQu_id(1);
		quEntity.setQu_type("测试更新类");
		quEntity.setQu_content("人有几条腿");
		quEntity.setQu_option("a 两条; b 三条");
		quEntity.setQu_answer("a");
		quEntity.setQu_score(5);
		SqlSession session = MyBatiesUtil.getSqlSession();
		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
		questionDao.updateQuestion(quEntity);
		session.commit();
		MyBatiesUtil.closeSqlSession();
	}
//	@Test
//	public void TestGetSingleObject() {
//		SqlSession session = MyBatiesUtil.getSqlSession();
//		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
//		String qu_content = "人有几条腿";
//		QuestionEntity entity = questionDao.getQuestionByContent(qu_content);
//		System.out.println(entity);
//		MyBatiesUtil.closeSqlSession();
//	}
	
//	@Test
//	public void TestGetObjectList() {
//		SqlSession session = MyBatiesUtil.getSqlSession();
//		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
//		String qu_type = "常识";
//		List<QuestionEntity> list = questionDao.queryQuestion(qu_type);
//		System.out.println(list);
//		MyBatiesUtil.closeSqlSession();
//	}
//	@Test
//	public void TestOneToMany() {
//		SqlSession session = MyBatiesUtil.getSqlSession();
//		IQuestionDao questionDao = session.getMapper(IQuestionDao.class);
//		String qu_type = "常识";
//		List<OptionEntity> list = questionDao.queryListOneToMany();
//		System.out.println(list);
//		MyBatiesUtil.closeSqlSession();
//	}
}
