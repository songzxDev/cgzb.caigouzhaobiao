package cn.szx.cgzb.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;


public class MyChangeModelUtil {
	/**
	 * @param <T,S> 声明两个泛型
	 * @param sources
	 *          要被复制的所有java目标对象的的集合(List<S> sources)
	 * @param c
	 *          用来创建泛型T代表的类的对象 "Class<T> c"是为了声明泛型T的具体类型
	 * @return List<T> 是指将sources集合中的每个java对象遍历出来，分别将每个<S>对象中的属性复制到<T>对象中，在将已复制完属性的<T>对象装入集合List<T>中
	 * 
	 * @throws Exception
	 * 
	 */
	public static <T, S> List<T> changeCollectionModel(List<S> sources, Class<T> c) throws Exception {
		List<T> targets = new ArrayList<T>();
		T t;
		if (sources != null && sources.size() > 0) {
			for (S s : sources) {
				// 创建泛型对象
				// c.newInstance()创建对象
				t = c.newInstance();
				BeanUtils.copyProperties(s, t);
				targets.add(t);
			}
			return targets;
		} else {
			return null;
		}
	}
	
	public static <T, S> T changeModel(S s, Class<T> c) throws Exception {
		T t = c.newInstance();
		BeanUtils.copyProperties(s, t);
		return t;
	}
}