package com.ing.casyadapterpoc.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Slf4j
public class ObjectToExcelWriter {


    @SneakyThrows
    public static void writeObjectToExcelFile(Object object, String path) {
        log.info("Path: - {}", path);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(object);
        Workbook workbook;
        Sheet sheet;

        try (FileInputStream inputStream = new FileInputStream(path)) {
            workbook = WorkbookFactory.create(inputStream);
            sheet = workbook.getSheetAt(0);
        } catch (IOException e) {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet();
        }
        ObjectNode jsonNode = (ObjectNode) objectMapper.readTree(jsonStr);

//        log.info("Initial row number: {}", sheet.getLastRowNum());

        if (sheet.getLastRowNum() < 1) {
            Row headerRow = sheet.createRow(0);
            insertValueIntoCell(jsonNode, null, RowType.HEADER, headerRow, 0);
        }

        int rowIndex = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowIndex);
        insertValueIntoCell(jsonNode, null, RowType.ROW, row, 0);

        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);

    }

    private static int insertValueIntoCell(JsonNode node, String parentNodeName, RowType rowType, Row row, int startCellIndex) throws JsonProcessingException {
        for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> field = it.next();
            if (!field.getValue().isValueNode() && !field.getValue().isArray()) {
                try {
                    startCellIndex = insertValueIntoCell(field.getValue(), field.getKey(), rowType, row, startCellIndex);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                if (RowType.HEADER == rowType) {
                    String headerValue = constructHeaderValue(parentNodeName, field.getKey());
                    createCell(row, startCellIndex, headerValue);
//                    log.info("Creating header with - Index :{} - Key: {}, Value: {}", startCellIndex, field.getKey(), field.getValue().toString());
                } else {
                    String value;
                    if (field.getValue().isDouble()) {
                        value = field.getValue().toString();
                    } else {
                        value = !isBlank(field.getValue().textValue()) ? field.getValue().textValue() : "null";
                    }
                    createCell(row, startCellIndex, value);
//                    log.info("Index :{} - Key: {}, Value: {}", startCellIndex, field.getKey(),field.getValue());
                }
                startCellIndex++;
            }
        }
        return startCellIndex;
    }

    private static String constructHeaderValue(String parentNodeName, String nodeName) {
        StringBuilder stringBuilder = new StringBuilder();
        String headerValue;
        if (parentNodeName != null) {
            headerValue = stringBuilder.append(parentNodeName)
                    .append(".")
                    .append(nodeName).toString();
        } else {
            headerValue = nodeName;
        }

        log.info("headerValue :{}", headerValue);
        return headerValue;
    }

    //    public static void writeObjectsToExcelFile(List<? extends Object> objList, String path) throws IOException {
//        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(jsonStr);
//        int rowIndex = sheet.getLastRowNum() + 1;
//        for (JsonNode node : arrayNode) {
//            Row row = sheet.createRow(rowIndex);
//            insertValueIntoCell(node, RowType.ROW, row, 0);
//            rowIndex++;
//        }
//    }

    private static void createCell(Row row, int columnIndex, String values) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(values);
    }

    private enum RowType {
        HEADER,
        ROW
    }
}
