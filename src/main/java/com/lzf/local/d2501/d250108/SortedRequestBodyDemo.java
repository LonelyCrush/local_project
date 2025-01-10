package com.lzf.local.d2501.d250108;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leizefeng
 */
public class SortedRequestBodyDemo {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public SortedRequestBodyDemo() {
    objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
  }

  public static void main(String[] args) throws JsonProcessingException {
    // mock data json string
    SortedRequestBodyDemo thisClass = new SortedRequestBodyDemo();
    Map<String, Object> dataMap = new HashMap<>();
    dataMap.put("name", "leizefeng");
    dataMap.put("age", 25);
    dataMap.put("address", "beijing");
    Map<String, Object> levelOneMap = new HashMap<>();
    levelOneMap.put("levelOneArray", thisClass.levelOneArray());
    levelOneMap.put("levelOneValue", thisClass.levelOneValue());
    levelOneMap.put("levelTwoMap", thisClass.levelTwoMap());
    dataMap.put("levelOneMap", levelOneMap);
    String data = new ObjectMapper().writeValueAsString(dataMap);
    System.out.println(data);

    // sorted
    System.out.println();
    System.out.println(thisClass.sortedData(data));
  }

  private String sortedData(String data) {
    try {
      JsonNode jsonNode = objectMapper.readTree(data);
      if (jsonNode.isArray()) {
        List<String> list = new ArrayList<>();
        for (JsonNode node : jsonNode) {
          list.add(sortedData(node.toString()));
        }
        return list.toString();
      } else if (jsonNode.isValueNode()) {
        return jsonNode.toString();
      } else {
        Map<String, Object> objectMap =
            objectMapper.readValue(data, new TypeReference<Map<String, Object>>() {});
        return objectMapper.writeValueAsString(objectMap);
      }
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Invalid json data: " + data, e);
    }
  }

  private int[] levelOneArray() {
    return new int[]{33, 12, 56, 23, 78, 45, 99, 67, 89, 101};
  }

  private String levelOneValue() {
    return "levelOneValue";
  }

  private int[] levelTwoArray() {
    return new int[]{45, 23, 12, 67, 99, 89, 56, 33, 78, 101};
  }

  private String levelTwoValue() {
    return "levelTwoValue";
  }

  private Map<String, Object> levelTwoMap() {
    SortedRequestBodyDemo thisClass = new SortedRequestBodyDemo();
    Map<String, Object> leveltwoMap = new HashMap<>();
    leveltwoMap.put("levelTwoArray", thisClass.levelTwoArray());
    leveltwoMap.put("levelTwoValue", thisClass.levelTwoValue());
    leveltwoMap.put("levelThreeMap", null);
    return leveltwoMap;
  }
}
