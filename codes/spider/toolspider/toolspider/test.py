import pymysql
import time

# coding=utf-8
#username=input('输入数据库用户名:')
#password=input('输入数据库密码:')
# 创建连接
conn=pymysql.connect(host='idevtools.cn', port=3306, user='idt', passwd='idt!@zkdsz', db='idevtools_dev', charset='utf8')

cursor = conn.cursor()

# 关闭游标
cursor.close()
# 关闭连接
conn.close()

input('运行结束')
