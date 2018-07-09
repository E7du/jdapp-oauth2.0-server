/**
 * 
 */
package cn.zhucongqi.oauth2.services;

import cn.zhucongqi.oauth2.base.services.OAuthApi;
import cn.zhucongqi.oauth2.clientcredentials.PasswordClientCredentials;
import cn.zhucongqi.oauth2.consts.OAuthRequestConsts;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.request.OAuthHttpServletRequest;
import cn.zhucongqi.oauth2.request.OAuthRequest;
import cn.zhucongqi.oauth2.response.OAuthErrResponse;

/**
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 */
public class OAuthService implements OAuthApi {
	
	private OAuthHttpServletRequest request = null;
	
	@Override
	public void initRequest(OAuthHttpServletRequest request) {
		this.request = request;
	}

	private Object respClient(int requestType) {
		Object o = null;
		OAuthRequest request = null;
		try {
			switch (requestType) {
			case OAuthRequestConsts.AUTHORIZATION_REQUEST: {
				request = OAuthRequest.authorizatonRequest(this.request, new PasswordClientCredentials());
			}
				break;

			case OAuthRequestConsts.ACCESS_TOKEN_REQUEST: {
				request = OAuthRequest.accessTokenRequest(this.request, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.CLIENT_CREDENTIAL_REQUEST: {
				request = OAuthRequest.clientCredentialRequest(this.request, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.IMPLICIT_REQUEST: {
				request = OAuthRequest.implicitRequest(this.request, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.PASSOWRD_CREDENTIAL_REQUEST: {
				request = OAuthRequest.passwordCredentialRequest(this.request, new PasswordClientCredentials());
			}
				break;
			case OAuthRequestConsts.REFRESH_TOKEN_REQUEST: {
				request = OAuthRequest.refreshTokenRequest(this.request, new PasswordClientCredentials());
			}
				break;
			}

			o = request.validate();//push to o: o = request.validate();
			
		} catch (OAuthProblemException e) {
			e.printStackTrace();
			OAuthErrResponse error = new OAuthErrResponse(request.getValidator(), e);
			o = error.parameters();
		}
		return o;
	}
 	
	@Override
	public Object authrize() {
		return this.respClient(OAuthRequestConsts.AUTHORIZATION_REQUEST);
	}
	
	@Override
	public Object authrizeCode() {
		return null;
	}

	@Override
	public Object accessToken() {
		return this.respClient(OAuthRequestConsts.ACCESS_TOKEN_REQUEST);
	}
	
	@Override
	public Object secureAccessToken() {
		return this.respClient(OAuthRequestConsts.PASSOWRD_CREDENTIAL_REQUEST);
	}

	@Override
	public Object refreshToken() {
		return this.respClient(OAuthRequestConsts.REFRESH_TOKEN_REQUEST);
	}

}
