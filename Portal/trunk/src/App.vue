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
                                <h2>Integration between R and other languages</h2>
                            </template>
                            <div>welcome to here~</div>
                            <div>This is a website to show JianTang's MTHM007 （Engaging With Research） term2 project.
                            </div>
                            <div>The topic is "integration between R and other languages"</div>
                            <div>There are examples (click the "Examples" in the bottom) and Github link (click the
                                "gitHub Link" above)
                            </div>
                        </el-collapse-item>

                        <el-collapse-item name="1">
                            <template slot="title">
                                <h2>Abstract</h2>
                            </template>
                            <div>R is very powerful for data analysis and visualization, but in software engineering
                                other languages such
                                as c++ and java maybe more popular.
                            </div>
                            <div>Image the case where we want to use R to analyze our data, and use the analysis results
                                (suck as ggplot2 graph, a predictive model defined in R) in our c++ /java coded
                                application (this website is exactly such a case), the
                                integration between R and other languages will be inevitable.
                            </div>
                            <div>
                                Our goal here is to find ways that can implement this integration. More greedy speaking,
                                we want they fast, stable, easy to understand and use.
                            </div>
                            <div>In this page I will introduce several ways for implementing this integration
                                (between R&java mostly), how they work,what's the requirements, merits and demerits.
                            </div>
                            <div>What's more, this website was built by using pmml, there will be an
                                operable area (bottom) to show how it works.
                            </div>
                            <div>Finally, there will be a summary (in the bottom), as a cheat sheet in case u need to
                                choose an integration method.
                            </div>
                        </el-collapse-item>

                        <el-collapse-item name="2">
                            <template slot="title">
                                <h2>Optional methods</h2>
                            </template>
                            <div><h3>1. Using R in java (and vice versa)</h3></div>
                            <div><h3>2. Using DB or file as bridge</h3></div>
                            <div><h3>3. Using HTTP API in R</h3></div>
                            <div><h3>4. Using PMML</h3></div>
                        </el-collapse-item>

                        <el-collapse-item name="3">
                            <template slot="title">
                                <h2>1. Using R in java (and vice versa)</h2>
                            </template>
                            <div>This means coding R code in you Java project, or coding java code in you R project.
                                We can implement this by using “JRI” or “RServe”.
                            </div>
                            <div> “JRI” and “RServe” are well used in
                                many R-java integrated project. However, I don’t really recommend to
                                use them because programmers have to master both R and Java, I think this
                                is too much to ask.
                            </div>
                            <div><h3>JRI:</h3></div>
                            <div><h4>What is JRI? </h4>
                                JRI is really old, it is a Java/R Interface, which allows to run R inside Java
                                applications as a single thread. Basically it loads R dynamic library into Java and
                                provides a Java API to R functionality. It supports both simple calls to R functions and
                                a full running REPL(Read–eval–print loop). It is the inverse of rJava (where u can run
                                java code in R project).
                            </div>
                            <div><h4>Cons:</h4> you have to configure some environment variables and libraries,
                                different in Win/UNIX.
                            </div>
                            <div><h4>Pros:</h4> better performance than “RServe”(see below)</div>
                            <div><h3>RServe:</h3></div>
                            <div><h4>What is RServe?</h4> What is RServe? Rserve is a TCP/IP server which allows other
                                programs to use facilities of R from various languages (such as C/C++, PHP and Java)
                                without the need to initialize R or link against R library. In another word, for example
                                in java, u can write R code in your java project, send it to Rserve, and get the R code
                                outputs back.
                            </div>
                            <div><h4>Cons:</h4> using RServe means u need to build a client/server system, and it’s
                                based on TCP/IP, slower than JRI (more time will be spent in network communication),
                            </div>
                            <div><h4>Pros:</h4> easy to setup, you don't need to initialize R or link against any R
                                library, can run in a different machine.
                            </div>
                        </el-collapse-item>

                        <el-collapse-item name="4">
                            <template slot="title">
                                <h2>2. Using DB or file as bridge</h2>
                            </template>
                            <div>DB here means database, such as mySQL or Oracle. There is really no more terms to
                                explain. This way is so easy to understand: we can just save the R results to DB or
                                local files, and then read them into other applications.
                            </div>
                            <div>Using files as bridge seems to be the simplest way for integration, it’s suitable for
                                small size data. However, the performance and stability can be the problems when facing
                                with large size data or high-concurrency scenario.
                            </div>
                            <div><h4>Pros:</h4> easy to understand and use. What’s more, this integration method
                                is separable, by separable it means the job can be divided into two parts (R and java),
                                the programmers in each part don’t have to master both R and Java. Different parts can
                                be done by different programmers and then combined through DB or local files.
                            </div>
                            <div><h4>Cons:</h4> if the dataset is large, too much I/O operations will be unacceptable,
                                it will slow down the running performance, more memories are required for saving, and
                                more likely to lead to bugs in high-concurrency scenario.
                            </div>
                        </el-collapse-item>

                        <el-collapse-item name="5">
                            <template slot="title">
                                <h2>3. Using HTTP API in R</h2>
                            </template>
                            <div>HTTP(REST) API has become a common way for allowing various systems to interact with
                                one another. We can create HTTP APIs in R with "Plumber", then other systems can use our
                                R code by using these APIs.
                            </div>
                            <div>HTTP API is so popular hence i believe Plumber should be the best default method for R
                                integration. It can be used to provide rich R inputs/outputs formats such as Jason
                                strings, HTML, PNG, or JPEG. But in some special cases PMML(see below) will do better.
                            </div>
                            <div><h4>What is Plumber? </h4>
                                The plumber R package (Trestle Technology, LLC 2017) allows users to expose existing R
                                code as a service available to others on the Web. (Note that in Rserve we send raw R
                                code to a server and get response, but here we send API parameters, which means users
                                don’t need master R)We can do this by merely decorating our existing R code with special
                                annotations (annotations, amazing!). The API will use port 8000(default) and will
                                continue running in your R session until you press the Esc key.
                            </div>
                            <div><h4>Pros:</h4> HTTP APIs are so popular hence this might be the most acceptable way for
                                integration. It is easy to understand and use (as long as users are familiar with the
                                API), users don’t need to master both R and other languages, rich inputs/outputs formats
                                such as Jason strings, HTML, PNG, or JPEG.
                            </div>
                            <div><h4>Cons:</h4> using too much REST API will slow down the running performance (more
                                time will be spent in network communication), This is not a good choice for
                                time-sensitive systems. Plus we need to maintain this R service.
                            </div>
                        </el-collapse-item>

                        <el-collapse-item name="6">
                            <template slot="title">
                                <h2>4. Using PMML</h2>
                            </template>
                            <div>First I want to state the integration between R&java behind this website is done by
                                using PMML.
                            </div>
                            <div>In the case where we want build predictive and descriptive models (such as GLM,
                                Hcluster, SVM random forest, nnet…) in R and use these models in other language coded
                                system, we can use PMML as bridge.
                            </div>
                            <div>If the integration context is about producing and Consuming predictive and descriptive
                                models, and they are supported by PMLL, then PMLL is a safer and more efficient way.
                            </div>
                            <div><h4>What is PMML? </h4>
                                The goal of PMML is to encapsulate in an application and system independent fashion a
                                model so that two different applications (the PMML Producer, such as R or Python, and
                                Consumer, such as Java and C++) can use it.
                            </div>
                            <div>The PMML package in R (the PMML Producer) exports a variety of predictive and
                                descriptive models from R to the Predictive Model Markup Language. And JPMML (the PMML
                                Consumer) is one of the Java libraries for consuming these PMML documents
                            </div>
                            <div><h4>Pros:</h4> inner system, pure Java /C++, there is not network communication or
                                R/python code running, so it is fast and stable when facing with large size data or
                                high-concurrency scenario.
                            </div>
                            <div><h4>Cons:</h4>pmml doesn’t support exporting everything (it supports predictive and
                                descriptive models, for more detail please see the PMML doc). For example If we can’t
                                export ggplot graphs.
                            </div>
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


                        <!--                        example-->
                        <el-collapse-item name="8">
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