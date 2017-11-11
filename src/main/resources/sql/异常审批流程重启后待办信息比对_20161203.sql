-- 第一步：
-- 核实采购待办临时表中的待办信息是否在采购待办表中已经存在，并将已存在的待办排除，将不存在的待办信息插入到采购待办表中
INSERT INTO message_check_caigou_tab (
	PROCESS_INSTANCE_ID,
	MESSAGE_TRACK_ID,
	MESSAGE_WORKITEM_ID,
	WORKFLOW_MESSAGES_ID
) SELECT
	cgtemp.PROCESS_INSTANCE_ID,
	cgtemp.MESSAGE_TRACK_ID,
	cgtemp.MESSAGE_WORKITEM_ID,
	cgtemp.WORKFLOW_MESSAGES_ID
FROM
	message_check_caigou_tab_temp cgtemp
WHERE
	NOT EXISTS (
		SELECT
			caigou.MESSAGE_TRACK_ID
		FROM
			message_check_caigou_tab caigou
		WHERE
			caigou.MESSAGE_TRACK_ID = cgtemp.MESSAGE_TRACK_ID
	);
-- 核实CORDYS待办临时表中的待办信息是否在CORDYS待办表中已经存在，并将已存在的待办排除，将不存在的待办信息插入到CORDYS待办表中
INSERT INTO message_check_cordys_tab (
	INSTANCE_ID,
	TRACK_ID,
	MESSAGE_ID,
	ACTIVITY_ID,
	ITERATION_COUNT,
	TASK_OWNER
) SELECT
	cdtemp.INSTANCE_ID,
	cdtemp.TRACK_ID,
	cdtemp.MESSAGE_ID,
	cdtemp.ACTIVITY_ID,
	cdtemp.ITERATION_COUNT,
	cdtemp.TASK_OWNER
FROM
	message_check_cordys_tab_temp cdtemp
WHERE
	NOT EXISTS (
		SELECT
			cordys.TRACK_ID
		FROM
			message_check_cordys_tab cordys
		WHERE
			cordys.TRACK_ID = cdtemp.TRACK_ID
	);

-- 后续执行语句
-- 清空采购临时表数据
-- DELETE FROM message_check_caigou_tab_temp;

-- 清空CORDYS临时表数据
-- DELETE FROM message_check_cordys_tab_temp;


-- 第二步：
SELECT
	cordys.CD1_INSTANCE_ID,
	cordys.CD1_TRACK_ID,
	cordys.CD1_MESSAGE_ID,
	caigou.CG2_WORKFLOW_MESSAGE_ID,
	caigou.CG2_PROCESS_INSTANCE_ID,
	caigou.CG2_MESSAGE_TRACK_ID,
	caigou.CG2_MESSAGE_WORKITEM_ID
FROM
	(
		SELECT
			cd1.INSTANCE_ID AS CD1_INSTANCE_ID,
			cd1.TRACK_ID AS CD1_TRACK_ID,
			cd1.MESSAGE_ID AS CD1_MESSAGE_ID,
			cg1.PROCESS_INSTANCE_ID,
			cg1.MESSAGE_TRACK_ID,
			cg1.MESSAGE_WORKITEM_ID,
			cg1.WORKFLOW_MESSAGES_ID
		FROM
			message_check_cordys_tab AS cd1
		LEFT JOIN MESSAGE_CHECK_CAIGOU_TAB AS cg1 ON cd1.TRACK_ID = cg1.MESSAGE_TRACK_ID
		WHERE
			cd1.USABLE_STATUS = '1'
		AND cg1.USABLE_STATUS IS NULL
	) AS cordys
LEFT JOIN (
	SELECT
		cg2.PROCESS_INSTANCE_ID AS CG2_PROCESS_INSTANCE_ID,
		cg2.MESSAGE_TRACK_ID AS CG2_MESSAGE_TRACK_ID,
		cg2.MESSAGE_WORKITEM_ID AS CG2_MESSAGE_WORKITEM_ID,
		cg2.WORKFLOW_MESSAGES_ID AS CG2_WORKFLOW_MESSAGE_ID,
		cd2.INSTANCE_ID,
		cd2.TRACK_ID,
		cd2.MESSAGE_ID
	FROM
		MESSAGE_CHECK_CAIGOU_TAB AS cg2
	LEFT JOIN message_check_cordys_tab AS cd2 ON cg2.MESSAGE_TRACK_ID = cd2.TRACK_ID
	WHERE
		cg2.USABLE_STATUS = '1'
	AND cd2.USABLE_STATUS IS NULL
) AS caigou ON cordys.CD1_INSTANCE_ID = caigou.CG2_PROCESS_INSTANCE_ID WHERE caigou.CG2_WORKFLOW_MESSAGE_ID !="";
-- 

-- 第三步：将查询结果导出到excel文件中，同时将excel文件中的数据导入到数据表：【aborted_process_restart_check_tab_temp】
INSERT INTO aborted_process_restart_check_tab (
	CD1_TRACK_ID,
	CD1_MESSAGE_ID,
	CG2_WORKFLOW_MESSAGE_ID,
	CG2_PROCESS_INSTANCE_ID,
	CG2_MESSAGE_TRACK_ID,
	CD1_INSTANCE_ID,
	CG2_MESSAGE_WORKITEM_ID
) SELECT
	temp.CD1_TRACK_ID,
	temp.CD1_MESSAGE_ID,
	temp.CG2_WORKFLOW_MESSAGE_ID,
	temp.CG2_PROCESS_INSTANCE_ID,
	temp.CG2_MESSAGE_TRACK_ID,
	temp.CD1_INSTANCE_ID,
	temp.CG2_MESSAGE_WORKITEM_ID
FROM
	aborted_process_restart_check_tab_temp AS temp
WHERE
	NOT EXISTS (
		SELECT
			1
		FROM
			aborted_process_restart_check_tab AS tab
		WHERE
			tab.CD1_TRACK_ID = temp.CD1_TRACK_ID
	);

-- 删除临时表数据
-- DELETE FROM aborted_process_restart_check_tab_temp;

-- 第四步：
--  执行查询SQL之前，需要先设定GROUP_CONCAT()函数的参数，默认仅支持1024，需要设置为102400
SHOW VARIABLES LIKE 'group_concat_max_len';
SET GLOBAL group_concat_max_len = 102400;
SET SESSION group_concat_max_len = 102400;


-- SELECT CONCAT("INSTANCE:",aprc.CD1_INSTANCE_ID,"'!!!!!!!!!!'") FROM aborted_process_restart_check_tab aprc WHERE aprc.USABLE_STATUS='1' AND aprc.ATTRIBUTE1='001';
-- 第（1）步，获得删除新老门户待办时使用的MESSAGE_TRACK_ID，并删除新老门户待办
SELECT GROUP_CONCAT(aprc.CG2_MESSAGE_TRACK_ID) AS 删除新老门户的TRACK_ID FROM aborted_process_restart_check_tab aprc WHERE aprc.USABLE_STATUS='1' AND aprc.ATTRIBUTE1='001';
-- 第（2）步，根据cordys中存储的待办相关字段的信息更新采购的待办相关字段的信息
SELECT CONCAT("UPDATE ERPEX.WORKFLOW_MESSAGES m SET m.MESSAGE_TRACK_ID='",aprc.CD1_TRACK_ID,"',m.MESSAGE_WORKITEM_ID='",aprc.CD1_MESSAGE_ID,"' WHERE m.WORKFLOW_MESSAGES_ID='",aprc.CG2_WORKFLOW_MESSAGE_ID,"' WITH UR;") FROM aborted_process_restart_check_tab aprc WHERE aprc.USABLE_STATUS='1' AND aprc.ATTRIBUTE1='001';
-- 第（3）步，获得新增新老门户待办时使用的MESSAGE_TRACK_ID，并新增新老门户待办
SELECT GROUP_CONCAT(aprc.CD1_TRACK_ID) AS 新增新老门户的TRACK_ID FROM aborted_process_restart_check_tab aprc WHERE aprc.USABLE_STATUS='1' AND aprc.ATTRIBUTE1='001';



-- 第五步，将ABORTED_PROCESS_RESTART_CHECK_TAB表的字段值USABLE_STATUS更新为'0'，字段值ATTRIBUTE1更新为'003'
UPDATE aborted_process_restart_check_tab aprc SET aprc.USABLE_STATUS='0',aprc.ATTRIBUTE1='003' WHERE aprc.ATTRIBUTE1='001';
-- 第六步，将MESSAGE_CHECK_CAIGOU_TAB表和MESSAGE_CHECK_CORDYS_TAB表中本次处理的数据的字段值USABLE_STATUS更新为'0'，字段值ATTRIBUTE1更新为'003'
UPDATE message_check_caigou_tab caigou SET caigou.USABLE_STATUS='0',caigou.ATTRIBUTE1='003' WHERE (caigou.ATTRIBUTE1='001' OR caigou.ATTRIBUTE1 IS NULL);
UPDATE message_check_cordys_tab cordys SET cordys.USABLE_STATUS='0',cordys.ATTRIBUTE1='003' WHERE (cordys.ATTRIBUTE1='001'OR cordys.ATTRIBUTE1 IS NULL);