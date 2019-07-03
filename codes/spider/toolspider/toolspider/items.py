# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy
from scrapy.contrib.loader.processor import Join, Compose, TakeFirst,MapCompose
from scrapy.contrib.loader import ItemLoader
#测试
class ToolspiderItem(scrapy.Item):
    #链接
    href = scrapy.Field(
        output_processor = TakeFirst()
    )
    #链接上的字
    text=scrapy.Field(
        output_processor = TakeFirst()
    )
    #层级
    level=scrapy.Field(
        output_processor = TakeFirst()
    )
    #链接源
    source=scrapy.Field(
        output_processor = TakeFirst()
    )

#工具实体
class ToolItem(scrapy.Item):
    #站点名称
    offical_website=scrapy.Field(
        output_processor=TakeFirst()
    )
    #工具名称
    tool_name=scrapy.Field(
        output_processor=TakeFirst()
    )
    #工具描述
    description=scrapy.Field(
        output_processor=TakeFirst()
    )
    #工具标签
    tag=scrapy.Field()
    #信息来源
    source=scrapy.Field(
        output_processor=TakeFirst()
    )

class NewLoader(ItemLoader):
    #default_output_processor = TakeFirst()
    pass

class ToolLoader(NewLoader):
    #指定tool_name and description的输出形式
    tool_name_out=Compose(Join(),lambda s:s.strip())
    description_out=Compose(Join(),lambda s:s.strip())
    tag_out=Compose(Join(),lambda s:s.strip())
