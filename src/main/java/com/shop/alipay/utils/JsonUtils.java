package com.shop.alipay.utils;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shop.alipay.enums.Constants;
import com.shop.alipay.model.AlipassModel;

import java.io.IOException;
import java.io.StringWriter;

/**
 * 提供用于Json转换的工具类
 * @author siyu.jsy
 * @version $Id: JsonUtil.java,v 0.1 2013-5-3 下午11:05:27 siyu.jsy Exp $
 */
public final class JsonUtils {

    /**
     * 将Object对象转换为Json字符
     * @param obj
     * @return
     * @throws IOException 
     */
    public static String toJson(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();

        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        mapper.writeValue(gen, obj);
        gen.close();

        String json = sw.toString();
        return json;
    }

    /**
     * 替换passData中的固定字符串
     * @param passData
     * @return
     */
    public static String replace(String passData) {
        passData = StringUtil.replace(passData, Constants.APPMESSAGE.getValue(),
            Constants.MESSAGE.getValue());

        passData = StringUtil.replace(passData, Constants.TEXTMESSAGE.getValue(),
            Constants.MESSAGE.getValue());

        passData = StringUtil.replace(passData, Constants.IMGMESSAGE.getValue(),
            Constants.MESSAGE.getValue());
        return passData;
    }

    /**
     * Json to AlipassModel
     * 如果json数据为空或则不合法，则会返回null
     * 反之，返回正常AlipassModel对象
     * @param json
     * @return
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public static AlipassModel jsonToObj(String json) throws JsonParseException,
                                                     JsonMappingException, IOException {
        if (StringUtil.isBlank(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper().configure(
            JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        // 对json进行预处理，主要是针对operation中的mesage信息进行处理
        json = handleJsonMessage(json, mapper);

        //删除掉locations节点
        json = removeLocations(json, mapper);

        return mapper.readValue(json, AlipassModel.class);
    }

    /**
     * 处理json中的特殊数据。目前主要是针对message的类型，message对应4种类型，需要分别处理
     * @param json
     * @param mapper 
     * @return
     * @throws IOException 
     * @throws JsonProcessingException 
     */
    private static String handleJsonMessage(String json, ObjectMapper mapper)
                                                                             throws JsonProcessingException,
                                                                             IOException {
        // 不包含复杂message数据，直接返回即可。
        if (!StringUtil.contains(json, Constants.JSONNODE_FORMAT_KEY_VALUE_APP.getValue())
            && !StringUtil.contains(json, Constants.JSONNODE_FORMAT_KEY_VALUE_TEXT.getValue())
            && !StringUtil.contains(json, Constants.JSONNODE_FORMAT_KEY_VALUE_IMG.getValue())) {
            return json;
        } else {
            // 包含复杂message数据
            JsonNode nodes = mapper.readTree(json);
            JsonNode evoucherInfo = nodes.get(Constants.JSONNODE_EVOUCHERINFO.getValue());
            if (evoucherInfo != null) {
                String evoucherInfoStr = evoucherInfo.toString();
                JsonNode childNodes = mapper.readTree(evoucherInfoStr);

                // opeartion是一个数组
                JsonNode operations = childNodes.get(Constants.JSONNODE_OPERATION.getValue());
                // opeartion数据
                String operationsJson = operations.toString();
                // 移除原有operation数据
                json = ExtendStringUtil.remove(json, operationsJson);

                // 新的operation数据
                StringBuilder sbd = new StringBuilder();
                sbd.append("[");
                for (JsonNode jsonNode : operations) {
                    String jsonNodeStr = jsonNode.toString();
                    JsonNode messageNode = mapper.readTree(jsonNodeStr);

                    JsonNode format = messageNode.get(Constants.JSONNODE_FORMAT.getValue());
                    if (StringUtil.equals(format.toString(),
                        Constants.JSONNODE_FORMAT_VALUE_APP.getValue())) {
                        jsonNodeStr = jsonNodeStr.replace(
                            Constants.JSONNODE_MESSAGE_VALUE_MESSAGE.getValue(),
                            Constants.JSONNODE_MESSAGE_VALUE_APPMESSAGE.getValue());
                    } else if (StringUtil.equals(format.toString(),
                        Constants.JSONNODE_FORMAT_VALUE_TEXT.getValue())) {
                        jsonNodeStr = jsonNodeStr.replace(
                            Constants.JSONNODE_MESSAGE_VALUE_MESSAGE.getValue(),
                            Constants.JSONNODE_MESSAGE_VALUE_TEXTMESSAGE.getValue());
                    } else if (StringUtil.equals(format.toString(),
                        Constants.JSONNODE_FORMAT_VALUE_IMG.getValue())) {
                        jsonNodeStr = jsonNodeStr.replace(
                            Constants.JSONNODE_MESSAGE_VALUE_MESSAGE.getValue(),
                            Constants.JSONNODE_MESSAGE_VALUE_IMGMESSAGE.getValue());
                    }

                    sbd.append(jsonNodeStr).append(",");
                }
                String newOperationStr = sbd.substring(0, sbd.length() - 1) + "]";

                // 把替换后的sbd数据放入json
                int operationLocations = StringUtil.indexOf(json,
                    Constants.JSONNODE_OPERATION_VALUE.getValue());
                String newJson = new StringBuilder().
                // operation之前数据
                    append(StringUtil.substring(json, 0, operationLocations))
                    // operation 标签
                    .append(Constants.JSONNODE_OPERATION_VALUE.getValue())
                    // operation数据
                    .append(newOperationStr)
                    // operation之后数据
                    .append(
                        StringUtil.substring(
                            json,
                            operationLocations
                                    + ExtendStringUtil.length(Constants.JSONNODE_OPERATION_VALUE
                                        .getValue()))).toString();

                // 返回处理后数据
                return newJson;
            }
        }

        // 默认返回原数据
        return json;
    }

    /**
     * 删除掉locations节点，如果不存在cityId时
     * 
     * @param json      pass.json内容字串
     * @param mapper    json解析器
     * @return  处理locations节点后的json字串
     */
    private static String removeLocations(String json, ObjectMapper mapper) {
        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode evoucherInfo = rootNode.get(Constants.JSONNODE_EVOUCHERINFO.getValue());
            if (evoucherInfo != null) {
                if (evoucherInfo.has(Constants.JSONNODE_LOCATIONS.getValue())) {//含有locations节点
                    JsonNode locations = evoucherInfo.get(Constants.JSONNODE_LOCATIONS.getValue());
                    JsonNode location = locations.get(0);
                    if (null == location.get("cityId")) {
                        ((ObjectNode) evoucherInfo).remove(Constants.JSONNODE_LOCATIONS.getValue());
                    }
                }
            }
            return rootNode.toString();
        } catch (Exception e) {
            return json;
        }
    }
}
