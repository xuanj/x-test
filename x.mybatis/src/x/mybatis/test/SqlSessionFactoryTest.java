package x.mybatis.test;
import javax.sql.DataSource;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class SqlSessionFactoryTest {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = initSqlSessionFactory();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Merchant user = (Merchant) session.selectOne("UserMapper.selectUser", 14L);
			System.out.println(user.getContacts());
			System.out.println(user.getAddress());
		} finally {
			session.close();
		}
	}

	private static SqlSessionFactory initSqlSessionFactory() {
		DataSource dataSource = new PooledDataSource(
				"oracle.jdbc.driver.OracleDriver",
				"jdbc:oracle:thin:@192.168.50.34:1521:OBANKS", "EA_LOGIC",
				"LOGIC_EA");
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development",
				transactionFactory, dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(UserMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(configuration);

		return sqlSessionFactory;
	}
}