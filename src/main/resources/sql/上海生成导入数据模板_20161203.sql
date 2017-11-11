SELECT
	CONCAT("'", wpr.ID) 序号,
	CONCAT("'", wpr.WORKFLOW_ID) 流程ID,
	CONCAT("'", wpr.WORKFLOW_NAME) 流程名称,
	CONCAT("'", wpr.WORKFLOW_STEP_ID) 环节ID,
	CONCAT("'", wpr.WORKFLOW_STEP_NAME) 环节名称,
	CONCAT("'", wpr.SOURCE_STEP_ID) 来源环节ID,
	wpr.SOURCE_STEP_NAME 来源环节名称,
	CASE wpr.FILTER_TYPE
WHEN 'b' THEN
	'过滤人员'
WHEN 'c' THEN
	'过滤人员与环节'
WHEN 'd' THEN
	'过滤环节'
END 过滤类型,
 CONCAT("'", wpr.PARTY_CODE) 人员CODE,
 wpr.PARTY_NAME 人员名称,
 CASE wpr.WORKFLOW_ID
WHEN '1222' THEN
	'需求单'
WHEN '310063' THEN
	'采购订单'
WHEN '310064' THEN
	'采购订单'
END 单据类型,
 CASE wprcon.CON_ID
WHEN '100001' THEN
	'预算金额'
WHEN '100002' THEN
	'合同类型'
WHEN '100003' THEN
	'专业类别'
WHEN '100004' THEN
	'需求部门'
WHEN '400001' THEN
	'编制人部门'
END 字段名称,
 CASE wprcon.LOGIC_TYPE
WHEN 'a' THEN
	'等于'
WHEN 'b' THEN
	'<='
WHEN 'c' THEN
	'>='
WHEN 'd' THEN
	'<'
WHEN 'e' THEN
	'>'
WHEN 'f' THEN
	'like'
END 逻辑关系,
 CONCAT("'", wprcon.CON_VALUE) 条件值
FROM
	workflow_person_relation wpr
LEFT JOIN workflow_person_relation_conditions wprcon ON wpr.ID = wprcon.WORKFLOW_PERSON_RELATION_ID
AND wprcon.USABLE_STATUS = '1'
AND wprcon.ATTRIBUTE1 = '001'
WHERE
	(
		wpr.WORKFLOW_ID = '1222'
		OR wpr.WORKFLOW_ID = '310063'
		OR wpr.WORKFLOW_ID = '310064'
	)
ORDER BY
	wpr.WORKFLOW_ID;