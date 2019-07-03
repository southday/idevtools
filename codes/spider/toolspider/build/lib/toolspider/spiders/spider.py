import scrapy
from scrapy.contrib.loader import ItemLoader
from toolspider.items import ToolspiderItem
import json
import re
import pymysql

class ToolSpider(scrapy.Spider):
    name="toolspider"

    #开始的url列表
    FILE_URL_START_LIST='url_start_list.txt'
    #以http开头的URL
    FILE_URL_BEGINWITH_HTTP='url_beginwith_http.txt'

    #with open(FILE_URL_START_LIST,"r",encoding='utf-8') as f_url_start_list:
    #    urls=f_url_start_list.readlines()
    start_urls=["https://dl.xunlei.com/"]
    #先将start_urls的内容插入数据库
    # db=pymysql.connect("localhost","root","wqk123","devtool",charset='utf8')
    # cursor=db.cursor()
    # for url in start_urls:
    #     sql='insert into link(`href`,`text`,`sourceid`,`level`) values(\'{0}\',\'{1}\',0,0)'.format(url,'初始')
    #     try:
    #         cursor.execute(sql)
    #         db.commit()
    #     except:
    #         db.rollback()
    # db.close()


    def parse(self, response):
        #test=response.xpath('//div[@class="main-content"]/div[@class="para" and @label-module="para"]/text()').extract()
        #l=ItemLoader(item=ToolspiderItem(),response=response)
        #l.add_xpath('name','//div[@class="main-content"]/div[@class="anchor-list"]/a[@class="lemma-anchor "][2]/@name')
        #l.add_xpath('content','//div[@class="main-content"]/div[@class="para" and @label-module="para"]/text()')
        #items=l.load_item()
        #print(test)
        #获取所有a节点
        level=1
        source=response.url
        node_list=response.xpath('//a')
        #对于每个节点,获取其href与text,随后返回,交给pipelines处理
        for node in node_list:
            item=ItemLoader(item=ToolspiderItem(),response=response)
            href=node.xpath('./@href').extract()
            #如果是/开头 则拼接到url后方
            if(len(href)>0):
                # if(re.match("/[a-zA-Z0-9]+.*",href[0])):
                #     href=response.url+href[0]
                # else:
                href=href[0]
                text=node.xpath('./text()').extract()
                item.add_value('href',href)
                item.add_value('text',text)
                item.add_value('level',level)
                item.add_value('source',source)
                yield item.load_item()


        # for u in node_list.xpath('./@href').extract():
        #     print(u)
        #     yield response.follow(u,callback=self.parse2)


    def parse2(self, response):
        node_list=response.xpath('//a')
        level=2
        source=response.url
        for node in node_list:
            item=ItemLoader(item=ToolspiderItem(),response=response)
            href=node.xpath('./@href').extract()
            if(len(href)>0):
                href=href[0]
                text=node.xpath('./text()').extract()
                item.add_value('href',href)
                item.add_value('text',text)
                item.add_value('level',level)
                item.add_value('source',source)
                yield item.load_item()
        # for u in node_list.xpath('./@href').extract():
        #     print(u)
        #     yield response.follow(u,callback=self.parse3)

    def parse3(self, response):
        node_list=response.xpath('//a')
        level=3
        source=response.url
        for node in node_list:
            item=ItemLoader(item=ToolspiderItem(),response=response)
            href=node.xpath('./@href').extract()
            if(len(href)>0):
                href=href[0]
                text=node.xpath('./text()').extract()
                item.add_value('href',href)
                item.add_value('text',text)
                item.add_value('level',level)
                item.add_value('source',source)
                yield item.load_item()


        # if self.deep<0 and self.enableopenfile:
        #
        #     print('open file'+ response.url)
        # #获取的以http开头的href保存在url_beginwith_http.txt文件中,对这里面的链接递归调用此方法
        #     with open(self.FILE_URL_BEGINWITH_HTTP,"r",encoding='utf-8') as f_url_beginwith_http:
        #         url_beginwith_http=f_url_beginwith_http.readlines()
        # #只获取从pos位置到文件末尾的url
        #
        #
        #     urls_beginwith_http=[url.strip() for url in url_beginwith_http]
        #
        #
        #
        #     self.enableopenfile=False
        #
        #     print('长度'+str(self.pos))
        #
        #
        #
        #     for url in urls_beginwith_http[self.pos:]:
        #     #response.follow可以直接处理相对路径
        #         print('yield链接:'+url)
        #         yield scrapy.Request(url,self.parse)
        #     self.deep+=1
        #
        #
        #     self.enableopenfile=True
        #
        #     self.pos+=len(url_beginwith_http)
        #
        #     print("文件大小"+str(self.pos))
