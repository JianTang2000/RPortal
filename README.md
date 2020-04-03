# what is this :
Integrating R and Java by pmml.  
Part 1, run R models in RStudio;  
Part 2, Build java servers(2 servers), use models(from Part1) to do prediction and provide restful API.  
Part 3, Deploy the servers on the Google cloud.
# key words :
R, rpart, GLM, Hcluster, RandomForest, SVM, nnet pmml;  
java, maven, Spring, Spring-boot, tomcat, dubbo, zookeeper, org.jpmml, RESTfulAPI;  
Google cloud, Cpmpute Engine, CentOS 7,jar  
## API request Examples (Valid until my Google cloud expires):
### e.g.1 
get http://34.89.49.160:8282/GLM/audit  
return:  
 {
    "msg": "OK",
    "code": 200,
    "data": {
        "requestDesc": "request dataSet is empty, default examples have benn shown",
        "modelDesc": "Regression Models — glm from stats",
        "defaultData": "//    Age Employment Education       Income       TARGET_Adjusted\n//    60    Private   College      7568.23           1\n//    23    Private      Yr11      260405.44         0",
        "auditList": [
            {
                "target_Adjusted": 0.46316688257675476,
                "age": 60,
                "employment": "Private",
                "education": "College",
                "income": 7568.23
            },
            {
                "target_Adjusted": 0.010658919100189708,
                "age": 23,
                "employment": "Private",
                "education": "Yr11",
                "income": 260405.44
            }
        ]
    }
}
### e.g.2
post http://34.89.49.160:8282/GLM/audit
Body:  
{
	"requestDesc":"test",
	"modelDesc":null,
	"defaultData":null,
	"auditList":[
	{"age":74,"education":"HSgrad","income":33144.40,"employment":"Private","target_Adjusted":0.0},
	{"age":42,"education":"College","income":66139.36,"employment":"PSState","target_Adjusted":0.0}]
}  
return :  
{
    "msg": "OK",
    "code": 200,
    "data": {
        "requestDesc": "request dataSet acquired, prediction has been done",
        "modelDesc": "//    Age Employment Education       Income       TARGET_Adjusted\n//    60    Private   College      7568.23           1\n//    23    Private      Yr11      260405.44         0",
        "defaultData": null,
        "auditList": [
            {
                "target_Adjusted": 0.47964916519574197,
                "age": 74,
                "employment": "Private",
                "education": "HSgrad",
                "income": 33144.4
            },
            {
                "target_Adjusted": 0.17778087229612208,
                "age": 42,
                "employment": "PSState",
                "education": "College",
                "income": 66139.36
            }
        ]
    }
}
