# iReciteWord开发文档  
## 数据库  
### 框架  
DBFlow  
### vocabulary.db  
包含两张表，分别为四级和六级词汇书  
### 层次说明  
#### Dao  
数据库提供的接口  
调用接口示例：  
<pre>
//实现从四级词汇书中随机弹出10个单词
Cet4Dao cet4Dao = getCet4DaoInstance();  
List<Cet4> list = cet4Dao.randomQuery(10);  
</pre>  

#### DaoImpl  
Dao接口实现，非后台开发不用关心  

##### vo  
数据封装层，各项数据对应4张表的各列。  
表结构发生变化时需要更新`vocabulary.db`的版本（在`VocabularyDatabase.java`中使常量`VERSION`的值加一  

### 类与接口描述  
#### Cet4Dao和Cet6Dao  
提供1个查询方法`randomQuery`，用于获取任意个数单词  
传入参数：`count`，需要的单词个数  
返回类型：`List<Cet4>`，List元素类型为`Cet4`，封装了单词的信息  
  
#### LookUpResultDao  
网络查词获得的结果  
提供4个方法  
 **insert：** 接受一个类型为LookUpResult的参数，将查询所得的单词信息插入数据库  
 **queryByHistory：** 接受一个String类型的参数（要查询的单词），返回一个LookUpResult类型的结果。在查询历史中查找相关单词的信息  
 **randomQuery：** 功能类似  
 **deleteAll：** 清除所有查询的历史记录  

#### DaoFactory  
工厂模式  
包含4个静态方法，获取Dao接口前导入静态方法。  
`import static com.hhu.ireciteword.data.DaoFactory.getLookUpResultDaoInstance;`  

#### VocabularyDatabase  
数据库信息，包括数据库名字与版本号  

#### MyApp  
Android应用入口，初始化数据库。不需要做更改。  

## 网络  
### 查词  
#### API  
有道  
[文档](http://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html)  

#### TranslateActivity  
目前没有页面适配  
#### translate包  
包括api调用，加密，解析Json  
仅提供一个public方法`getResult`  
接受参数：word，type（要获取的发音种类，英音或美音）  
返回：返回值类型为LookUpResult，封装了word的相关内容。  

### 每日一句  
#### API  
天行  
[文档](https://www.tianapi.com/apiview/174)  

#### SentenceActivity  
我写了简单的页面，需要调整  
使用比较简单，暂且不表  

## utils  
### 类说明  
#### ParseJSON  
仅用于解析SentenceAPI获得的Json串  
#### VoicePlayer  
包含一个静态方法，用于播放URL中的音频  
使用示例：
<pre>
//url为https协议
playVoice(url);
</pre>  

## Todo  
背单词的流程实现  
单词锁屏  
学习进度  
TranslateActivity页面  
SentenceActivity优化  
打卡页面，只需要一个  
词汇书页面：四六级，生词本。放弃导入词汇书  
设置：有用的设置落实，没用的设置放弃  

 **暂时想到这些**   
