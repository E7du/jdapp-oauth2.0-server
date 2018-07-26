/*
 * Copyright 2018 Jobsz (zcq@zhucongqi.cn)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
*/
package cn.zhucongqi.oauth2.cfg;

import java.util.Properties;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.config.JFinalConfigExt;
import com.jfinal.ext.plugin.spring.SpringPlugin;
import com.jfinal.template.Engine;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class Config extends JFinalConfigExt {
	
	public void afterJFinalStarted(){
	}

	@Override
	public void configMoreConstants(Constants me) {
		
	}

	@Override
	public void configMoreRoutes(Routes me) {

	}

	@Override
	public void configMorePlugins(Plugins me) {
		SpringPlugin sp = new SpringPlugin("classpath:conf/jdappoauth2-consumer.xml");
		me.add(sp);
	}

	@Override
	public void configMoreInterceptors(Interceptors me) {

	}

	@Override
	public void configMoreHandlers(Handlers me) {
		
	}

	@Override
	public void configEngineMore(Engine me) {
		
	}

	@Override
	public Properties getLazyProp() {
		Properties prop = new Properties();
		prop.setProperty("app.dev", "true");
		prop.setProperty("app.post", "true");
		prop.setProperty("app.name", "jdapp-oauth2-server");//same: JFinalConfigExt.APP_NAME = "jdapp-oauth2-server";
		return prop;
	}
}
