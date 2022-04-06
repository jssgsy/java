#!/usr/bin/env bash
#4232278 4220938 4219551 4219307 4217325
for i in
do
    curl -XPOST -d "{\"pages\":[{\"path\": \"pages/bbs/detail/index\", \"query\":\"id=${i}\" }]}" https://api.weixin.qq.com/wxa/search/wxaapi_submitpages?access_token=38_W8XJtQxYqDAkVv_g4yy0Wp4rr73Kx7R6DvEkivgg25J_YM-HCQAH6lCf_pR0dwqB_ACbJaB1HpG2_2N6I50Zp18pQi9oB_5RmPlVFudm7-_bgXYHA_6A9s8iSyMBTHKMfjeh_wgygQtEu81CKMMbADANPP
done