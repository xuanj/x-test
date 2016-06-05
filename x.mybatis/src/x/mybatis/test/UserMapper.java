package x.mybatis.test;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
	@Select("SELECT * FROM Merchant WHERE id = #{id}")
	Merchant selectUser(Long id);
}