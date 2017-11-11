package cn.szx.cgzb.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;


public class MyChangeModelUtil {
	/**
	 * @param <T,S> ������������
	 * @param sources
	 *          Ҫ�����Ƶ�����javaĿ�����ĵļ���(List<S> sources)
	 * @param c
	 *          ������������T�������Ķ��� "Class<T> c"��Ϊ����������T�ľ�������
	 * @return List<T> ��ָ��sources�����е�ÿ��java��������������ֱ�ÿ��<S>�����е����Ը��Ƶ�<T>�����У��ڽ��Ѹ��������Ե�<T>����װ�뼯��List<T>��
	 * 
	 * @throws Exception
	 * 
	 */
	public static <T, S> List<T> changeCollectionModel(List<S> sources, Class<T> c) throws Exception {
		List<T> targets = new ArrayList<T>();
		T t;
		if (sources != null && sources.size() > 0) {
			for (S s : sources) {
				// �������Ͷ���
				// c.newInstance()��������
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