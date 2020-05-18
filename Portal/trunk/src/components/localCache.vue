<template>
    <div>
        <!--搜索框 layout(基础的24分栏，迅速简便地创建布局)这里为两个分栏,间隔未2,分别占11/24,1/24-->
        <el-row :gutter="2">
            <!--左边-->

            <el-col :span="5" class="grid">
                <el-row>
                    <el-tree
                            :data="treeData"
                            default-expand-all
                            :props="defaultProps"
                            ref="tree"
                            @node-click="handleNodeClick">
                    </el-tree>
                </el-row>
            </el-col>

            <!--右边-->

            <el-col :span="15" class="grid">
                <el-row>
                    <el-input
                            type="textarea"
                            :rows="26"
                            placeholder="请输入内容"
                            v-model="textArea2">
                    </el-input>
                </el-row>
                <el-row style="margin: 8px">
                    <el-tooltip placement="top" effect="light">
                        <div slot="content">
                            reload:可简单理解为清掉缓存,重新加载数据库数据到缓存<br/>
                            reload会对选中的根节点的每一个1/2级key，保留缓存中现有的key结构，清掉每组key的value，然后调用写好的加载方法重新加载value<br/>
                            比如说:写好加载方法是:全量加载用户表数据到缓存，1级key叫做all_person_with_id,2级key是id.<br/>
                            现在id为101的条记录的用户名是gust,缓存展示则为:根节点名-all_person_with_id-101-value,可见value中用户名是gust.<br/>
                            这时手动修改数据库值，使得gust为gust1，执行reload，缓存中值会变成gust1
                        </div>
                        <el-button round @click.prevent="handleRefresh()">Reload</el-button>
                    </el-tooltip>
                    <el-tooltip placement="top" effect="light">
                        <div slot="content">
                            Clean:可简单理解为清掉缓存<br/>
                            Clean会对选中的根节点的每一个1/2级key，保留缓存中现有的key结构，清掉每组key的value<br/>
                            比如说:写好加载方法是:全量加载用户表数据到缓存，1级key叫做all_person_with_id,2级key是id.<br/>
                            现在id为101的条记录的用户名是gust,缓存展示则为:根节点名-all_person_with_id-101-value,可见value中用户名是gust.<br/>
                            这时Clean，1/2级节点结构仍然，value会被清空
                        </div>
                        <el-button round @click.prevent="handleDeleted()">Clean</el-button>
                    </el-tooltip>
                    <el-tooltip placement="top" effect="light">
                        <div slot="content">
                            Save:可简单理解为保存界面修改到当前缓存<br/>
                            Save指的是手动在界面上修改某个value的值，然后Save，会把修改后的值保存在当前缓存内<br/>
                            比如说:当前缓存有值,用户名为gust,界面手动修改gust为gust2<br/>
                            点击Save,当前缓存内对应的gust位置的值会被改成gust2<br/>
                            !注意修改后的value不要改变了原value的结构，比如说删掉了一些中括号大括号，自己新增一个没有的字段属性等等<br/>
                            !虽然不会改变数据库的值，但还是建议谨慎操作……
                        </div>
                        <el-button round @click.prevent="handleSave()">Save</el-button>
                    </el-tooltip>
                    <el-tooltip placement="top" effect="light">
                        <div slot="content">
                            即对所有的根节点执行reload<br/>
                            暂时还没写<br/>
                        </div>
                        <el-button round @click.prevent="handleRefreshAll()">Reload All</el-button>
                    </el-tooltip>
                    <el-tooltip placement="top" effect="light">
                        <div slot="content">
                            即对所有的根节点执行Clean<br/>
                            暂时还没写<br/>
                        </div>
                        <el-button round @click.prevent="handleDeletedAll()">Clean All</el-button>
                    </el-tooltip>
                </el-row>
            </el-col>
        </el-row>
        <br>
    </div>
</template>
<style>
</style>
<script>
    export default {
        created() {
            this.initTree();
        },
        data() {
            return {
                treeData: [{
                    label: 'key1',
                    level: 1,
                    children: [{
                        parentLabel: 'key1',
                        label: 'key1-1',
                        level: 2,
                        cacheValue: 'cacheValue1-1-cacheValue1-1-cacheValue1-1-cacheValue1-1-cacheValue1-1-cacheValue1-1-cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1cacheValue1-1'
                    }]
                }, {
                    label: 'key2',
                    level: 1,
                    children: [{
                        parentLabel: 'key2',
                        label: 'key2-1',
                        level: 2,
                        cacheValue: 'cacheValue2-1cacheValue2-1cacheValue2-1cacheValue2-1cacheValue2-1cacheValue2-1cacheValue2-1cacheValue2-1'
                    }, {
                        parentLabel: 'key2',
                        label: 'key2-2',
                        level: 2,
                        cacheValue: 'cacheValue2-2'
                    }]
                }, {
                    label: 'key3',
                    level: 1,
                    children: [{
                        parentLabel: 'key3',
                        label: 'key3-1',
                        level: 2,
                        cacheValue: 'cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1cacheValue3-1'
                    }, {
                        parentLabel: 'key3',
                        label: 'key3-2',
                        level: 2,
                        cacheValue: 'cacheValue3-2'
                    }]
                }],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                textArea2: ''
            };
        },
        methods: {
            initTree() {
                this.textArea2 = '';
                this.$http.put("localCache/getCacheInfo").then(result => {
                    var result = result.body;
                    if (result.code === 200) {
                        this.treeData = result.data;
                    } else {
                        // 失败了
                        alert("服务器异常!");
                    }
                });
            },
            handleNodeClick(data) {
                if (data.level != 2) {
                    console.log('not level 2 node!');
                    return;
                }
                else {
                    this.textArea2 = data.cacheValue;
                }
            },
            handleRefresh() {
                let currentTreeData = this.$refs.tree.getCurrentNode();
                if (currentTreeData.level == 1) {
                    this.$message('not this node(Key level 1)!');
                    return;
                }
                else if (currentTreeData.level == 0) {
                    this.$http.put("localCache/handleRefresh", {
                        beanMapName: currentTreeData.label,
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.initTree();
                            alert("Reload OK!");
                        } else {
                            // 失败了
                            alert("服务器异常!");
                        }
                    });
                }
                else {
                    this.$http.put("localCache/handleRefresh", {
                        beanMapName: currentTreeData.beanMapName,
                        parentLabel: currentTreeData.parentLabel,
                        label: currentTreeData.label
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.initTree();
                            alert("Reload OK!");
                        } else {
                            // 失败了
                            alert("服务器异常!");
                        }
                    });
                }
            },
            handleDeleted() {
                let currentTreeData = this.$refs.tree.getCurrentNode();
                if (currentTreeData.level == 1) {
                    this.$message('not this node(Key level 1)!');
                    return;
                }
                else if (currentTreeData.level == 0) {
                    this.$http.put("localCache/handleClean", {
                        beanMapName: currentTreeData.label,
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.initTree();
                            alert("Clean OK!");
                        } else {
                            // 失败了
                            alert("服务器异常!");
                        }
                    });
                }
                else {
                    this.$http.put("localCache/handleClean", {
                        beanMapName: currentTreeData.beanMapName,
                        parentLabel: currentTreeData.parentLabel,
                        label: currentTreeData.label
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.initTree();
                            alert("Clean OK!");
                        } else {
                            // 失败了
                            alert("服务器异常!");
                        }
                    });
                }
            },
            handleSave() {
                let currentTreeData = this.$refs.tree.getCurrentNode();
                if (currentTreeData.level == 1) {
                    this.$message('not this node(Key level 1)!');
                    return;
                }
                else if (currentTreeData.level == 0) {
                    this.$message('not this node(Key level 0)!');
                    return;
                }
                else {
                    this.$http.put("localCache/handleSave", {
                        beanMapName: currentTreeData.beanMapName,
                        parentLabel: currentTreeData.parentLabel,
                        label: currentTreeData.label,
                        cacheValue: this.textArea2
                    }).then(result => {
                        var result = result.body;
                        if (result.code === 200) {
                            this.initTree();
                            alert("Save OK!");
                        } else {
                            // 失败了
                            alert("服务器异常!");
                        }
                    });
                }
            },
            handleRefreshAll() {
                alert("尚未实现!");
            },
            handleDeletedAll() {
                alert("尚未实现!");
            }
        }
    };
</script>