# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://doc.scrapy.org/en/latest/topics/item-pipeline.html
import json
import re
import pymysql




class ToolItemPipline(object):
    def __init__(self):
       self.conn=pymysql.connect(host='idevtools.cn', port=3306, user='idt', passwd='idt!@zkdsz', db='idevtools_dev', charset='utf8')
       self.cursor=self.conn.cursor(pymysql.cursors.DictCursor)


    def process_item(self, item, spider):
        #参数顺序：标签组，工具名，来源，官网，描述
       self.cursor.callproc('PROC_INSERT_TOOL',args=(item['tag'],item['tool_name'],item['source'],item['offical_website'],item['description']))
       self.conn.commit()
       print('save into mysql')
       return item

    def close_spider(self):
       self.cursor.close()
       self.conn.close()
