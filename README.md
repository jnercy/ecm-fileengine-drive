## drive 地址
* [网盘主页](http://139.196.138.51/#!/drive/my-drive)




## 更新记录
### 2017/6/1更新内容
* 批量移动文件到回收站
* 批量还原回收站文件
* 清空回收站功能
* 批量删除回收站文件功能
* 文件/文件夹重命名功能
* 文件列表查询添加默认排序为修改时间降序

### 2017/6/2更新内容
* 新建并上传文件
* 新建文件夹
* 进去文件夹内目录查看文件
* 修改文件列表接口排序 先根据文件夹排序再根据修改时间排序

### 2017/6/5更新内容
* 文件分片上传接口完成
* 拆分普通上传接口和分块上传接口
* 优化所有查询接口去除不必要的序列化操作

### 2017/6/7更新内容
* 修复pageToken Bug

### 2017/6/8更新内容
* 网盘下载功能实现
* 移动文件功能完成
* 分享功能重构完成 

### 2017/6/9更新内容
* 网盘排序功能完成
* 文件列表接口支持过滤mimetype
* 文件列表接口文件夹优先排序

### 2017/6/12更新内容
* 文件复制功能完成
* 文件夹目录树功能完成













## fastfdfs启动命令
* cd /etc/init.d
* 启动  ./fdfs_trackerd start
* 启动  ./fdfs_storaged start
* fdfs_monitor /etc/fdfs/client.conf

``


```
SELECT
	*
FROM
	transition_file AS a,
	transition AS b
WHERE
	a.globalId = b.globalId
AND a.globalId IN (
	SELECT
		c.globalId
	FROM
		transition_callback AS c
	WHERE
		c.aggregationStatus = 0 
)

```


```
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n -jar ecm-drive-0.0.1.jar 
```

scp root@hk01.rotationss.cn:/usr/local/solo.tar.gz /usr/local
