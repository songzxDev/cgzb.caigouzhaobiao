-- SELECT
-- cgusr.CG_USR_ID,
-- cgusr.CG_CODE,
-- cgusr.CG_NAME,
-- cgusr.CG_LOGIN_ID,
-- cgusr.CG_PARTY_ID,
-- cordysusr.USERDN
-- FROM
-- 	cg_usr AS cgusr
-- LEFT JOIN cordys_au_user AS cordysusr ON 
--  cgusr.CG_CODE = cgusr.CG_CODE
-- AND cgusr.CG_NAME = cordysusr.`NAME`
-- AND cgusr.CG_LOGIN_ID = cordysusr.LOGIN_ID
-- AND cgusr.CG_PARTY_ID = cordysusr.PARTY_ID
-- WHERE (cordysusr.USERDN is null or cordysusr.USERDN="");
-- 
-- 