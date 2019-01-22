package com.springboot.templet.test.redis;

import com.springboot.templet.test.redis.bean.User;
import com.springboot.templet.test.redis.dao.RedisDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

	public static Logger logger= LoggerFactory.getLogger(SpringBootRedisApplicationTests.class);
	@Test
	public void contextLoads() {
	}

	@Autowired
	RedisDao redisDao;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private JedisPool jedisPool;

	@Test
	public void testRedis(){
		redisDao.setKey("name","test");
		redisDao.setKey("age","11");
		logger.info(redisDao.getValue("name"));
		logger.info(redisDao.getValue("age"));
	}

	@Test
	public void saveObjectTest() throws Exception {

		// 保存对象
		User user = new User("超人", 20);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蝙蝠侠", 30);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User("蜘蛛侠", 40);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		Assert.assertEquals(20, redisTemplate.opsForValue().get("超人"));

	}

	@Test
	public void saveStringTest() throws Exception {

		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

	}

	@Test
	public void jedisTest() throws Exception {

		Long i = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			//  从左往右
			jedis.lpush("nameList", "2");
			jedis.lpush("nameList", "1");
			jedis.lpush("nameList", "3");
			// 查询出nameList集合中的素有元素
			List<String> nameList = jedis.lrange("nameList", 0, -1);
			for (String name : nameList) {
				System.out.println(name);
			}
			String rpop = jedis.rpop("nameList1");
			System.out.println("右侧弹出:"+rpop);
			// 查询出nameList集合中的素有元素
			List<String> name2List = jedis.lrange("nameList", 0, -1);
			for (String name : name2List) {
				System.out.println(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
		}

	}
}
