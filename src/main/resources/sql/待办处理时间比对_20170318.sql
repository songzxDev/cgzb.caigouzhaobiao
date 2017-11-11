SELECT
workflow_instance_translog.MESSAGE_TRACK_ID AS `待办标识（track_id）`,
middle_message_synclog.SYN_DATE AS `采购发cordys时间`,
workflow_instance_translog.FINISH_DATETIME AS `cordys处理完成时间`,
workflow_instance_translog.STEP_ID AS `处理环节ID`,
workflow_instance_translog.STEP_NAME AS `处理环节名称`,
workflow_instance_translog.STEP_USER_DN AS cordys_userdn,
workflow_instance_translog.STEP_USER_NAME AS `待办处理人`,
workflow_instance_translog.NEXT_STEP_ID AS `下一环节ID`,
workflow_instance_translog.NEXT_STEP_NAME AS `下一环节名称`,
workflow_instance_translog.NEXT_STEP_USER_DN AS `下一环节cordys_userdn`,
workflow_instance_translog.NEXT_STEP_USER_NAME AS `下一环节处理人`,
middle_message_synclog.LOG_TEXT AS `日志信息`,
middle_message_synclog.STEP_NAME AS `备注`
FROM
workflow_instance_translog
LEFT JOIN middle_message_synclog ON workflow_instance_translog.MESSAGE_TRACK_ID = middle_message_synclog.CURRENT_GUID
