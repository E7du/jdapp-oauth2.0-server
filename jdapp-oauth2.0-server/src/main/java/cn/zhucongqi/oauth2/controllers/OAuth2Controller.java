/**
 * 注意： view 对应的 func 的命名，为 xxv，如 loginv，对应的处理为 login
 */
package cn.zhucongqi.oauth2.controllers;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.core.ControllerExt;
import com.jfinal.ext.interceptor.OnExceptionInterceptorExt;
import com.jfinal.ext.plugin.spring.Inject;
import com.jfinal.ext.plugin.spring.IocInterceptor;

import cn.zhucongqi.oauth2.base.services.OAuthApi;
import cn.zhucongqi.oauth2.consts.ActionUrls;
import cn.zhucongqi.oauth2.kit.OAuthRequestKit;
import cn.zhucongqi.oauth2.request.OAuthHttpServletRequest;

/**
 * 
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
@Before({OnExceptionInterceptorExt.class, IocInterceptor.class})
public class OAuth2Controller extends ControllerExt {

	//auto init the service instance
	@Inject.BY_NAME
	private OAuthApi oauthService;
	
	public OAuth2Controller() {
	}

	@Override
	protected void onInit() {
	}

	@ActionKey(ActionUrls.AUTHORIZE_URL)
	public void onAuthorize() {
		this.oauthService.authrize();
	}

	@ActionKey(ActionUrls.AUTHORIZE_CODE_URL)
	public void onAuthorizeCode() {
		this.oauthService.authrizeCode();
	}
	
	@ActionKey(ActionUrls.SECURE_ACCESS_TOKEN_URL)
	public void onAccessTokenSecure() {

		OAuthHttpServletRequest req = new OAuthHttpServletRequest();
		OAuthRequestKit.cp(this.getRequest(), req);
		this.oauthService.initRequest(req);
		Object ret = this.oauthService.secureAccessToken();
		this.renderJson(ret);
	}

	@ActionKey(ActionUrls.ACCESS_TOKEN_URL)
	public void onAcessToken() {
		this.oauthService.accessToken();
	}
	
	@ActionKey(ActionUrls.REFRESH_TOKEN_URL)
	public void onRefreshToken() {	
		this.oauthService.refreshToken();
	}

	@Override
	public void onExceptionError(Exception e) {
		e.printStackTrace();
	}
	
}
