package ua.dark.catparser.entity;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.Column;

import java.time.LocalDate;

@Table
public class Cat {

    @PrimaryKey
    private String disNo;

    @Column
    private String historic;

    @Column
    private String classificationKey;

    @Column
    private String disasterGroup;

    @Column
    private String disasterSubgroup;

    @Column
    private String disasterType;

    @Column
    private String disasterSubtype;

    @Column
    private String externalIds;

    @Column
    private String eventName;

    @Column
    private String iso;

    @Column
    private String country;

    @Column
    private String region;

    @Column
    private String subRegion;

    @Column
    private String continent;

    @Column
    private String location;

    @Column
    private String origin;

    @Column
    private String associatedTypes;

    @Column
    private Integer startYear;

    @Column
    private Integer startMonth;

    @Column
    private Integer startDay;

    @Column
    private Integer endYear;

    @Column
    private Integer endMonth;

    @Column
    private Integer endDay;

    @Column
    private Integer totalDeaths;

    @Column
    private Integer missing;

    @Column
    private Integer injured;

    @Column
    private Integer affected;

    @Column
    private Integer homeless;

    @Column
    private Integer totalAffected;

    @Column
    private Double reconstructionCosts;

    @Column
    private Double reconstructionCostsAdjusted;

    @Column
    private Double insuredDamage;

    @Column
    private Double insuredDamageAdjusted;

    @Column
    private Double totalDamage;

    @Column
    private Double totalDamageAdjusted;

    @Column
    private Double cpi;

    @Column
    private String adminUnits;

    @Column
    private LocalDate entryDate;

    @Column
    private LocalDate lastUpdate;

    public String getDisNo() {
        return disNo;
    }

    public void setDisNo(String disNo) {
        this.disNo = disNo;
    }

    public String getHistoric() {
        return historic;
    }

    public void setHistoric(String historic) {
        this.historic = historic;
    }

    public String getClassificationKey() {
        return classificationKey;
    }

    public void setClassificationKey(String classificationKey) {
        this.classificationKey = classificationKey;
    }

    public String getDisasterGroup() {
        return disasterGroup;
    }

    public void setDisasterGroup(String disasterGroup) {
        this.disasterGroup = disasterGroup;
    }

    public String getDisasterSubgroup() {
        return disasterSubgroup;
    }

    public void setDisasterSubgroup(String disasterSubgroup) {
        this.disasterSubgroup = disasterSubgroup;
    }

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getDisasterSubtype() {
        return disasterSubtype;
    }

    public void setDisasterSubtype(String disasterSubtype) {
        this.disasterSubtype = disasterSubtype;
    }

    public String getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(String externalIds) {
        this.externalIds = externalIds;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getMissing() {
        return missing;
    }

    public void setMissing(Integer missing) {
        this.missing = missing;
    }

    public Integer getInjured() {
        return injured;
    }

    public void setInjured(Integer injured) {
        this.injured = injured;
    }

    public Integer getAffected() {
        return affected;
    }

    public void setAffected(Integer affected) {
        this.affected = affected;
    }

    public Integer getHomeless() {
        return homeless;
    }

    public void setHomeless(Integer homeless) {
        this.homeless = homeless;
    }

    public Integer getTotalAffected() {
        return totalAffected;
    }

    public void setTotalAffected(Integer totalAffected) {
        this.totalAffected = totalAffected;
    }

    public Double getReconstructionCosts() {
        return reconstructionCosts;
    }

    public void setReconstructionCosts(Double reconstructionCosts) {
        this.reconstructionCosts = reconstructionCosts;
    }

    public Double getReconstructionCostsAdjusted() {
        return reconstructionCostsAdjusted;
    }

    public void setReconstructionCostsAdjusted(Double reconstructionCostsAdjusted) {
        this.reconstructionCostsAdjusted = reconstructionCostsAdjusted;
    }

    public Double getInsuredDamage() {
        return insuredDamage;
    }

    public void setInsuredDamage(Double insuredDamage) {
        this.insuredDamage = insuredDamage;
    }

    public Double getInsuredDamageAdjusted() {
        return insuredDamageAdjusted;
    }

    public void setInsuredDamageAdjusted(Double insuredDamageAdjusted) {
        this.insuredDamageAdjusted = insuredDamageAdjusted;
    }

    public Double getTotalDamage() {
        return totalDamage;
    }

    public void setTotalDamage(Double totalDamage) {
        this.totalDamage = totalDamage;
    }

    public Double getTotalDamageAdjusted() {
        return totalDamageAdjusted;
    }

    public void setTotalDamageAdjusted(Double totalDamageAdjusted) {
        this.totalDamageAdjusted = totalDamageAdjusted;
    }

    public Double getCpi() {
        return cpi;
    }

    public void setCpi(Double cpi) {
        this.cpi = cpi;
    }

    public String getAdminUnits() {
        return adminUnits;
    }

    public void setAdminUnits(String adminUnits) {
        this.adminUnits = adminUnits;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(String subRegion) {
        this.subRegion = subRegion;
    }

    public String getAssociatedTypes() {
        return associatedTypes;
    }

    public void setAssociatedTypes(String associatedTypes) {
        this.associatedTypes = associatedTypes;
    }
}
