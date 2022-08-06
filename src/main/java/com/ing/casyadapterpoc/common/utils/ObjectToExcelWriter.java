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

@Slf4j
public class ObjectToExcelWriter {


    @SneakyThrows
    public static void writeObjectToExcelFile(Object object, String path) {
        System.out.println(path);
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

        log.info("Initial row number: {}", sheet.getLastRowNum());

        if (sheet.getLastRowNum() < 1) {
            Row headerRow = sheet.createRow(0);
            insertValueIntoCell(jsonNode, RowType.HEADER, headerRow, 0);
        }

        int rowIndex = sheet.getLastRowNum() + 1;
        Row row = sheet.createRow(rowIndex);
        insertValueIntoCell(jsonNode, RowType.ROW, row, 0);

        FileOutputStream outputStream = new FileOutputStream(path);
        workbook.write(outputStream);

    }

    private static int insertValueIntoCell(JsonNode node, RowType rowType, Row row, int startCellIndex) throws JsonProcessingException {
        for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> field = it.next();
            if (!field.getValue().isValueNode() && !field.getValue().isArray()) {
                try {
                    startCellIndex = insertValueIntoCell(field.getValue(), rowType, row, startCellIndex);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                String cellValue = rowType == RowType.HEADER ? field.getKey() : field.getValue().toString();
                createCell(row, startCellIndex, cellValue);
                log.info("index :{} - key: {}, value: {}", startCellIndex, field.getKey(), cellValue);
                startCellIndex++;
            }
        }
        return startCellIndex;
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
