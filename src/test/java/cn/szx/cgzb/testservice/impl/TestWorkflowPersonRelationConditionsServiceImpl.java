package cn.szx.cgzb.testservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.szx.cgzb.model.TworkflowPersonRelation;
import cn.szx.cgzb.service.WorkflowPersonRelationConditionsServiceI;
import cn.szx.cgzb.service.WorkflowPersonRelationServiceI;
import cn.szx.cgzb.util.MyHandleXmlUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestWorkflowPersonRelationConditionsServiceImpl {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestWorkflowPersonRelationConditionsServiceImpl.class);
	private MyHandleXmlUtil myHandleXmlUtil = MyHandleXmlUtil.getInstance();

	private WorkflowPersonRelationServiceI workflowPersonRelationService;

	private WorkflowPersonRelationConditionsServiceI workflowPersonRelationConditionsService;

	public WorkflowPersonRelationServiceI getWorkflowPersonRelationService() {
		return workflowPersonRelationService;
	}

	@Autowired
	public void setWorkflowPersonRelationService(WorkflowPersonRelationServiceI workflowPersonRelationService) {
		this.workflowPersonRelationService = workflowPersonRelationService;
	}

	public WorkflowPersonRelationConditionsServiceI getWorkflowPersonRelationConditionsService() {
		return workflowPersonRelationConditionsService;
	}

	@Autowired
	public void setWorkflowPersonRelationConditionsService(WorkflowPersonRelationConditionsServiceI workflowPersonRelationConditionsService) {
		this.workflowPersonRelationConditionsService = workflowPersonRelationConditionsService;
	}

	@Test
	public void testSave() throws Exception {
		// workflowPersonRelationConditionsService.remove();
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("workflowId", "1222");
		// params.put("workflowStepId", "004");
		// params.put("conditionN", "%10991004000000000010000100003000310000400003%");
		// Map<String, Object> conditionsMap = new HashMap<String, Object>();
		// List<TworkflowPersonRelation> workflowPersonRelationList = workflowPersonRelationService.getWorkflowPersonRelation(params);
		//
		// for (TworkflowPersonRelation tWorkflowPersonRelation : workflowPersonRelationList) {
		// conditionsMap.put(tWorkflowPersonRelation.getId().toString(), tWorkflowPersonRelation.getConditionN());
		// }
		// if (conditionsMap.isEmpty() == false) {
		// // workflowPersonRelationConditionsService.remove();
		// int counts = workflowPersonRelationConditionsService.save(myHandleXmlUtil.getTargetListFromTraversedXmlAllNodes(conditionsMap));
		// logger.error("总共向数据表【WORKFLOW_PERSON_RELATION_CONDITIONS】 插入数据总数为：" + counts);
		// }
	}

	@Test
	public void testSave2() throws Exception {
		// workflowPersonRelationConditionsService.remove();
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("workflowId", "1222");
		// params.put("workflowStepId", "004");
		// params.put("conditionN", "%10991004000000000010000100003000310000400003%");
		// Map<String, Object> conditionsMap = new HashMap<String, Object>();
		// List<TworkflowPersonRelation> workflowPersonRelationList = workflowPersonRelationService.getWorkflowPersonRelationByConditions(params).getDataGridList();
		// logger.error("总共查询出待处理数据总是为：" + workflowPersonRelationService.getWorkflowPersonRelationByConditions(params).getDataGridsCount());
		// for (TworkflowPersonRelation tWorkflowPersonRelation : workflowPersonRelationList) {
		// conditionsMap.put(tWorkflowPersonRelation.getId().toString(), tWorkflowPersonRelation.getConditionN());
		// }
		// if (conditionsMap.isEmpty() == false) {
		// // workflowPersonRelationConditionsService.remove();
		// int counts = workflowPersonRelationConditionsService.save(myHandleXmlUtil.getTargetListFromTraversedXmlAllNodes(conditionsMap));
		// logger.error("总共向数据表【WORKFLOW_PERSON_RELATION_CONDITIONS】 插入数据总数为：" + counts);
		// }
		// logger.error("总共查询出待处理数据总数为：" + workflowPersonRelationService.getWorkflowPersonRelationByConditions(params).getDataGridsCount());
	}

	/**
	 * 
	 * @Title: testSave3
	 * @Description: TODO
	 * @throws Exception
	 * @return: void
	 */
	@Test
	public void testSave3() throws Exception {
		workflowPersonRelationConditionsService.remove();
		Map<String, Object> params = new HashMap<String, Object>();
		String[] workflowIds = { "1222", "310063", "310064" };
		// String[] workflowStepIds = {"004"};
		params.put("workflowIds", workflowIds);
		// params.put("workflowStepIds", workflowStepIds);
		// params.put("conditionN", "%1099100400000000001000010000300031000040000400006%");
		Map<String, Object> conditionsMap = new HashMap<String, Object>();
		List<TworkflowPersonRelation> workflowPersonRelationList = workflowPersonRelationService.getWorkflowPersonRelationByConditionsSecondVersion(params).getDataGridList();
		logger.error("总共查询出待处理数据总数为：" + workflowPersonRelationService.getWorkflowPersonRelationByConditionsSecondVersion(params).getDataGridsCount());
		for (TworkflowPersonRelation tWorkflowPersonRelation : workflowPersonRelationList) {
			conditionsMap.put(tWorkflowPersonRelation.getId().toString(), tWorkflowPersonRelation.getConditionN());
		}
		if (conditionsMap.isEmpty() == false) {
			// workflowPersonRelationConditionsService.remove();
			int counts = workflowPersonRelationConditionsService.save(myHandleXmlUtil.getTargetListFromTraversedXmlAllNodes(conditionsMap));
			logger.error("总共向数据表【WORKFLOW_PERSON_RELATION_CONDITIONS】 插入数据总数为：" + counts);
		}
		logger.error("总共查询出待处理数据总数为：" + workflowPersonRelationService.getWorkflowPersonRelationByConditionsSecondVersion(params).getDataGridsCount());
	}
}
