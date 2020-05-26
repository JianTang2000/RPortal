<template>
    <div id="app">

        <!--        最上面的导航栏-->
        <el-row>
            <el-col :span="5">
                <div class="grid-content bg-purple-dark"></div>
            </el-col>
            <el-col :span="14">
                <el-menu class="el-menu-demo" mode="horizontal" background-color="#1E89E0" text-color="#fff"
                         active-text-color="#ffd04b">
                </el-menu>
            </el-col>
            <el-col :span="2">
                <el-menu class="el-menu-demo" mode="horizontal" background-color="#1E89E0" text-color="#fff"
                         active-text-color="#ffd04b">
                    <el-menu-item index="7">
                        <a href="https://github.com/JianTang2000/RPortal" target="_blank">GitHub Link</a>
                    </el-menu-item>
                </el-menu>
            </el-col>
            <el-col :span="3">
                <div class="grid-content bg-purple-dark"></div>
            </el-col>
        </el-row>

        <!--    空白模板，用以拷贝，-->
        <el-row>
            <el-col :span="3">
                <div class="grid-content-blank bg-purple-dark-blank"></div>
            </el-col>
            <el-col :span="18">
                <div class="grid-content-blank bg-purple-dark-blank">


                </div>
            </el-col>
            <el-col :span="3">
                <div class="grid-content-blank bg-purple-dark-blank"></div>
            </el-col>
        </el-row>

        <!--    主界面 行-->
        <el-row>
            <el-col :span="3">
                <div class="grid-content-blank bg-purple-dark-blank"></div>
            </el-col>
            <el-col :span="18">
                <div class="grid-content-blank bg-purple-dark-blank">
                    <el-collapse v-model="activeNames" @change="handleChange">

                        <el-collapse-item name="0">
                            <template slot="title">
                                <h2>A PMML Based Framework for Developing R-Java Analytic Applications</h2>
                            </template>
                            <div>welcome to here~</div>
                            <div>The process of building R-Java analytic applications presents challenges such as data wrangling, integrating and model presentation. In this paper, four R and Java integration methods and related scenarios are summarized.
                            </div>
                            <div>A Predictive Modeling Markup Language (PMML) standard based framework that allows independent R model producers and Java consumers is presented.</div>
                            <div>A demo to show the lifecycle of developing and deploying a R-Java analytic application based on this framework is also presented.</div>
                            <div>click the "Examples" in the bottom and Github link click the
                                "gitHub Link" above
                            </div>
                        </el-collapse-item>

                        <el-collapse-item name="1">
                            <template slot="title">
                                <h2>Introduction</h2>
                            </template>
                            <div>R is very powerful in data analysis and visualization, but in software engineering languages such as C++ and java are more popular. By R-Java analytic application we mean an application coded by R and Java that can analyze historical data, extract the potential meaning and give prediction to the new coming data. Examples include a website or a smartphone APP.
                            </div>
                            <div>R data analysis has outputs come in a variety of formats, such as plots and analytic models. By integration we mean plugging in these analytic results into other language coded software project, this integration can be implemented in various ways. The goal here is to find ways that can implement the integration between R and Java. More greedy speaking, we want they fast, stable, easy to understand and implement in related scenarios.
                            </div>
                            <div>
                                In this paper several integration methods and their applicable scenarios are listed. The explanation, usage, merits and demerits for each method are summarized.
                            </div>
                            <div>The Streaming Machine Learning Architecture proposed by Oracle (Arango, Mauricio and Alex, 2016) allows model building based on Oracle R Enterprise (ORE) and model presentation based on Oracle Stream Explorer (OSX). The Predictive Modeling Markup Language (PMML) standard (Guazzelli, Alex, et al, 2009) is used to transfer information from ORE to OSX. However, too much reliance on the Oracle products makes this architecture too heavy to implement, a PMML based framework for building analytic application is presented here, it has much lighter weight and allows independent R model producers and consumers, many machine learning algorithms are well supported.
                            </div>
                            <div>A demo to show the complete lifecycle of developing and deploying a R-Java analytic application based on this PMML framework is presented. The source code is available on GitHub and an accessible website provided by this demo is also shown.
                            </div>
                        </el-collapse-item>

                        <el-collapse-item name="2">
                            <template slot="title">
                                <h2>Optional Integration methods</h2>
                            </template>
                            <div><h3>1. Using R in java (and vice versa)</h3></div>
                            <div><h3>2. Using DB or files</h3></div>
                            <div><h3>3. Using HTTP API in R</h3></div>
                            <div><h3>4. Using PMML</h3></div>
                        </el-collapse-item>

                        <el-collapse-item name="3">
                            <template slot="title">
                                <h2>1. Using R in java (and vice versa)</h2>
                            </template>
                            <div>This means coding R code in Java project, or coding java code in R project. We can implement this by using RServe (Urbanek, Simon, 2003) or JRI (Urbanek, Simon, 2011). RServe and JRI are well used in many R-java integrated projects. Programmers are required to master both R and Java to start work.
                            </div>
                            <div><h3>RServe.</h3></div>
                            <div>Rserve is a TCP/IP server. It works in Client/Server mode, the client side supports various programming languages such as Java, C++ and PHP. The client side can get R responses back by sending raw R code or R files to the server without the need to install any R environment setting. Authentication and session control are also supported. </div>
                            <div>Rserve is easy to start since there is no need to any R setting in client side. But using RServe means we need to build a client/server system, and it’s based on TCP/IP, running slower than JRI because more time will be spent in network communication.</div>
                            <div><h3>JRI.</h3></div>
                            <div>JRI is a Java/R interface. It can be used to support the single-threaded running of raw R codes inside Java applications, JRI is the inverse of rJava (Urbanek, Simon, 2013) where we can run java code in R project. The basic idea behind JRI is to load required R libraries into Java environments and roll, calls to R function and running REPL(Read–eval–print loop) are supported.
                            </div>
                            <div>JRI requires more complex environment setting and this can vary from different operating systems. But it’s an inner Java operation which means better running performance.</div>
                        </el-collapse-item>

                        <el-collapse-item name="4">
                            <template slot="title">
                                <h2>2. Using DB or file as bridge</h2>
                            </template>
                            <div>By DB here we mean database, such as MySQL and Oracle. This way is easy to start: we can just save the R results to DB or local files, and then read them into other language coded applications. R package RMySQ (James, David, and Saikat, 2012) can be used to provide R interface to the MySQL database. And a variety of file formats, for example, .txt, .csv, .png , are well supported by R.</div>
                            <div>Using DB or files as bridge is an intuitive way and easy to start, R outputs such as plots and numbers can be well transmitted without misunderstanding. However, R outputs such as analytic models have the format that other language can never understand and use directly without specific interpreter. Besides, too much I/O operation presents the challenge to running performance and stability when facing with large size data or high-concurrency scenario.</div>
                        </el-collapse-item>

                        <el-collapse-item name="5">
                            <template slot="title">
                                <h2>3. Using HTTP API in R</h2>
                            </template>
                            <div>This means exposing R codes as services in the HTTP (or REST) API format available on the Internet. The Hypertext Transfer Protocol (HTTP) API has become the predominant standard for helping different language coded systems to talk to each other. We can create HTTP APIs in R with package Plumber (Cran.R-Project.Org plumber, 2020), so as to our R codes dressed as services can communicate with other systems as long as they are in the same subnet, or public Internet.</div>
                            <div>HTTP API provides rich inputs and outputs formats such as Jason strings, HTML, PNG, or JPEG. R package Plumber helps user to build web API by merely adding special annotations above the normal R function, no more special editing for the R function body or definition is needed. These R functions will be exposed as Web APIs automatically. The APIs will use default port 8000 and will continue running in the R session until an Esc command is triggered.</div>
                            <div>No function definition or body editing is need, but the Plumber API does require specific annotation formats, we can use either #* or # ':</div>
                            <div>	#' @param a. This specifies the input parameter name for the function</div>
                            <div>	#' @get /echo. This specifies the function as a @get request, and echoes the message passed in. Other request types @post, @put, @delete, @head are also supported.</div>
                            <div>	#' @png. This specifies the output of the function is an image. Other formats such as @html, @jpeg are also supported.</div>
                            <div>Plumber API is easy to start, what we need is to decorate our normal R function with some special annotations, and Plumber will do the rest, such as port setting and routing, and of course we can change these default setting manually. Then our R function will be available online before we know it. Just like Rserve, more time will be spent in network communication if we call too much web APIs in our system. The difference between Plumber and Rserve is that in Rserve we send raw R code to a serve and get response, but in Plumber we send API parameters, which means users don’t need master R.</div>
                        </el-collapse-item>

                        <el-collapse-item name="6">
                            <template slot="title">
                                <h2>4. Using PMML</h2>
                            </template>
                            <div>In the case where we need build predictive and descriptive models, such as GLM, Hclust, SVM, randomForest, nnet, in the generator side by using R or python, and consume these models in other language coded system such as Java or C++, we can use PMML as bridge. </div>
                            <div>The goal of PMML is to rise a standard for wrapping models so that two different sides, one called the PMML producer, one called consumer, can work together (Ferrucci, et al, 2006). Package PMML in R allows user to export a variety of analytic models and necessary datasets from R to .pmml files in the producer side. These files use XML schema defined by PMML to describe the model structure. Then consumer side, commonly coded by other languages and running in environments without any R setting, can import these .pmml files and do presentation with the help with specific evaluator. JPMML (Java PMML API, 2020) is an open source Java library that provides interfaces to run this PMML interpretation.</div>
                            <div>The PMML generator and consumer can be linked by static .pmml files, so as to in the consumer side no network communication or R/python code running is required, the inner system operation, pure Java, makes it possible for a fast and stable running performance when facing with large size data or high-concurrency scenario. </div>
                            <div>Outputs such as plots are not supported by PMML, but if the integration context is about producing and consuming predictive and descriptive models, and they are supported by PMLL, PMLL is a safer and more efficient way. </div>
                        </el-collapse-item>

                        <!--                        summary table-->
                        <el-collapse-item name="7">
                            <template slot="title">
                                <h2>Summary</h2>
                            </template>
                            <template>
                                <el-table
                                        :data="tableData"
                                        style="width: 100%">
                                    <el-table-column
                                            prop="date"
                                            label=""
                                            width="100">
                                    </el-table-column>
                                    <el-table-column
                                            prop="name"
                                            label="Desc"
                                            width="200">
                                    </el-table-column>
                                    <el-table-column
                                            prop="address"
                                            label="Some Pros & Cons">
                                    </el-table-column>
                                </el-table>
                            </template>
                        </el-collapse-item>

                        <el-collapse-item name="8">
                            <template slot="title">
                                <h2>A PMML based framework</h2>
                            </template>
                            <div>The Streaming Machine Learning Architecture proposed by Oracle supports the separable, fashion independent components in model development and building based on Oracle R Enterprise (ORE) and model presentation based on Oracle Stream Explorer (OSX). PMML in this framework is used to transfer models from ORE to OSX, and special PMML evaluator in OSX is needed for preprocessing, model import and scoring. Since too much reliance on the Oracle products makes this architecture too heavy to implement, based on this ideal a PMML based framework for building analytic application is proposed here, it’s much lighter and allows independent R model producers and consumers. </div>
                            <div>The PMML framework involves two main stages:</div>
                            <div><h3>Model Training.</h3></div>
                            <div>Use structured or semi-structured data as input, necessary data cleaning and
                                preparing is required.</div>
                            <div>Large dataset processing may be required.</div>
                            <div>A variety of machine learning algorithms should be supported.</div>
                            <div>Processing with low frequency.</div>

                            <div><h3>Model Scoring.</h3></div>
                            <div>Contains all scoring information and provides interfaces to handle input streaming
                                data directly.</div>
                            <div>Supports automatic and manual model refresh.</div>
                            <div>Processing with high frequency.</div>

                            <div><h3>The PMML framework allows the following step-to-step workflow:</h3></div>
                            <div>Model generation. Using structured or semi-structured data as input to build analytic
                                models. The training can be triggered automatically or manually. R environment setting
                                and related R libraries are need, data wrangling and preparing is required. Machine
                                learning algorithms supported by PMML can be accessed by checking the latest R
                                PMML package doc.</div>
                            <div>Model export by PMML standard. PMML package in R allows to export models to
                                xml format .pmml files. All necessary scoring information and datasets are included.
                            </div>
                            <div>Model storage. Model files can be stored as static files or file stream in self-defined
                                memories. Database is not necessary since the model building process is in low
                                frequency. Triggers for model refresh is supported.</div>
                            <div>Model import and scoring function creating. The PMML evaluator JPMML is need
                                .to help importing PMML files or file stream and scoring interfaces design. A desirable
                                maven dependency version can be org.jpmml pmml-evaluator 1.4.6.</div>
                            <div>JPMML provides common model evaluator Java class, but the consumer interfaces can
                                still vary due to the training models can be built by using a variety of algorithms or
                                datasets. This means semi-automatic scoring interfaces can be generated, all we need is
                                to specific parameter names or other necessary formats.</div>
                            <div>Exposing and consuming. In this part exposed scoring functions and visual interfaces
                                such as a web portal is generated. Java functions can be exposed as web APIs by using
                                Spring-boot (Walls, Craig, 2016) to support the integration with web programming
                                languages such as Javascript, or Dubbo servers (Apache Dubbo, 2020) as plugins in
                                distributed systems. And visualization such as a website can be built by calling these
                                servers</div>
                        </el-collapse-item>

                        <el-collapse-item name="9">
                            <template slot="title">
                                <h2>Building Applications Using the PMML framework
                                </h2>
                            </template>
                            <div>The PMML framework provides an end-to-end workflow and implementation for each
                                component. In this section an example of developing and deploying analytic application
                                based on this framework is presented.</div>

                            <div><h3>This demo application has the following components:</h3></div>
                            <div>Model producer in R. 6 machine learning models rpart, glm, hclust, SVM, nnet,
                                randomForest are built by using dataset “iris” or “audit”, Models are exported as
                                PMML format files. No database is used.</div>
                            <div>fit <- rpart(Species ~ ., data = iris)</div>
                            <div>saveXML(pmml(fit), ".//R//irisRpart.pmml")</div>
                            <div>mod <- glm(TARGET_Adjusted ~ Age + Employment + Education + Income,data = audit, family = binomial(logit)) </div>
                            <div>saveXML(pmml(mod), ".//R//auditGLM.pmml")</div>
                            <div>Scoring in Java. An optional maven dependency used for JPMML is 1.4.6.
                            </div>
                            <div>Semi-automatic scoring interfaces are generated by JPMML. The model evaluator class
                                will load model files defined by model producer into JVM and no file reading
                                processing is need during the high frequency scoring unless a model refresh is triggered.</div>
                            <div>Scoring interfaces accept input data stream and give prediction output stream by using
                                class evaluator defined above, input parameter formats can be determined by checking
                                the model predictor variables information</div>
                            <div>Exposing. The scoring functions are exposed as Web APIs and Dubbo servers available
                                externally. Web APIs is created by Spring-boot and consumed by the web portal. Dubbo
                                servers is created by Dubbo to work as plugins in the distributed system to provide high-performance function call. For the full code detail please check the GitHub link
                                https://github.com/JianTang2000/RPortal.</div>
                            <div>Web. A website is built for model introduction and operation such as model refresh and
                                prediction. The web is built by using VUE (Macrae, Callum, 2018) and ElementUI
                                (Element, 2020). The following web screenshots show how to input predictor variables
                                and get prediction in the “audit” dataset, glm scenario and the “iris” dataset, rpart
                                scenario.</div>
                            <div>Deployment. This application is deployed on the Google Cloud Platform (Krishnan,
                                Jose, 2015). A quick setup simply includes public network, static IP address, SSH tunnel,
                                centos OS 7 operating system, Java environment setting, Dubbo environment setting
                                and node.js environment setting. Most of them are well supported by the Google Cloud
                                Platform so the deployment is easy to be done. An accessible address is
                                http://35.189.127.74:8110. This website will keep running and be available on the
                                Internet as long as the associated Google resources are not released.</div>

                        </el-collapse-item>


                        <el-collapse-item name="10">
                            <template slot="title">
                                <h2>Conclusion</h2>
                            </template>
                            <div>In this paper, we have summarized 4 methods to implement the R-Java integration and
                                their related scenarios. We have presented a light weight, Predictive Modeling Markup
                                Language (PMML) standard based framework, it allows independent R model
                                producers and consumers. We have also shown the development and deployment of a
                                demo analytic application based on the PMML framework.
                            </div>
                        </el-collapse-item>


                        <!--                        example-->
                        <el-collapse-item name="11">
                            <template slot="title">
                                <h2>Examples</h2>
                            </template>
                            <div><h3>This website is an example of integration R&Java by using PMML.</h3></div>
                            <div><h3>what's behind this website? </h3>
                                We built 6 R models ("rpart"
                                ,"GLM"
                                ,"Hcluster"
                                ,"RandomForest"
                                ,"SVM"
                                ,"nnet"
                                ) in R by using two
                                different dataset (click to see more details (such as levels/range),
                                <el-button type="text" size="medium" @click="irisVisible = true">"iris"</el-button>
                                and
                                <el-button type="text" size="medium" @click="adultVisible = true">"adult"</el-button>
                                ), export these models to .pmml files by using PMML
                                package in R.
                                <!--                                We built 6 R models (-->
                                <!--                                <el-button type="text" size="medium" @click.prevent="rpart()">"rpart"</el-button>-->
                                <!--                                ,-->
                                <!--                                <el-button type="text" size="medium" @click.prevent="GLM()">"GLM"</el-button>-->
                                <!--                                ,-->
                                <!--                                <el-button type="text" size="medium" @click.prevent="Hcluster()">"Hcluster"</el-button>-->
                                <!--                                ,-->
                                <!--                                <el-button type="text" size="medium" @click.prevent="RandomForest()">"RandomForest"-->
                                <!--                                </el-button>-->
                                <!--                                ,-->
                                <!--                                <el-button type="text" size="medium" @click.prevent="SVM()">"SVM"</el-button>-->
                                <!--                                ,-->
                                <!--                                <el-button type="text" size="medium" @click.prevent="nnet()">"nnet"</el-button>-->
                                <!--                                ) in R by using two-->
                                <!--                                different dataset (click to see more details,-->
                                <!--                                <el-button type="text" size="medium" @click.prevent="adult()">"adult"</el-button>-->
                                <!--                                and-->
                                <!--                                <el-button type="text" size="medium" @click.prevent="iris()">"iris"</el-button>-->
                                <!--                                ), export these models to .pmml files by using PMML-->
                                <!--                                package in R.-->
                            </div>
                            <div>We built a java server, it read these ".pmml" files into JVM by using jpmml library,
                                and provide several
                                REST APIs (such as model desc, single prediction, multi prediction) by using Spring-boot
                                framework.
                            </div>
                            <div>We built a website Portal(this website) to describe context and show how some of these
                                interfaces work.
                            </div>
                            <br>
                            <div>
                                E.g. In "rpart" example, first you can see the R model behind this part, then you can
                                input
                                sepal_Width, sepal_Length, petal_Length and petal_Width, click button "predict" to
                                get the predicted species.
                            </div>

                            <!--                            rpart-->
                            <el-row>
                                <div><h4>rpart (using iris)</h4></div>
                                <div>R code : fit <- rpart(Species ~ ., data = iris)</div>
                                <div>R code : saveXML(pmml(fit), ".//R//007//A--term2//irisRpart.pmml")</div>
                                <div>
                                    <el-col :span="4" style="margin: 5px"> sepal_Width ：
                                        <el-input v-model="input1" placeholder="e.g. 2.8"></el-input>
                                    </el-col>
                                    <el-col :span="4" style="margin: 5px">
                                        sepal_Length ：
                                        <el-input v-model="input2" placeholder="e.g. 4.6"></el-input>
                                    </el-col>
                                    <el-col :span="4" style="margin: 5px">petal_Length ：
                                        <el-input v-model="input3" placeholder="e.g. 1.3"></el-input>
                                    </el-col>
                                    <el-col :span="4" style="margin: 5px">petal_Width ：
                                        <el-input v-model="input4" placeholder="e.g. 0.3"></el-input>
                                    </el-col>
                                    <el-col :span="4" style="margin: 5px"> species:
                                        <el-input :disabled="true" v-model="input5"></el-input>
                                    </el-col>
                                </div>
                                <div>
                                    <el-col :span="5" style="margin: 5px">
                                        <el-button type="primary" size="medium" @click.prevent="Prpart()">predict
                                        </el-button>
                                    </el-col>
                                </div>
                            </el-row>

                            <!--                            glm-->
                            <el-row>
                                <div><h4>glm (using audit)</h4></div>
                                <div>R code : mod <- glm(TARGET_Adjusted ~ Age + Employment + Education + Income, data
                                    =
                                    audit, family = binomial(logit))
                                </div>
                                <div>R code :saveXML(pmml(mod), ".//R//007//A--term2//auditGLM.pmml")
                                </div>
                                <div>
                                    <el-col :span="4" style="margin: 5px"> Age ：
                                        <el-input v-model="input21" placeholder="e.g. 17-90"></el-input>
                                    </el-col>
                                    <el-col :span="4" style="margin: 5px">
                                        Employment ：
                                        <el-input v-model="input22" placeholder="e.g. Consultant/Private/PSFederal..."></el-input>
                                    </el-col>
                                    <el-col :span="4" style="margin: 5px">Education ：
                                        <el-input v-model="input23" placeholder="e.g. College/Doctorate/Master..."></el-input>
                                    </el-col>
                                    <el-col :span="4" style="margin: 5px">Income ：
                                        <el-input v-model="input24" placeholder="e.g. 609.72-481259.50"></el-input>
                                    </el-col>
                                    <el-col :span="4" style="margin: 5px"> TARGET_Adjusted:
                                        <el-input :disabled="true" v-model="input25"></el-input>
                                    </el-col>
                                </div>
                                <div>
                                    <el-col :span="5" style="margin: 5px">
                                        <el-button type="primary" size="medium" @click.prevent="PGLM()">predict
                                        </el-button>
                                    </el-col>
                                </div>
                            </el-row>

                            <!--                            Hcluster-->
                            <el-row>
                                <div><h4>Hcluster (using iris)</h4></div>
                                <div>R code : model <- hclusterpar(iris[, -5])</div>
                                <div>R code : centerInfo <- centers.hclust(iris[, -5], model, 3)</div>
                                <div>R code : model_pmml <- pmml(model, centers = centerInfo)</div>
                                <div>R code : saveXML(model_pmml, ".//R//007//A--term2//irisHclust.pmml")</div>

                                <div>The prediction interface for Hcluster does exist. It's pretty much the same as the
                                    previous example, so I'm not going to show it here
                                </div>
                            </el-row>

                            <!--                            randomForest-->
                            <el-row>
                                <div><h4>randomForest (using iris)</h4></div>
                                <div>R code : iris_rf <- randomForest(Species ~ ., data = iris, ntree = 20)</div>
                                <div>R code : iris_rf_pmml <- pmml(iris_rf)</div>
                                <div>R code : saveXML(iris_rf_pmml, ".//R//007//A--term2//irisRandomForest.pmml")</div>

                                <div>The prediction interface for randomForest does exist. It's pretty much the same as
                                    the
                                    previous example, so I'm not going to show it here
                                </div>
                            </el-row>

                            <!--                            nnet-->
                            <el-row>
                                <div><h4>nnet (using iris)</h4></div>
                                <div>R code : fit <- nnet(Species ~ ., data = iris, size = 4)</div>
                                <div>R code : fit_pmml <- pmml(fit)</div>
                                <div>R code : saveXML(fit_pmml, ".//R//007//A--term2//irisNnet.pmml")</div>
                                <div>The prediction interface for nnet does exist. It's pretty much the same as the
                                    previous example, so I'm not going to show it here
                                </div>
                            </el-row>

                            <!--                            svm-->
                            <el-row>
                                <div><h4>svm (using iris)</h4></div>
                                <div>R code : model <- ksvm(Species ~ ., data = iris)</div>
                                <div>R code : model_pmml <- pmml(model, dataset = iris)</div>
                                <div>R code : saveXML(model_pmml, ".//R//007//A--term2//irisSVM.pmml")</div>
                                <div>The prediction interface for svm does exist. It's pretty much the same as the
                                    previous example, so I'm not going to show it here
                                </div>
                            </el-row>

                        </el-collapse-item>

                        <el-collapse-item name="12">
                            <template slot="title">
                                <h2>References</h2>
                            </template>
                            <div>Arango, Mauricio, and Alex Ardel. "Machine Learning on Streaming Data via
                                Integration of Oracle Advanced Analytics and Oracle Stream Explorer." (2016).</div>
                            <div>Guazzelli, Alex, et al. "PMML: An open standard for sharing models." The R Journal
                                1.1 (2009): 60-65.</div>
                            <div>Urbanek, Simon. "Rserve--a fast way to provide R functionality to applications." PROC.
                                OF THE 3RD INTERNATIONAL WORKSHOP ON DISTRIBUTED
                                STATISTICAL COMPUTING (DSC 2003), ISSN 1609-395X, EDS.: KURT
                                HORNIK, FRIEDRICH LEISCH & ACHIM ZEILEIS, 2003
                                (HTTP://ROSUDA. ORG/RSERVE. 2003.</div>
                            <div>Urbanek, Simon. "JRI: Java-R Interface." URL http://www. rforge. net/JRI/index. html
                                (2011).</div>
                            <div>Urbanek, Simon. "rJava: Low-level R to Java interface." (2013).</div>
                            <div>James, David A., and Saikat DebRoy. "RMySQL: R interface to the MySQL database."
                                R package version 0.9-3 (2012).</div>
                            <div>Cran.R-Project.Org, 2020, https://cran.r-project.org/web/packages/plumber/plumber.
                                pdf. </div>
                            <div>Ferrucci, David, Robert L. Grossman, and Anthony Levas. "PMML and UIMA based
                                frameworks for deploying analytic applications and services." Proceedings of
                                the 4th international workshop on Data mining standards, services and
                                platforms. 2006.</div>
                            <div>"Java PMML API". Github, 2020, https://github.com/jpmml.</div>
                            <div>Cran.R-Project.Org, 2020, https://cran.r-project.org/web/packages/pmml/pmml.pdf</div>
                            <div>Walls, Craig. Spring Boot in action. Manning Publications, 2016.</div>
                            <div>"Apache/Dubbo". Github, 2020, https://github.com/apache/dubbo.</div>
                            <div>Macrae, Callum. Vue. js: Up and Running: Building Accessible and Performant Web
                                Apps. " O'Reilly Media, Inc.", 2018.
                            </div>
                            <div>"Element - The World's Most Popular Vue UI Framework". Element.Eleme.Io, 2020,
                                https://element.eleme.io/#/en-US.</div>
                            <div>Krishnan, S. P. T., and Jose L. Ugia Gonzalez. Building your next big thing with google
                                cloud platform: A guide for developers and enterprise architects. Apress, 2015.</div>
                        </el-collapse-item>

                    </el-collapse>

                    <br><br><br><br><br><br><br>

                </div>
            </el-col>
            <el-col :span="3">
                <div class="grid-content-blank bg-purple-dark-blank"></div>
            </el-col>
        </el-row>


        <!-- iris -->
        <el-dialog title="iris data " :visible.sync="irisVisible">
            <div> >head(iris[sample(nrow(iris)),])</div>
            <div> Sepal.Length Sepal.Width Petal.Length Petal.Width Species</div>
            <div> 6.0 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2.9
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                4.5 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                1.5 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                versicolor
            </div>
            <div> 5.7 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                3.0 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                4.2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                1.2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                versicolor
            </div>
            <div> 5.0 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                3.0 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                1.6 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                0.2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                setosa
            </div>
            <div> 7.6 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                3.0 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                6.6 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                2.1 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                virginica
            </div>
            <div> 4.8 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                3.4 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                1.6 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                0.2 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                etosa
            </div>
            <div> 7.7 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                2.6 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                6.9 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                2.3 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                virginica
            </div>

            <div> > range(iris$Sepal.Length)</div>
            <div> [1] 4.3 7.9</div>
            <div> > range(iris$Sepal.Width)</div>
            <div> [1] 2.0 4.4</div>
            <div> > range(iris$Petal.Length)</div>
            <div> [1] 1.0 6.9</div>
            <div> > range(iris$Petal.Width)</div>
            <div> [1] 0.1 2.5</div>

        </el-dialog>

        <!-- adult -->
        <el-dialog title="adult data " :visible.sync="adultVisible">
            <div> > head(audit[sample(nrow(audit)),c(2,3,4,7,13)])</div>
            <div>Age Employment Education Income TARGET_Adjusted</div>
            <div>228 &nbsp;&nbsp;&nbsp;&nbsp;
                23 &nbsp;&nbsp;&nbsp;&nbsp;
                Private &nbsp;&nbsp;&nbsp;&nbsp;
                HSgrad &nbsp;&nbsp;&nbsp;&nbsp;
                106275.40 &nbsp;&nbsp;&nbsp;&nbsp;
                0 &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div>369 &nbsp;&nbsp;&nbsp;&nbsp;
                54 &nbsp;&nbsp;&nbsp;&nbsp;
                Private &nbsp;&nbsp;&nbsp;&nbsp;
                Yr10 &nbsp;&nbsp;&nbsp;&nbsp;
                60708.32 &nbsp;&nbsp;&nbsp;&nbsp;
                0 &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div>200 &nbsp;&nbsp;&nbsp;&nbsp;
                60 &nbsp;&nbsp;&nbsp;&nbsp;
                SelfEmp &nbsp;&nbsp;&nbsp;&nbsp;
                HSgrad &nbsp;&nbsp;&nbsp;&nbsp;
                235662.80 &nbsp;&nbsp;&nbsp;&nbsp;
                1 &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div> 1190 &nbsp;&nbsp;&nbsp;&nbsp;
                59 &nbsp;&nbsp;&nbsp;&nbsp;
                Private &nbsp;&nbsp;&nbsp;&nbsp;
                Yr11 &nbsp;&nbsp;&nbsp;&nbsp;
                86286.36 &nbsp;&nbsp;&nbsp;&nbsp;
                0 &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div> 1847 &nbsp;&nbsp;&nbsp;&nbsp;
                27 &nbsp;&nbsp;&nbsp;&nbsp;
                Private &nbsp;&nbsp;&nbsp;&nbsp;
                College &nbsp;&nbsp;&nbsp;&nbsp;
                121520.87 &nbsp;&nbsp;&nbsp;&nbsp;
                0 &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div> 1217 &nbsp;&nbsp;&nbsp;&nbsp;
                38 &nbsp;&nbsp;&nbsp;&nbsp;
                Private &nbsp;&nbsp;&nbsp;&nbsp;
                Bachelor &nbsp;&nbsp;&nbsp;&nbsp;
                57242.66 &nbsp;&nbsp;&nbsp;&nbsp;
                0 &nbsp;&nbsp;&nbsp;&nbsp;
            </div>

            <div> > range(audit$Age)</div>
            <div> [1] 17 90</div>
            <div> > levels(audit$Employment)</div>
            <div> [1] "Consultant" "Private" "PSFederal" "PSLocal" "PSState"</div>
            <div> [6] "SelfEmp" "Unemployed" "Volunteer"</div>
            <div> > levels(audit$Education)</div>
            <div> [1] "Associate" "Bachelor" "College" "Doctorate"</div>
            <div> [5] "HSgrad" "Master" "Preschool" "Professional"</div>
            <div> [9] "Vocational" "Yr10" "Yr11" "Yr12"</div>
            <div> [13] "Yr1t4" "Yr5t6" "Yr7t8" "Yr9"</div>
            <div> > range(audit$Income)</div>
            <div> [1] 609.72 481259.50</div>
        </el-dialog>

    </div>
</template>

<style>
    a {
        /*去掉导航栏链接文字的下划线*/
        text-decoration: none;
        text-align: center;
    }

    .white-space {
        white-space: pre
    }

    .el-table .cell {
        white-space: pre-line;
    }

    /*样式的使用方式 .+标签 指到对应的标签*/
    .el-menu {
        min-height: 60px;
        max-height: 60px;
    }

    .el-menu-item {
        min-height: 60px;
        max-height: 60px;
        /* 修改默认字体的大小 */
        font-size: 16px;
    }

    /* 两边填充位置的底色和el-menu-item el-menu保持一致 */
    .bg-purple-dark {
        background: #1E89E0;
    }

    .grid-content {
        /* 圆角边框*/
        border-radius: 0px;
        /* 长度和el-menu-item el-menu在缩放的情况下保持一致*/
        min-height: 60px;
    }

    .bg-purple-dark-blank {
        background: Transparent;
    }

    .grid-content-blank {
        border-radius: 0px;
        min-height: 30px;
    }

</style>

<script>
    export default {
        created() {
        },
        data() {
            return {
                irisVisible: false,
                adultVisible: false,
                activeNames: ['0'],
                tableData: [{
                    date: 'JRI/Rserve',
                    name: 'Coding R in java (and vice versa)',
                    address: 'Programmer need to master both R and other languages (e.g. jave).\n' +
                        'JRI requires some environment setting.\n' +
                        'RServe requires a R Serve (based on TCP/IP).\n'
                }, {
                    date: 'DB/files',
                    name: 'Using DB or file as bridge',
                    address: 'easy to understand and use.\n' +
                        'Separable(don’t need to master both R and other languages）.\n' +
                        'Not suitable for large dataset or high-concurrency scenario.\n'
                }, {
                    date: 'Plumber',
                    name: 'create HTTP APIs in R with "Plumber" ',
                    address: 'Probably the best default method for R integration.\n' +
                        'Based on HTTP APIs (might slow down the running performance).\n' +
                        'rich inputs/outputs formats.\n'
                }, {
                    date: 'PMML',
                    name: 'Using PMML(PMML Producer  and Consume)',
                    address: 'fast and stable\n' +
                        'Only suitable for the cases where the integration context is about producing and Consuming predictive and descriptive models.\n'
                }],

                input1: null,
                input2: null,
                input3: null,
                input4: null,
                input5: null,

                input21: null,
                input22: null,
                input23: null,
                input24: null,
                input25: null,
            };
        },
        methods: {

            Prpart() {
                if (!(this.input1) || !(this.input2) || !(this.input3) || !(this.input4)) {
                    this.$message('predictors can not be null');
                    return;
                }
                this.$http.put("RpartTree/iris2", {
                    Sepal_Length: parseFloat(this.input1),
                    Sepal_Width: parseFloat(this.input2),
                    Petal_Length: parseFloat(this.input3),
                    Petal_Width: parseFloat(this.input4)
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        let ret = result.data;
                        this.input5 = ret.irisList[0].species;
                        this.$message('prediction is : ' + this.input5);

                    } else {
                        this.$message('failed！');
                    }
                });
            },
            PGLM() {
                if (!(this.input21) || !(this.input22) || !(this.input23) || !(this.input24)) {
                    this.$message('predictors can not be null');
                    return;
                }
                this.$http.put("GLM/audit2", {
                    Age: parseInt(this.input21),
                    Employment: this.input22,
                    Education: this.input23,
                    Income: parseFloat(this.input24)
                }).then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        let ret = result.data;
                        this.input25 = ret.auditList[0].target_Adjusted;
                        this.$message('prediction is : ' + this.input25);

                    } else {
                        this.$message('failed！');
                    }
                });


            },
            handleChange(val) {
                console.log(val);
            },


        }
    };
</script>