package com.ing.casyadapterpoc.common.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Component
@Slf4j
public class WriteExcelFile {

    public void processFile(String path) {

        try (FileInputStream inputStream = new FileInputStream(new File(path))) {
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(0);

            Object[][] bookData = {
                    {"1", "A", "qwe"},
                    {"2", "B", "rrr"},
                    {"3", "C", "vvv"}
            };

            int rowCount = sheet.getLastRowNum();

            for (Object[] book : bookData) {
                Row row = sheet.createRow(++rowCount);

                int columnCount = 0;

                Cell cell = row.createCell(++columnCount);
                cell.setCellValue(rowCount);
                for (Object field : book) {
                    cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }

//                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(path);
                workbook.write(outputStream);

                inputStream.close();
                workbook.close();
                outputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void processFileSecondVersion(Account acc) throws IOException {


        Workbook workbook = new XSSFWorkbook();
        String outputfileName = "newFiless.xls";
        Sheet sheet = workbook.createSheet(outputfileName);

        Row headerRow = sheet.createRow(0);
        addHeadersFromObject(headerRow, Account.class);

        File outputFile = new File("src/test/resources/" + outputfileName);

        for (int i = 1; i < 20; i++) {
            Row row = sheet.createRow(i);
            writeTableModelRow(row, acc);
        }


        FileOutputStream outputStream = new FileOutputStream(outputFile);
        workbook.write(outputStream);

//        writeTableModelHeader(headerRow);
    }


    @SneakyThrows
    public void processJsonToXcel(Account account) throws JSONException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(account);
        ObjectNode node = (ObjectNode) objectMapper.readTree(jsonStr);
//
//        node.fields().forEachRemaining(fieldEntity -> {
//            log.info("key: {}, value: {}", fieldEntity.getKey(), fieldEntity.getValue().toString());
//            // parse each key somehow
//            if (!fieldEntity.getValue().isValueNode()) {
//                try {
//                    ObjectNode nestedNode = (ObjectNode) objectMapper.readTree(fieldEntity.getValue().toString());
//                    nestedNode.fields().forEachRemaining(stringJsonNodeEntry -> {
//                        log.info("Nested: key: {}, value: {}", stringJsonNodeEntry.getKey(), stringJsonNodeEntry.getValue().toString());
//                    });
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//
//                log.info("is Object bitch!1");
//            }
//
//        });
        parseJsonObjectRefactored(node, "U");
//        parseJsonObject(jsonStr, "main");

    }

    public void parseJsonObjectRefactored(ObjectNode nodes, String name) throws JsonProcessingException {
        for (Iterator<Map.Entry<String, JsonNode>> it = nodes.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> field = it.next();
            if (!field.getValue().isValueNode()) {
                parseJsonObject(field.toString(), "?");
            } else {
                log.info("Object: {} key: {}, value: {}", name, "key", field);
            }
        }
    }

    public void parseJsonObject(String jsonObject, String name) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode nodes = (ObjectNode) mapper.readTree(jsonObject);
        nodes.fields().forEachRemaining(
                field -> {
                    if (!field.getValue().isValueNode()) {
                        try {
                            parseJsonObject(field.getValue().toString(), field.getKey());
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        log.info("Object: {} key: {}, value: {}", name, field.getKey(), field.getValue().toString());
                    }
                }
        );
    }

    public void parseJsonTree(String jsonStr, String key) throws JSONException {
        JSONObject addInfoJson = new JSONObject(jsonStr);
        JSONArray contentListJson = addInfoJson.getJSONArray(key);

        for (int i = 0; i < contentListJson.length(); i++) {
            JSONObject contentJson = contentListJson.getJSONObject(i);
            log.info("parseJsonTree - {}", contentJson.toString());
        }
    }


    public void writeTableModelRow(Row row, Account account) {
        AtomicInteger index = new AtomicInteger();
        Arrays.stream(account.getClass().getDeclaredFields())
                .forEach(field -> {
                    if (field.getType() == String.class) {
                        try {
                            Object value = field.get(account);
                            createCell(row, index.get(), (String) value);
                            index.getAndIncrement();
                            System.out.println((String) value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
//        createCell(row, 0, lineModel.getAlertId());
//        createCell(row, 0, lineModel.getFileDate());
//        createCell(row, 1, lineModel.getClientUserId());
//        createCell(row, 2, lineModel.getScenario());
//        createCell(row, 3, lineModel.getAlertType());
//        createCell(row, 4, lineModel.getCountryCode().toString());
    }


    public void addHeadersFromObject(Row row, Class c) {
        AtomicInteger index = new AtomicInteger();
        Arrays.stream(c.getDeclaredFields()).forEach(
                field -> {
                    Cell cell = row.createCell(index.get());
                    cell.setCellValue(field.getName());
                    index.getAndIncrement();
                }
        );
    }

    private void createCell(Row row, int column, String values) {
        Cell cell = row.createCell(column);
        cell.setCellValue(values);
    }
}
