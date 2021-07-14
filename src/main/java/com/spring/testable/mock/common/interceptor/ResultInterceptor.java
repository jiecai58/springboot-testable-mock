package com.spring.testable.mock.common.interceptor;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.spring.testable.mock.common.interceptor.annotation.EncryptDecryptClass;
import com.spring.testable.mock.common.interceptor.encrypt.IEncryptDecrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

/**
 * Mybatis 返回值加解密拦截器
 *
 * @author fraser
 * @date 2019-05-15 14:40
 */
@Intercepts({
		@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
@ConditionalOnProperty(value = "domain.decrypt", havingValue = "true")
@Component
@Slf4j
public class ResultInterceptor implements Interceptor {

	@Resource
	private IEncryptDecrypt encryptDecrypt;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("拦截器ResultInterceptor");
		Object result = invocation.proceed();
		if (Objects.isNull(result)) {
			return null;
		}

		if (result instanceof ArrayList) {
			ArrayList resultList = (ArrayList) result;
			if (CollectionUtils.isNotEmpty(resultList) && needToDecrypt(resultList.get(0))) {
				for (int i = 0; i < resultList.size(); i++) {
					encryptDecrypt.decrypt(resultList.get(i));
				}
			}
		} else {
			if (needToDecrypt(result)) {
				encryptDecrypt.decrypt(result);
			}
		}
		return result;
	}

	public boolean needToDecrypt(Object object) {
		Class<?> objectClass = object.getClass();
		EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass, EncryptDecryptClass.class);
		if (Objects.nonNull(encryptDecryptClass)) {
			return true;
		}
		return false;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}
}

