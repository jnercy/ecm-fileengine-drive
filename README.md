###idea 
####gradle模块->build->build执行打包spring-boot可执行jar包



### fastfdfs
#### /etc/init.d
 启动  ./fdfs_trackerd start
 --
 启动  ./fdfs_storaged start
 --


gradle打包

clean -Penv=dev

build -x test -Penv=dev
