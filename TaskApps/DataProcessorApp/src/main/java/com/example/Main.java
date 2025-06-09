package com.example;
public  class Main {
    public static void main(String[] args) {

        String jsonData = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\"}";
        String xmlData = "<person><name>John</name><age>30</age><city>New York</city></person>";

        // Example usage of XMLProcessor
        // String xmlData = "<root><element>Value</element></root>";
        DataProcessor xmlProcessor = new XMLProcessor();
        String xmlResult = xmlProcessor.process(xmlData);
        System.out.println("Processed XML:\n" + xmlResult);

        // Example usage of JsonProcessor
        // String jsonData = "{\"key\": \"value\"}";
        DataProcessor jsonProcessor = new JsonProcessor();
        String jsonResult = jsonProcessor.process(jsonData);
        System.out.println("Processed JSON:\n" + jsonResult);
    }
}