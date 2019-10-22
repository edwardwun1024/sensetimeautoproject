package httptool;

import exception.HttpProcessException;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.util.EntityUtils;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import utility.Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClentTool{

    protected final static Logger logger = (Logger) LoggerFactory.getLogger(HttpClentTool.class);

    public static String send(HttpClient client, String url, HttpMethods httpMethod, Map<String,String> parasMap,
                              Header[] headers, String encoding) throws HttpProcessException {
        String body = "";
        try {
            //创建请求对象
            HttpRequestBase request = getRequest(url, httpMethod);

            //设置header信息
            request.setHeaders(headers);

            //判断是否支持设置entity(仅HttpPost、HttpPut、HttpPatch支持)
            if(HttpEntityEnclosingRequestBase.class.isAssignableFrom(request.getClass())){
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();

                //检测url中是否存在参数
                url = Utils.checkHasParas(url, nvps);

                //装填参数
                Utils.map2List(nvps, parasMap);

                //设置参数到请求对象中
                ((HttpEntityEnclosingRequestBase)request).setEntity(new UrlEncodedFormEntity(nvps, encoding));

                logger.debug("请求地址："+url);
                if(nvps.size()>0){
                    logger.debug("请求参数："+nvps.toString());
                }
            }else{
                int idx = url.indexOf("?");
                logger.debug("请求地址："+url.substring(0, (idx>0 ? idx-1:url.length()-1)));
                if(idx>0){
                    logger.debug("请求参数："+url.substring(idx+1));
                }
            }

            //调用发送请求
            body = execute(client, request, url, encoding);

        } catch (UnsupportedEncodingException e) {
            throw new HttpProcessException(e);
        }
        return body;
    }


    private static HttpRequestBase getRequest(String url, HttpMethods method) {
        HttpRequestBase request = null;
        switch (method.getCode()) {
            case 0:// HttpGet
                request = new HttpGet(url);
                break;
            case 1:// HttpPost
                request = new HttpPost(url);
                break;
            case 2:// HttpHead
                request = new HttpHead(url);
                break;
            case 3:// HttpPut
                request = new HttpPut(url);
                break;
            case 4:// HttpDelete
                request = new HttpDelete(url);
                break;
            case 5:// HttpTrace
                request = new HttpTrace(url);
                break;
            case 6:// HttpPatch
                request = new HttpPatch(url);
                break;
            case 7:// HttpOptions
                request = new HttpOptions(url);
                break;
            default:
                request = new HttpPost(url);
                break;
        }
        return request;
    }

    private static String execute(HttpClient client, HttpRequestBase request,String url, String encoding) throws HttpProcessException {
        String body = "";
        HttpResponse response =null;
        try {

            //执行请求操作，并拿到结果（同步阻塞）
            response = client.execute(request);

            //获取结果实体
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = EntityUtils.toString(entity, encoding);
                logger.debug(body);
            }
            EntityUtils.consume(entity);
        } catch (ParseException | IOException e) {
            throw new HttpProcessException(e);
        } finally {
            close(response);
        }

        return body;
    }


    private static void close(HttpResponse resp) {
        try {
            if(resp == null) return;
            //如果CloseableHttpResponse 是resp的父类，则支持关闭
            if(CloseableHttpResponse.class.isAssignableFrom(resp.getClass())){
                ((CloseableHttpResponse)resp).close();
            }
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }

}
