package com.dingjh.example.hessianStudy.common;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.remoting.caucho.HessianClientInterceptor;
import com.dingjh.tools.string.StringUtils;
import com.caucho.hessian.client.HessianProxyFactory;
import com.dingjh.config.SystemConfig;

/**
 * 
 * @ClassName: HessianProxyFactoryBean
 * @Description: TODO()
 * 
 * @author dingjianhua
 * @date 2017年4月12日 上午11:57:15
 
 */
public class HessianProxyFactoryBean extends HessianClientInterceptor implements FactoryBean<Object> {
    private HessianProxyFactory proxyFactory = new HessianProxyFactory();

    private Object serviceProxy;

    @Override
    public void afterPropertiesSet() {
        logger.info("afterPropertiesSet :" + this.getServiceInterface());
        proxyFactory.setConnectTimeout(readTimeOut);
        proxyFactory.setReadTimeout(readTimeOut);
        proxyFactory.setOverloadEnabled(true);
        setProxyFactory(proxyFactory);
        super.afterPropertiesSet();
        this.serviceProxy = new ProxyFactory(getServiceInterface(), this).getProxy(getBeanClassLoader());
    }

    public Object getObject() {
        return this.serviceProxy;
    }

    public Class<?> getObjectType() {
        return getServiceInterface();
    }

    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setServiceUrl(String serviceUrl) {
        if (serviceUrl.indexOf("/") < 0) {
            throw new ExceptionInInitializerError("serviceUrl In violation of the constraint");
        }
        serviceUrl = prepre(serviceUrl);
        logger.info(serviceUrl);
        super.setServiceUrl(serviceUrl);
    }

    private String prepre(String serviceUrl) {
        String server = serviceUrl.substring(0, serviceUrl.indexOf("/"));
        String url = SystemConfig.getConfig(server);
        if (StringUtils.isNotBlank(url)) {
            if (url.endsWith("/")) {
                url = url.substring(0, (url.length() - 1));
            }
            serviceUrl = serviceUrl.replace(server, url);
        }
        if (serviceUrl.indexOf("http") < 0)
            serviceUrl = "http://" + serviceUrl;
        return serviceUrl;
    }

    @Override
    public void setReadTimeout(long timeout) {
        logger.info("time out :" + timeout);
        super.setReadTimeout(timeout);
    }

    private int readTimeOut = 10000;

    public int getReadTimeOut() {
        return readTimeOut;
    }

    public void setReadTimeOut(int readTimeOut) {
        this.readTimeOut = readTimeOut;
    }
}
