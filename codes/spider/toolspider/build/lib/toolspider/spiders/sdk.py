# -*- coding: utf-8 -*-
import scrapy
from scrapy.linkextractors import LinkExtractor
from scrapy.spiders import CrawlSpider, Rule
#from scrapy.contrib.loader import ItemLoader
from toolspider.items import ToolItem,ToolLoader
from toolspider import pipelines
import pymysql
from toolspider.util import conf
#from scrapy_redis.spiders import RedisCrawlSpider

class SdkSpider(CrawlSpider):
    name = 'sdk'

    #创建数据库链接读取配置文件
    conn=pymysql.connect(host='idevtools.cn', port=3306, user='idt', passwd='idt!@zkdsz', db='idevtools_dev', charset='utf8')
    cursor=conn.cursor()
    #执行sql
    cursor.execute("select * from spider_conf_t where name = 'sdk'")
    conn.commit()
    #取得结果,结果为元组形式
    conf=cursor.fetchone()
    #关闭
    cursor.close()
    conn.close()
    #起始页面
    start_urls =[conf[1]]
    #数据库的字符串为str，要用loads解析为json
    #解析数据库的字符串时注意要把单引号改成双引号否则会报错custom_settings=json.loads(result[4].replace("'","\""))
    #也可以用eval直接将str转为dic
    #custom_settings = eval(conf[4])
    #rules=eval("{'rule':%s}" % (conf[2]))['rule']
    rules = (Rule(LinkExtractor(allow='/datas/[0-9]+',restrict_xpaths='//*[@id="data-index"]/div[3]'),callback='parse_item', follow=True),
 Rule(LinkExtractor(restrict_xpaths='//*[@id="data-index"]/div[4]/ul/li[8]/a')));


    def parse_item(self, response):
        loader=ToolLoader(item=ToolItem(),response=response)
        loader.add_xpath('offical_website',self.conf[5])
        loader.add_value('source',response.url)
        loader.add_xpath('tool_name',self.conf[6])
        loader.add_xpath('description',self.conf[7])
        loader.add_xpath('tag',self.conf[8])

        Tool=loader.load_item()
        #print(f'{Tool}')
        yield Tool
