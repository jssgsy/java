#!/usr/bin/env bash


# 演示curl在shell script中的基础用法

# 直接显示返回的内容
curl www.baidu.com

# 将返回内容写入文件中
curl -s -o $(dirname $0)/curl_response.txt www.baidu.com

# 将返回结果返回给某个变量(在返回json时有用)
result=$(curl -s https://api.apiopen.top/musicBroadcastingDetails?channelname=public_tuijian_spring)
echo $result
#结合jq使用便能解析其它的某些字段了
result=$(curl -s https://api.apiopen.top/musicBroadcastingDetails?channelname=public_tuijian_spring | jq .code)
echo $result
