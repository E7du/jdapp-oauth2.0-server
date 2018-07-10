package cn.zhucongqi.oauth2.testings;
import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Test;

import cn.zhucongqi.server.core.Launcher;

class JdappOAuthProvider {

	@Test
	void test() throws InterruptedException {
		//-D${LOG4J_CFG} 
		PropertyConfigurator.configure("src/main/resources/conf/log4j.properties");
		
		Launcher.laucher(new String[] {}) ;
	}

}
