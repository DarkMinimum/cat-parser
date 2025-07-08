package ua.dark.catparser.mapper;

import org.apache.poi.ss.usermodel.Row;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ua.dark.catparser.entity.Cat;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SimpleRowCatMapper {

    @Mapping(target = "disNo", expression = "java(row.getCell(0).getStringCellValue())")
    @Mapping(target = "historic", expression = "java(row.getCell(1).getStringCellValue())")
    @Mapping(target = "classificationKey", expression = "java(row.getCell(2).getStringCellValue())")
    @Mapping(target = "disasterGroup", expression = "java(row.getCell(3).getStringCellValue())")
    @Mapping(target = "disasterSubgroup", expression = "java(row.getCell(4).getStringCellValue())")
    @Mapping(target = "disasterType", expression = "java(row.getCell(5).getStringCellValue())")
    @Mapping(target = "disasterSubtype", expression = "java(row.getCell(6).getStringCellValue())")
    @Mapping(target = "externalIds", expression = "java(row.getCell(7).getStringCellValue())")
    @Mapping(target = "eventName", expression = "java(row.getCell(8).getStringCellValue())")
    @Mapping(target = "iso", expression = "java(row.getCell(9).getStringCellValue())")
    @Mapping(target = "country", expression = "java(row.getCell(10).getStringCellValue())")
    @Mapping(target = "subRegion", expression = "java(row.getCell(11).getStringCellValue())")
    @Mapping(target = "region", expression = "java(row.getCell(12).getStringCellValue())")
    @Mapping(target = "location", expression = "java(row.getCell(13).getStringCellValue())")
    @Mapping(target = "origin", expression = "java(row.getCell(14).getStringCellValue())")
    @Mapping(target = "associatedTypes", expression = "java(row.getCell(15).getStringCellValue())")
    Cat rowToCat(Row row);
}
