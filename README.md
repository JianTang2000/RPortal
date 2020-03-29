# RPortal
Integrating R and Java. Build models(rpart,GLM,Hcluster,RandomForest,SVM,nnet) by R, export as PMML, use these XML format models in JAVA to do prediction and provide restful API.
e.g.
1: get http://127.0.0.1:8080/GLM/audit
2: post http://127.0.0.1:8080/GLM/audit
{
	"requestDesc":"test",
	"modelDesc":null,
	"defaultData":null,
	"auditList":[
	{"age":74,"education":"HSgrad","income":33144.40,"employment":"Private","target_Adjusted":0.0},
	{"age":42,"education":"College","income":66139.36,"employment":"PSState","target_Adjusted":0.0}]
}