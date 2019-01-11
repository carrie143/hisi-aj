package com.hisi.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.Assert;

public class BeanCopyUtil {

	/* 缓存 */
	private static Map<Class, Map<Class, BeanCopier>> beanCopierCache = new HashMap<>();

	public static void copyProperties(Object source, Object target)
			throws BeansException {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = BeanUtils
				.getPropertyDescriptors(actualEditable);
		for (PropertyDescriptor targetPd : targetPds) {
			if (targetPd.getWriteMethod() != null) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(
						source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method readMethod = sourcePd.getReadMethod();
						if (!Modifier.isPublic(readMethod.getDeclaringClass()
								.getModifiers())) {
							readMethod.setAccessible(true);
						}
						Object value = readMethod.invoke(source);
						// 这里判断以下value是否为空 当然这里也能进行一些特殊要求的处理 例如绑定时格式转换等等
						if (value != null) {
							Method writeMethod = targetPd.getWriteMethod();
							if (!Modifier.isPublic(writeMethod
									.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						}
					} catch (Throwable ex) {
						throw new FatalBeanException(
								"Could not copy properties from source to target",
								ex);
					}
				}
			}
		}
	}

	/**
	 * 
	 * 属性拷贝
	 * 
	 * @param from
	 * @param to
	 * @param <T>
	 * @return
	 */
	public static <T> T copyTo(Object from, Object to) {

		return copyTo(from, to, null);
	}

	public static <T> T copyTo(Object from, Object to, Converter converter) {
		if (from == null || to == null) {
			return null;
		}

		Class fromClass = from.getClass();
		Class<T> toClass = (Class<T>) to.getClass();

		BeanCopier beanCopier = getBeanCopier(fromClass, toClass);

		beanCopier.copy(from, to, converter);

		return (T) to;
	}

	private static BeanCopier getBeanCopier(Class fromClass, Class toClass) {
		Map<Class, BeanCopier> beanCopierMap = beanCopierCache.get(fromClass);

		if (beanCopierMap == null) {
			synchronized (BeanCopyUtil.class) {
				beanCopierMap = beanCopierCache.computeIfAbsent(fromClass,
						k -> new HashMap<>());
			}
		}

		BeanCopier beanCopier = beanCopierMap.get(toClass);

		if (beanCopier == null) {
			synchronized (BeanCopyUtil.class) {
				beanCopier = beanCopierMap.computeIfAbsent(toClass,
						c -> BeanCopier.create(fromClass, c, false));
			}
		}

		return beanCopier;
	}
}
