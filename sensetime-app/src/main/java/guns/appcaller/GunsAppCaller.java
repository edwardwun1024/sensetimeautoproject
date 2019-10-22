package guns.appcaller;

import guns.bean.MrgLoginRequestBean;
import httptool.HttpMethods;


public class GunsAppCaller{

    public GunsAppCaller(){}

    private static String POST_MRG_LOGIN_PATH = "/mrg/login";


    public String postMrgLogin(MrgLoginRequestBean mrgLoginRequestBean){
        return doPostMrgLogin(POST_MRG_LOGIN_PATH,mrgLoginRequestBean, HttpMethods.POST);
    }
    private String doPostMrgLogin(String path, MrgLoginRequestBean mrgLoginRequestBean, HttpMethods post) {
        return mrgLoginRequestBean.toString();
    }


}
