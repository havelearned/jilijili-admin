
`mvn flyway:baseline` 
基准（该命令创建的版本号未V1,所以开发的第一个版本不要叫V1.0或者V1.0.0等）<br/>
`mvn flyway:migrate`
迁移<br/>
`mvn flyway:info`
信息<br/>
`mvn flyway:validate`
校验<br/>
`mvn flyway:clean`
清除所有表<br/>
`mvn flyway:undo`
撤销（免费版不支持）<br/>
`mvn flyway:repair`
修复，只能撤销对应的sql执行记录，不能回滚整个sql脚本