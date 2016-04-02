ORM 数据库框架 ActiveAndroid 的特点和优势

	ORM (Object Relational Mapping) 框架采用元数据来描述对象与关系映射细节。
	把对象持久化到数据库中
	就是利用 JAVA 的反射机制把对象和数据库记录映射关联起来

    ·基于 ORM 关系操作数据库
    ·方便配置
    ·几乎不需要编写任何 SQL 语句就能保存和检索 SQLite 数据库记录
    ·每一个操作都封装为一个类
    ·对象形式存取数据

/**
 
 */

基本用法:

    ·配置:
    	配置 AndroidManifest 的 application 的 name属性，同时在 meta-data 标签中可选的配置 db 的 name 和 version；
    	在自己的 Application 类中继承 ActiveAndroid 的 Application









































