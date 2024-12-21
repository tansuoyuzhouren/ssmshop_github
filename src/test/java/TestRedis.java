
// Redis 测试
// Spring 整合JUnit单元测试

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class TestRedis {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getRedisTemplate() {
        String username = "admin";
        String password = "123456";

        if ("admin".equals(username) && "123456".equals(password)){

            String sessionId = "session:" + username;
            System.out.println(sessionId);
            //将会话存储到Redis 设置过期时间为30分中
            redisTemplate.opsForValue().set(sessionId,username,30, TimeUnit.MINUTES);

        }
    }
}
