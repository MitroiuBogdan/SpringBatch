package com.ing.casyadapterpoc.common.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ing.casyadapterpoc.common.domain.casy_entity.Account;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.apache.poi.ss.util.CellUtil.createCell;

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

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(acc);
        ObjectNode node = (ObjectNode) objectMapper.readTree(jsonStr);

        Row headerRow = sheet.createRow(0);
        createCellForHeader(node, "main", headerRow, 0);

        for (int i = 1; i < 20; i++) {
            Row row = sheet.createRow(i);
            createCellFromNode(node, "main", row, 0);
        }
        File outputFile = new File("src/test/resources/" + outputfileName);

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        workbook.write(outputStream);

//        writeTableModelHeader(headerRow);
    }

    public void processLive(Account acc) throws IOException {
        String outputfileName = "newFiless.xls";
        FileInputStream inputStream = new FileInputStream(new File("src/test/resources/" + outputfileName));
        Workbook workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheetAt(0);

        int currentRow = sheet.getLastRowNum() + 1;
        log.info("numbaer of rows {}", currentRow);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(acc);
        ObjectNode node = (ObjectNode) objectMapper.readTree(jsonStr);

        if (currentRow < 1) {
            Row headerRow = sheet.createRow(0);
            createCellForHeader(node, "main", headerRow, 0);
        }

        for (int i = currentRow + 1; i < currentRow + 20; i++) {
            Row row = sheet.createRow(i);
            createCellFromNode(node, "main", row, 0);
        }
        File outputFile = new File("src/test/resources/" + outputfileName);

        FileOutputStream outputStream = new FileOutputStream(outputFile);
        workbook.write(outputStream);
    }


    public int createCellFromNode(JsonNode nodes, String name, Row row, int cellIndex) throws JsonProcessingException {
        for (Iterator<Map.Entry<String, JsonNode>> it = nodes.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> field = it.next();
            if (!field.getValue().isValueNode()) {
                try {
                    cellIndex = createCellFromNode(field.getValue(), field.getKey(), row, cellIndex);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                createCell(row, cellIndex, field.getValue().toString());
                log.info("index :{} - Object: {} key: {}, value: {}", cellIndex, name, field.getKey(), field.getValue().toString());
                cellIndex++;
            }
        }
        return cellIndex;
    }

    public int createCellForHeader(JsonNode nodes, String name, Row row, int cellIndex) throws JsonProcessingException {
        for (Iterator<Map.Entry<String, JsonNode>> it = nodes.fields(); it.hasNext(); ) {
            Map.Entry<String, JsonNode> field = it.next();
            if (!field.getValue().isValueNode()) {
                try {
                    cellIndex = createCellForHeader(field.getValue(), field.getKey(), row, cellIndex);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            } else {
                createCell(row, cellIndex, field.getKey());
                log.info("index :{} - Object: {} key: {}, value: {}", cellIndex, name, field.getKey(), field.getValue().toString());
                cellIndex++;
            }
        }
        return cellIndex;
    }

    private void createCell(Row row, int column, String values) {
        Cell cell = row.createCell(column);
        cell.setCellValue(values);
    }
}
