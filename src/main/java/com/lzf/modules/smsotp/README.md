```sql
-- Drop table

-- DROP TABLE TB_SMS_OTP_A;

CREATE TABLE TB_SMS_OTP_A (
	ID int IDENTITY(1,1) NOT NULL,
	VERSION int NOT NULL,
	[TYPE] varchar(20) COLLATE Chinese_PRC_CI_AS NOT NULL,
	LAST_SEND_OTP_TIMES int NOT NULL,
	LAST_VERIFY_OTP_TIMES int NOT NULL,
	VERIFICATION_CODE varchar(6) COLLATE Chinese_PRC_CI_AS NOT NULL,
	ENC_CRN varchar(256) COLLATE Chinese_PRC_CI_AS NOT NULL,
	SEND_TIME datetime NOT NULL,
	CONSTRAINT PK_TB_SMS_OTP_A PRIMARY KEY (ID)
);
 CREATE  UNIQUE NONCLUSTERED INDEX TB_SMS_OTP_A_ENC_CRN_IDX ON dbo.TB_SMS_OTP_A (  ENC_CRN ASC  , TYPE ASC  )
	 WITH (  PAD_INDEX = OFF ,FILLFACTOR = 100  ,SORT_IN_TEMPDB = OFF , IGNORE_DUP_KEY = OFF , STATISTICS_NORECOMPUTE = OFF , ONLINE = OFF , ALLOW_ROW_LOCKS = ON , ALLOW_PAGE_LOCKS = ON  )
	 ON [PRIMARY ] ;

-- SEQUENCE definition
CREATE SEQUENCE SEQ_SMS_OTP_REF_NO AS INT START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 999999 CYCLE;
```

**功能逻辑**：
1. 发送
   1. 查询表中无数据，发送验证码并插入表
   2. 查询表中有数据，判断冻结时间和间隔时间，发送验证码并更新表
2. 验证
   1. 查询表中有数据，版本和验证码双匹配
      1. 成功后删除
      2. 失败后更新，返回次数
